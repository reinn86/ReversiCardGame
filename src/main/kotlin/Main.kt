import domain.service.Reversi

//fun main() {
//    Window.createWindow()
//    TitleController.start()
//}

fun main() {
    var tcount = 0
    val r = Reversi()
    for(i in 0 .. 1000) {
        if(r.decideTurn()) {
            tcount++
        }
    }

    println(tcount)
}