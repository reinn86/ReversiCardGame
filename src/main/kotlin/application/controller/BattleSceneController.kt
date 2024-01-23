package application.controller

import application.view.Panel
import application.view.Window
import application.view.scene.battle.Battle
import application.view.scene.battle.Result
import com.google.gson.Gson
import domain.model.network.Command
import domain.service.reversi.Coordinate
import domain.service.reversi.Reversi
import domain.service.reversi.StoneStatus
import okhttp3.WebSocket
import java.awt.event.ActionEvent
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
//    private val peer = P2P()
    private  var peer = MatchMakeSceneController.wsc
    private lateinit var ws : WebSocket
    //scene
    private val battleScene = Battle()
    override fun onStart() {
        peer = MatchMakeSceneController.wsc
        Window.contentPane = battleScene
        connectClient()
        println("roomID: ${peer.roomId}")
        ws = peer.connectWS(peer.roomId)
    }
    override fun onEnd() {}
    override fun onControllerChange() {}

    override var mainPanel: JPanel = battleScene
    val scene: Panel = battleScene

    override fun actionPerformed(e: ActionEvent) {
        when(e.actionCommand) {
            PUT_STONE -> {
//                test
                val coordinate = battleScene.getClickedBoardCoordinate()
                val x = coordinate.x
                val y = coordinate.y
                val canPutStoneCoordinate = reversi.searchPlaceableCoordinate()

                //石を置くことが可能な座標の数
                if (canPutStoneCoordinate.size == 0) {
                    try {
                        changeButtonClickable(false)
//                    peer.send(PASS)
                        ws.send(Gson().toJson(Command(PASS)))
                        println("send:$PASS")
                    }catch (e:Exception) {
                        e.stackTrace
                    }
                } else {
                    for (i in canPutStoneCoordinate) {
                        if (i.matches(Coordinate(x, y))) {
                            putStone(x, y, reversi.myStoneColor)
                            ws.send(Gson().toJson(Command("${PUT_STONE}_${x}_${y}")))
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
        ws.send(Gson().toJson(Command("${DECIDE_TURN}_${rivalStoneColor}")))
    }

    private fun putStone(x: Int, y: Int,state: Int) {
        //TODO　下の関数は他クラスに干渉するべきではないのでリファクタした方がいいかも
        val put = reversi.board.a(x,y, state)
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

    private fun connectClient() {
        setUpBattle()

    }
    
    fun startCommandReception(str :String) {
        thread {

            /*
             * fixme コマンドのところには流れを書くのではなく処理を描く
             */
            if (str == "StartCommand" && peer.isPlayer1 == true) {
                decideStoneColor()
            }
            else if (str.matches(Regex("${CONNECT}_.*"))) {
                println("get:$CONNECT")


            } else if (str.matches(Regex("${DECIDE_TURN}_.+"))) { //DECIDE_TURN
                println("get:$DECIDE_TURN")

                reversi.myStoneColor = str.split("_")[2].toInt()
                println("stone_color${reversi.myStoneColor}")

                if (reversi.myStoneColor == StoneStatus.BLACK) {
                    changeButtonClickable(true)

                } else if (reversi.myStoneColor == StoneStatus.WHITE) {
                    changeButtonClickable(false)
                }
            } else if (str.matches(Regex("${PUT_STONE}_.+"))) { //PUT_STONE
                println("get:$PUT_STONE")

                commandPutStone(str)


                if (reversi.board.searchPlaceableCoordinate(reversi.myStoneColor).size == 0) {
                    changeButtonClickable(false)
                    ws.send(Gson().toJson(Command(PASS)))
                }else {
                    ws.send(Gson().toJson(Command(COMPLETE_PUT_STONE)))
                    changeButtonClickable(true)
                }

            } else if (str == COMPLETE_PUT_STONE) { //COMPLETE_PUT_STONE
                println("get:$COMPLETE_PUT_STONE")
                changeButtonClickable(false)
            } else if (str == PASS) {
                if (reversi.board.searchPlaceableCoordinate(reversi.myStoneColor).size == 0) {
                    changeController(HomeSceneController)
                    ws.send(Gson().toJson(Command(GAME_END)))
                    ws.close(1001,"")
                    Window.contentPane = Result()
                } else {
                    changeButtonClickable(true)
                }
            } else if (str == GAME_END) {
                Window.contentPane = Result()
                ws.close(1001,"")
            }
        }
    }
}