package application.controller

import application.view.scene.matchmake.MatchMake
import domain.model.network.WebSocketClient
import java.awt.event.ActionEvent
import java.lang.Thread.sleep
import javax.swing.JPanel
import kotlin.concurrent.thread

object MatchMakeSceneController : AbstractController() {
    override var mainPanel: JPanel = MatchMake()
    lateinit var wsc: WebSocketClient
    override fun onControllerChange() {}
    val connectThread = Thread {
        while (!wsc.isConnect) {
            println("test1")
            sleep(500)
        }
        if (wsc.isConnect) {
            println("test2")
            changeController(BattleSceneController)
            BattleSceneController.connectClient()
        }
    }

    override fun onStart() {
        thread {
            wsc =WebSocketClient()
            wsc.connectWS(null)
            connectThread.start()
        }
    }

    override fun onEnd() {}


    override fun actionPerformed(e: ActionEvent?) {
        TODO("Not yet implemented")
    }
}