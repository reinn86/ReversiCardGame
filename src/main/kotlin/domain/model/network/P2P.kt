package domain.model.network

class P2P {
    private var isHost = false
    private lateinit var server: Server
    private lateinit var client: Client

    fun connection(isHost: Boolean, port: Int) {
        this.isHost = isHost

        if (isHost) {
            server = Server(port)
        }
        else {
            client = Client(port)
        }
    }

    fun sendUTF(str: String) {
        if (isHost) {
            server.sendUTF(str)
        }
        else {
            client.sendUTF(str)
        }
    }

    fun readUTF(): String {
        if (isHost) {
            return server.readUTF()
        }
        else {
            return client.readUTF()
        }
    }

    fun close() {
        if (isHost) {
            server.close()
        }
        else {
            client.close()
        }
    }
}