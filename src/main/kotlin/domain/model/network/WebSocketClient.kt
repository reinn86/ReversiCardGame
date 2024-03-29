package domain.model.network

import application.controller.BattleSceneController
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import okio.ByteString
import java.util.*

class WebSocketClient{
    private lateinit var ws: WebSocket
    var roomId: String? = null // roomId保存用変数
    var playerId: String? = null // plyaerId保存用変数
    var isPlayer1:Boolean? = null
    var isConnect = false
    // リスナーを外に切り分け
    val wsl: WebSocketListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            println("open: $response")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            isConnect = false
            println("closing: $reason")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            println("closed: $reason")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
            println("message bytes: $bytes")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            println("message strs: $text")
            isConnect = true
            doCommand(text) // コマンド実行処理追加
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response) {
            super.onFailure(webSocket, t, response)
            println("failure: $t")
        }
    }

    // 接続処理を切り分け
    fun connectWS(roomId: String?): WebSocket {
        var url = "ws://localhost:8080/WebSocket/game"
        if (roomId != null) {
            if (playerId == null) {
                playerId = UUID.randomUUID().toString() // プレイヤーIDもUUIDで作ってしまう。
            }
            url += "?roomid=$roomId&playerid=$playerId"
        }
        var client = OkHttpClient()
        var request = Request.Builder()
            .url(url)
            .build()
        println("接続$url")
        return client.newWebSocket(request, wsl)
    }

    // コマンド実行処理
    fun doCommand(json: String?) {
        println("受け取ったJSON: ${json}")
        var jsonObject = Gson().fromJson(
            json,
            JsonObject::class.java
        )
       if (jsonObject.has("roomId")) {
            roomId = jsonObject["roomId"].asString
        }
        if (jsonObject.has("player1")) {
            val jsonObject1 = Gson().fromJson(
                jsonObject["player1"],
                JsonObject::class.java
            )
            isPlayer1 = playerId == jsonObject1["id"].asString
            println(isPlayer1)
        }
        if (jsonObject.has("command")) {
            val command = jsonObject["command"].asString
//            val jsonObject = Gson().fromJson(
//                json,
//                JsonObject::class.java
//            )
//            if (jsonObject.has("command")) {
            BattleSceneController.startCommandReception(command)
//            }
        }
    }
}
