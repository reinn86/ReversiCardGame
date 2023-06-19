package domain.model.network

import java.io.DataInputStream
import java.io.IOException
import java.net.ServerSocket

class Server {
    private val port = 10000
    private val serverSocket = ServerSocket(port)
    private val socket = serverSocket.accept()
    private val dataIn = DataInputStream(socket.getInputStream())

    init {
        try {
            println(dataIn.readUTF())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun close() {
        dataIn.close()
        serverSocket.close()
    }
}