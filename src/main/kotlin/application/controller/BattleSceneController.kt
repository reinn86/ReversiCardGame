package application.controller

import application.view.Panel
import application.view.Window
import application.view.scene.battle.Battle
import application.view.scene.battle.Result
import domain.model.network.P2P
import domain.service.reversi.Coordinate
import domain.service.reversi.Reversi
import domain.service.reversi.StoneStatus
import java.awt.event.ActionEvent
import java.io.IOException
import java.lang.Thread.sleep
import javax.swing.JPanel
import kotlin.concurrent.thread

object BattleSceneController : AbstractController() {
    //actionCommand
    const val PUT_STONE = "PUT_STONE"
    const val MOVE_HOME = "MOVE_HOME"

    //communicationCommand
    private const val CONNECT = "CONNECT"
    private const val DECIDE_TURN = "DECIDE_TURN"
    private const val COMPLETE_PUT_STONE = "COMPLETE_PUT_STONE"
    private const val PASS = "PASS"

    private const val GAME_END = "GAME_END"

    //service
    val reversi = Reversi()

    //model
    private val peer = P2P()

    //scene
    private val battleScene = Battle()

    override var mainPanel: JPanel = battleScene
    val scene: Panel = battleScene

    override fun actionPerformed(e: ActionEvent) {
        when(e.actionCommand) {
            PUT_STONE -> {
                val coordinate = battleScene.getClickedBoardCoordinate()
                val x = coordinate.x
                val y = coordinate.y
                val canPutStoneCoordinate = reversi.searchPlaceableCoordinate()

                //石を置くことが可能な座標の数
                if (canPutStoneCoordinate.size == 0) {
                    changeButtonClickable(false)
                    peer.sendUTF(PASS)
                } else {
                    for (i in canPutStoneCoordinate) {
                        if (i.matches(Coordinate(x, y))) {
                            putStone(x, y, reversi.myStoneColor)
                            peer.sendUTF("${PUT_STONE}_${x}_${y}")
                            break
                        }
                    }
                }
            }
            MOVE_HOME -> {
                BattleSceneController.changeController(HomeSceneController)
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
                battleScene.boardPanel.changeImage(StoneStatus.BLACK,i.x,i.y)
            }
            else if(state == StoneStatus.WHITE) {
                battleScene.boardPanel.changeImage(StoneStatus.WHITE,i.x,i.y)
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
        startCommandReception()
    }

    fun connectClient() {
        setUpBattle()
        peer.connection(false,10000)
        startCommandReception()
    }
    
    private fun startCommandReception() {
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
                            Window.contentPane = Result()
                        }
                        else {
                            changeButtonClickable(true)
                        }
                    }
                    else if(command == GAME_END) {
//                        changeController(HomeSceneController)
                        peer.close()
                        Window.contentPane = Result()
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