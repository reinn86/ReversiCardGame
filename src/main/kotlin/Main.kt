import application.controller.TitleController
import application.view.Window

fun main() {
    Window.createWindow()
    TitleController.start()
}

//fun main() {
//    val s = Server()
////    val reversi = Reversi()
////
////    val board = reversi.board
////    board.initBoard()
////
////    for (x in board.boardState) {
////        for (y in x){
////            print(y)
////        }
////        println()
////    }
////
////    for (test in board.getRayCoordinate(3,3,1,-1)) {
////        println("${test[0]} : ${test[1]}")
////    }
//}