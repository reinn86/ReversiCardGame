package domain.service

class Reversi {
    private var isMyTurn = false
    private var troutX = 6
    private var troutY = 6
    private var board  = Array(troutX) {
        arrayOfNulls<Int>(  troutY)
    }

    private val STONE_EMPTY = "0"
    private val STONE_BLACK = "1"
    private val STONE_WHITE = "2"

    constructor() {
        Reversi(6)
    }

    constructor(trout:Int) {
        Reversi(trout,trout)
    }

    constructor(troutX: Int,troutY: Int) {
        this.troutX = troutX
        this.troutY = troutY
    }

    fun initArrangement() {

    }

    fun decideTurn(): Boolean {
        val num = (Math.random() * 10).toInt()
        return num < 5
    }

    fun putStone() {

    }


    fun changeTurn() {
        isMyTurn = !isMyTurn
    }
}