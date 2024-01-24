
import domain.service.reversi.Timer
import java.lang.Thread.sleep

fun main() {
    val a = Timer()
    a.timer.start()
    while (!a.isTimeCome) {
        sleep(1000)
    }
    a.timer.start()
}