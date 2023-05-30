package domain.service

class Reversi {
    private var isMyTurn = false
    private var troutX = 6
    private var troutY = 6
    private var board  = arrayOf<Array<Int>>()

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
        this.troutX = troutX
        this.troutY = troutY
    }

    fun initArrangement(board : Array<Array<Int>>) {
        for (i in 0.. board.size){
            for (j in 0.. board[i].size) {
                if (j != STONE_EMPTY) {
                    this.board[i][j] = board[i][j]
                }
            }
        }
        board[2][2] = STONE_BLACK
        board[2][3] = STONE_WHITE
        board[3][2] = STONE_WHITE
        board[3][3] = STONE_BLACK
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