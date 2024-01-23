package application.controller

import application.view.scene.matchmake.MatchMake
import domain.model.network.WebSocketClient
import okhttp3.WebSocket
import java.awt.event.ActionEvent
import java.lang.Thread.sleep
import java.util.concurrent.Executors
import javax.swing.JPanel

object MatchMakeSceneController : AbstractController() {
//    var executor = Executors.newSingleThreadExecutor()
    override var mainPanel: JPanel = MatchMake()
    lateinit var wsc: WebSocketClient
    private lateinit var ws : WebSocket
    override fun onControllerChange() {}
    private lateinit var connectThread:Thread
//    = Thread {
//        while (!wsc.isConnect) {
//            sleep(500)
//        }
//        if (wsc.isConnect) {
////            changeController(BattleSceneController)
//            BattleSceneController.start()
//            executor.shutdown()
//        }
//    }

    override fun onStart() {

        wsc = WebSocketClient()
        ws = wsc.connectWS(null)
        try {
            connectThread = Thread {
                while (!wsc.isConnect) {
                    sleep(500)
                }
                if (wsc.isConnect) {
//            changeController(BattleSceneController)
                    BattleSceneController.start()
                    ws.close(1000,"")
                }
            }
                connectThread.start()
            }catch (e:InterruptedException) {
            e.stackTrace
        }
    }

    override fun onEnd() {}


    override fun actionPerformed(e: ActionEvent?) {
        TODO("Not yet implemented")
    }
}