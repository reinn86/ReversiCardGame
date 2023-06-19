import domain.model.network.Client

fun main() {
    val c = Client()
    c.sendString("aaa")
    c.close()
}