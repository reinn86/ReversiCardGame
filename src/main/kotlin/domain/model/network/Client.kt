package domain.model.network

import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket

class Client(port: Int) {
    private val ipAddress = "localhost"
    private val socket = Socket(ipAddress,port)
    private val out = DataOutputStream(socket.getOutputStream())

    fun sendString(str: String) {
        try {
            out.writeUTF(str)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun close() {
        out.close()
        socket.close()
    }
}