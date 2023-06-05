package domain.service.reversi

class Board(troutX: Int, troutY: Int) {
    constructor(trout:Int) : this(trout,trout)
    constructor() : this(6)

    var troutX = 0
    var troutY = 0
    var boardState = Array(troutX) {
        arrayOfNulls<Int>(troutY)
    }

    private val STONE_EMPTY = 0
    private val STONE_BLACK = 1
    private val STONE_WHITE = 2

    init {
        this.troutX = troutX
        this.troutY = troutY
        initBoardState()
        firstPutStone()
    }

    fun putStone(x: Int, y: Int, state: Int) {
        boardState[x][y] = state
    }
    fun initBoard() {
        initBoardState()
        firstPutStone()
    }

    fun initBoardState() {
        boardState = Array(troutX) {
            arrayOfNulls<Int>(troutY)
        }
        for (x in 0 until troutX) {
            for (y in 0 until troutY) {
                boardState[x][y] = STONE_EMPTY
            }
        }
    }

    private fun firstPutStone() {
        boardState[2][2] = STONE_WHITE
        boardState[2][3] = STONE_BLACK
        boardState[3][2] = STONE_BLACK
        boardState[3][3] = STONE_WHITE
    }
}