import domain.service.reversi.Board

fun main() {
    val a = Board()
    a.initBoard()

    val b = a.getRayCoordinateVector8(3,4)
    val c = a.findCanTurnOVerStoneVector8(b,1)
    for (i in c) {
        if (i.size >= 1) {
            for (j in i) {
                println("${j[0]},${j[1]}")
            }
        }
    }

//    for (i in b) {
//        for (j in i) {
//            println("${j[0]},${j[1]}")
//        }
//        println()
//    }
}