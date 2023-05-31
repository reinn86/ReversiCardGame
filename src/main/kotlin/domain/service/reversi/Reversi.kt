package domain.service.reversi

class Reversi {
    private var isMyTurn = false
    private var troutX = 6
    private var troutY = 6
    private var board  = arrayOf<Array<Int>>()

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

    fun decideTurn(): Boolean {
        val num = (Math.random() * 10).toInt()
        return num < 5
    }

    fun initArrangement(initBoard : Array<Array<Int>>) {
        for (i in 0.. board.size){
            for (j in 0.. board[i].size) {
                if (j != STONE_EMPTY) {
                    this.board[i][j] = board[i][j]
                }
            }
        }
    }

    fun putStone() {

    }


    fun changeTurn() {
        isMyTurn = !isMyTurn
    }
}