package application.controller

import application.view.Panel
import application.view.scene.battle.Battle
import domain.model.network.Client
import domain.model.network.Server
import domain.service.reversi.Reversi
import java.awt.Color
import java.awt.event.ActionEvent
import java.lang.Thread.sleep
import kotlin.concurrent.thread

object BattleController : Controller() {
    const val PUT_STONE = "PUT_STONE"
    const val DECIDE_TURN = "DECIDE_TURN"

    private val reversi = Reversi()

    private lateinit var client: Client
    private lateinit var server: Server

    //scene
    private val battleScene = Battle()
    override val scene: Panel = battleScene

    override fun actionPerformed(e: ActionEvent) {
        val command = e.actionCommand

        if(command.matches(Regex("${PUT_STONE}_.+"))) {
            val troutX = command.split("_")[2].toInt()
            val troutY = command.split("_")[3].toInt()

            putStone(troutX,troutY,0)
            client.sendString(command)
        }
    }

    private fun startServerThread(port: Int) {
        thread {
            server = Server(port)

            while (true) {
                val command = server.readUTF()
                if (command == "connect") {
                    client = Client(10001)
                }
                else if(command.matches(Regex("${PUT_STONE}_.+"))) {
                    val troutX = command.split("_")[2].toInt()
                    val troutY = command.split("_")[3].toInt()

                    putStone(troutX,troutY,0)
                }
                sleep(100)
            }
        }
    }

    fun decideTurn() {
        reversi.decideMyStoneColor()
        if(reversi.myStoneColor == reversi.board.STONE_WHITE) {
            client.sendString(reversi.board.STONE_BLACK.toString())
        } else {
            client.sendString(reversi.board.STONE_WHITE.toString())
        }
    }

    fun putStone(x: Int, y: Int, state: Int) {
        reversi.putStone(x, y, 0)
        battleScene.boardPanel.squares[x][y]!!.background = Color.GREEN
    }

    fun connectServer() {
        startServerThread(10000)
    }

    fun connectClient() {
        client = Client(10000)
        startServerThread(10001)
        client.sendString("connect")
    }
}