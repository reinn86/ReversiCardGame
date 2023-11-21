package domain.model.network

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import okio.ByteString
import java.util.*
import javax.swing.*
import javax.swing.border.EmptyBorder

class WebSocketClient{
    private lateinit var ws: WebSocket
    private var roomId: String? = null // roomId保存用変数
    private var playerId: String? = null // plyaerId保存用変数

    // リスナーを外に切り分け
    private val wsl: WebSocketListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            println("open: $response")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
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
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        println("接続$url")
        return client.newWebSocket(request, wsl)
    }

    // コマンド実行処理
    fun doCommand(json: String?) {
        val jsonObject = Gson().fromJson(
            json,
            JsonObject::class.java
        )
        if (jsonObject.has("command")) {
            val command = jsonObject["command"].asString
        }
    }

}
