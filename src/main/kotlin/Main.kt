import domain.service.reversi.Reversi

//fun main() {
//    Window.createWindow()
//    TitleController.start()
//}

fun main() {
    val reversi = Reversi()

    val board = reversi.board
    board.initBoard()

    for (x in board.boardState) {
        for (y in x){
            print(y)
        }
        println()
    }

    for (test in board.getRayCoordinate(2,3,-1,-1)) {
        println("${test[0]} : ${test[1]}")
    }
}