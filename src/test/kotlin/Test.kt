import domain.service.reversi.Board

fun main() {
    val a = Board()
    a.initBoard()

    val stoneColor = a.STONE_BLACK
    println("stoneColor:${stoneColor}")
    for (i in a.getRayCoordinateVector8(3,4)) {
        for (j in i) {
            print("[${j.getX()},${j.getY()}] ")
        }
        println()
    }
    println()
    for (i in a.getRayCoordinateVector8(3,4)) {
        for (j in i) {
            print("[${a.boardState[j.getX()][j.getY()]}] ")
        }
        println()
    }

    println()
    for (i in a.findCanTurnOVerStoneVector8(a.getRayCoordinateVector8(3,4),stoneColor)) {
        for (j in i) {
            print("[${j.getX()},${j.getY()}] ")
        }
        if (i.size != 0) {
            println()
        }

    }

    a.search(stoneColor)


//    for (i in a.findCanTurnOVerStoneVector8(a.getRayCoordinateVector8(0,0),stoneColor)) {
//        for (j in i) {
//            print("[${j.getX()},${j.getY()}] ")
//        }
//    }
}