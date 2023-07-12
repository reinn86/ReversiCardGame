package application.controller

import application.view.Panel
import application.view.scene.battle.Battle
import domain.model.network.Client
import domain.model.network.Server
import domain.service.reversi.Coordinate
import domain.service.reversi.Reversi
import domain.service.reversi.Stone
import java.awt.Color
import java.awt.event.ActionEvent
import java.lang.Thread.sleep
import kotlin.concurrent.thread

object BattleController : Controller() {
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
    private val stone = Stone()

    //model
    private lateinit var client: Client
    private lateinit var server: Server

    //scene
    private val battleScene = Battle()
    override val scene: Panel = battleScene

    override fun actionPerformed(e: ActionEvent) {
        val command = e.actionCommand
        if(command.matches(Regex("${PUT_STONE}_.+"))) { //PUT_STONE
            val x = command.split("_")[2].toInt()
            val y = command.split("_")[3].toInt()
            val canPutStoneCoordinate = reversi.board.search(reversi.myStoneColor)

            //石を置くことが可能な座標の数
            if (canPutStoneCoordinate.size == 0) {
                client.sendString(PASS)
            }
            else {
                //座標に石が何も置かれていないか
                if(reversi.board.boardState[x][y] == reversi.board.STONE_EMPTY) {
                    for (coordinate in canPutStoneCoordinate) {
                        if (coordinate.matches(Coordinate(x,y))) {
                            putStone(x, y, reversi.myStoneColor)
                            client.sendString(command)
                            break
                        }
                    }
                }
            }
        }
    }

    private fun startServerThread(port: Int) {
        thread {
            server = Server(port)

            while (true) {
                val command = server.readUTF()
                if (command.matches(Regex("${CONNECT}_.*"))) {
                    println("get:${CONNECT}")
                    client = Client(10001)

                   decideStoneColor()
                }
                else if(command.matches(Regex("${DECIDE_TURN}_.+"))) { //DECIDE_TURN
                    println("get:${DECIDE_TURN}")

                    reversi.myStoneColor = command.split("_")[2].toInt()
                    println("stone_color${reversi.myStoneColor}")

                    if (reversi.myStoneColor == reversi.board.STONE_BLACK) {
                        changeButtonClickable(true)

                    } else if(reversi.myStoneColor == reversi.board.STONE_WHITE){
                        changeButtonClickable(false)
                    }
                }
                else if(command.matches(Regex("${PUT_STONE}_.+"))) { //PUT_STONE
                    println("get:${PUT_STONE}")

                    commandPutStone(command)
                    client.sendString(COMPLETE_PUT_STONE)
                    changeButtonClickable(true)
                }
                else if (command == COMPLETE_PUT_STONE){ //COMPLETE_PUT_STONE
                    println("get:${COMPLETE_PUT_STONE}")
                    changeButtonClickable(false)
                }
                else if(command == PASS) {
                    if (reversi.board.search(reversi.myStoneColor).size == 0) {
                        changeController(HomeController)
                        client.sendString(GAME_END)
                        server.close()
                        client.close()
                    }
                    else {
//                        commandPutStone(command)
//                        client.sendString(PUT_EMPTY_STONE)
                        changeButtonClickable(true)
                    }
                }
                else if(command == GAME_END) {
                    changeController(HomeController)
                    server.close()
                    client.close()
                }
                sleep(100)
            }
        }
    }


    /*
     * 石の色を決める関数
     * 自分の意志の色を決めたあと、敵の色の
     */
    private fun decideStoneColor() {
        reversi.decideMyStoneColor()
        val rivalStoneColor = stone.getReverseColor(reversi.myStoneColor)

        if(reversi.myStoneColor == reversi.board.STONE_WHITE) {
            changeButtonClickable(false)
        } else {
            changeButtonClickable(true)
        }
        println("send:${DECIDE_TURN}")
        client.sendString("${DECIDE_TURN}_${rivalStoneColor}")
    }

    private fun putStone(x: Int, y: Int,state: Int) {
        val put = reversi.board.findCanTurnOVerStoneVector8(reversi.board.getRayCoordinateVector8(x, y), state)

        for (i in put) {
            for (j in i) {
                reversi.putStone(j.getX(), j.getY(), state)
                if (state == reversi.board.STONE_BLACK) {
                    battleScene.boardPanel.squares[j.getX()][ j.getY()]!!.background = Color.BLACK
                }
                else {
                    battleScene.boardPanel.squares[j.getX()][ j.getY()]!!.background = Color.WHITE
                }
            }
        }
    }

    private fun commandPutStone(command: String) {
        val troutX = command.split("_")[2].toInt()
        val troutY = command.split("_")[3].toInt()
        val color = if (reversi.myStoneColor == reversi.board.STONE_BLACK) {
            reversi.board.STONE_WHITE
        } else {
            reversi.board.STONE_BLACK
        }

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
        startServerThread(10000)
        setUpBattle()
    }

    fun connectClient() {
        client = Client(10000)
        startServerThread(10001)
        setUpBattle()
        client.sendString("${CONNECT}_")
        println("send:${CONNECT}")
    }
}