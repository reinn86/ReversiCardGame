package domain.model.network

import java.io.DataInputStream
import java.io.IOException
import java.net.ServerSocket

class Server(port :Int) {
    private val serverSocket = ServerSocket(port)
    private val socket = serverSocket.accept()
    private val dataIn = DataInputStream(socket.getInputStream())

    fun readUTF(): String {
        try {
            return dataIn.readUTF()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }

    fun close() {
        dataIn.close()
        serverSocket.close()
    }
}