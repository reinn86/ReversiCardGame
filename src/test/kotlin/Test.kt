import domain.model.network.P2P
import kotlin.concurrent.thread

fun main() {
    val p = P2P()

    thread {
        p.connection(true,10000)
    }
    println("aaa")
}