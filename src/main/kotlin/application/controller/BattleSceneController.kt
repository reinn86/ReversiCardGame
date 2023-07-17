package application.controller

import application.view.Panel
import application.view.scene.battle.Battle
import domain.model.network.P2P
import domain.service.reversi.Coordinate
import domain.service.reversi.Reversi
import domain.service.reversi.StoneStatus
import java.awt.Color
import java.awt.event.ActionEvent
import java.io.IOException
import java.lang.Thread.sleep
import kotlin.concurrent.thread

object BattleSceneController : SceneController() {
    //actionCommand
    const val PUT_STONE = "PUT_STONE"

    //communicationCommand
    private const val CONNECT = "CONNECT"
    private const val DECIDE_TURN = "DECIDE_TURN"
    private const val COMPLETE_PUT_STONE = "COMPLETE_PUT_STONE"
    private const val PASS = "PASS"
    private const val GAME_END = "GAME_END"

    //service
    private val reversi = Reversi()

    //model
    private val peer = P2P()

    //scene
    private val battleScene = Battle()
    override val scene: Panel = battleScene

    override fun actionPerformed(e: ActionEvent) {
        val command = e.actionCommand

        when(command) {
            PUT_STONE -> {
                val coordinate = battleScene.getClickedBoardCoordinate()
                val x = coordinate.x
                val y = coordinate.y
                val canPutStoneCoordinate = reversi.board.searchPlaceableCoordinate(reversi.myStoneColor)

                //石を置くことが可能な座標の数
                if (canPutStoneCoordinate.size == 0) {
                    peer.sendUTF(PASS)
                }
                else {
                    //座標に石が何も置かれていないか
                    if(reversi.board.getState(x,y) == StoneStatus.EMPTY) {
                        for (coordinate in canPutStoneCoordinate) {
                            if (coordinate.matches(Coordinate(x,y))) {
                                putStone(x, y, reversi.myStoneColor)
                                peer.sendUTF("${PUT_STONE}_${x}_${y}")
                                break
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * 石の色を決める関数
     * 自分の意志の色を決めたあと、敵の色の
     */
    private fun decideStoneColor() {
        reversi.decideMyStoneColor()
        val rivalStoneColor = StoneStatus.getReverseColor(reversi.myStoneColor)

        if(reversi.myStoneColor == StoneStatus.WHITE) {
            changeButtonClickable(false)
        } else {
            changeButtonClickable(true)
        }
        println("send:$DECIDE_TURN")
        peer.sendUTF("${DECIDE_TURN}_${rivalStoneColor}")
    }

    private fun putStone(x: Int, y: Int,state: Int) {
        //TODO　下の関数は他クラスに干渉するべきではないのでリファクタした方がいいかも
        val put = reversi.board.getReversibleStoneCoordinatesVector8(x,y, state)
        for (i in put) {
            reversi.putStone(i.x, i.y, state)
            if (state == StoneStatus.BLACK) {
                battleScene.boardPanel.squares[i.x][i.y]!!.background = Color.BLACK
            }
            else if(state == StoneStatus.WHITE) {
                battleScene.boardPanel.squares[i.x][i.y]!!.background = Color.WHITE
            }
        }
    }

    private fun commandPutStone(command: String) {
        val troutX = command.split("_")[2].toInt()
        val troutY = command.split("_")[3].toInt()
        val color = StoneStatus.getReverseColor(reversi.myStoneColor)

        putStone(troutX,troutY, color)
    }

    /*
     * ボタンをクリック可能かを変える関数
     */
    private fun changeButtonClickable(clickable: Boolean) {
        if (clickable) {
            for (buttons in battleScene.boardPanel.squares) {
                for (i in buttons) {
                    i!!.isEnabled = true
                }
            }

        }
        else {
            for (buttons in battleScene.boardPanel.squares) {
                for (i in buttons) {
                    i!!.isEnabled = false
                }
            }
        }
    }

    /*
     * バトルのセットアップをする関数
     * ボードの初期化をします
     */
    private fun setUpBattle() {
        battleScene.boardPanel.initBoard()
        reversi.board.initBoard()
    }

    fun connectServer() {
        setUpBattle()
        peer.connection(true,10000)
        decideStoneColor()
        startCommandReception(true,10000)
    }

    fun connectClient() {
        setUpBattle()
        peer.connection(false,10000)
        startCommandReception(false,10000)
    }
    
    private fun startCommandReception(isHost: Boolean, port: Int) {
        thread {
            var isCommunication = true

            /*
             * fixme コマンドのところには流れを書くのではなく処理を描く
             */
            try {
                while (isCommunication) {
                    val command = peer.readUTF()
                    if (command.matches(Regex("${CONNECT}_.*"))) {
                        println("get:$CONNECT")
                    }
                    else if(command.matches(Regex("${DECIDE_TURN}_.+"))) { //DECIDE_TURN
                        println("get:$DECIDE_TURN")

                        reversi.myStoneColor = command.split("_")[2].toInt()
                        println("stone_color${reversi.myStoneColor}")

                        if (reversi.myStoneColor == StoneStatus.BLACK) {
                            changeButtonClickable(true)

                        } else if(reversi.myStoneColor == StoneStatus.WHITE){
                            changeButtonClickable(false)
                        }
                    }
                    else if(command.matches(Regex("${PUT_STONE}_.+"))) { //PUT_STONE
                        println("get:$PUT_STONE")

                        commandPutStone(command)
                        peer.sendUTF(COMPLETE_PUT_STONE)
                        changeButtonClickable(true)

                        if ( reversi.board.searchPlaceableCoordinate(reversi.myStoneColor).size == 0) {
                            peer.sendUTF(PASS)
                        }

                    }
                    else if (command == COMPLETE_PUT_STONE){ //COMPLETE_PUT_STONE
                        println("get:$COMPLETE_PUT_STONE")
                        changeButtonClickable(false)
                    }
                    else if(command == PASS) {
                        if (reversi.board.searchPlaceableCoordinate(reversi.myStoneColor).size == 0) {
                            changeController(HomeSceneController)
                            peer.sendUTF(GAME_END)
                            peer.close()
                            isCommunication = false
                        }
                        else {
                            changeButtonClickable(true)
                        }
                    }
                    else if(command == GAME_END) {
                        changeController(HomeSceneController)
                        peer.close()
                        isCommunication = false
                    }
                    sleep(100)
                }
            }catch (e: IOException) {
                peer.close()
                changeController(HomeSceneController)
            }
        }
    }
}