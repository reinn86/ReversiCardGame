import domain.service.reversi.Reversi

//fun main() {
//    Window.createWindow()
//    TitleController.start()
//}

fun main() {
    val reversi = Reversi()

    val board = reversi.board

    for (x in board.boardState) {
        for (y in x){
            print(y)
        }
        println()
    }
}