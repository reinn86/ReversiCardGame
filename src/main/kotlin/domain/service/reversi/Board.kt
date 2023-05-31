package domain.service.reversi

class Board {
    val boardState = arrayOf<Array<Int>>()

    private val STONE_EMPTY = 0
    private val STONE_BLACK = 1
    private val STONE_WHITE = 2
    constructor() {
        Reversi(6)
    }

    constructor(trout:Int) {
        Reversi(trout,trout)
    }

    constructor(troutX: Int,troutY: Int) {
//        boardState = arrayOf(arrayOfNulls<Int>(6))
    }
    fun setBoardState(x: Int, y: Int, state: Int) {

    }
}