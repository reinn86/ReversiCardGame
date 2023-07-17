package domain.model.network

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket

class Client(port: Int) {
    private val ipAddress = "localhost"
    private val socket = Socket(ipAddress,port)
    private val dataIn = DataInputStream(socket.getInputStream())
    private val dataOut = DataOutputStream(socket.getOutputStream())

    fun sendUTF(str: String) {
        try {
            dataOut.writeUTF(str)
        } catch (e: IOException) {
            throw e
        }
    }

    fun readUTF(): String {
        try {
            return dataIn.readUTF()
        } catch (e: IOException) {
            throw e
        }
    }

    fun close() {
        dataIn.close()
        dataOut.close()
        socket.close()
    }
}