package domain.service.reversi

class Reversi {
    private val player1 = ""
    private val player2 = ""
    val board  = Board(6,6)
    var turn = 0
    var myStoneColor = 0
    private var isMyTurn = false

    init {
        board.initBoard()
    }

    fun decideMyStoneColor(){
        val num = (Math.random() * 10).toInt()
        if (num < 5) {
            myStoneColor = board.STONE_BLACK
        }
        myStoneColor = board.STONE_WHITE
    }

    fun putStone(x: Int, y: Int, state: Int) {
        board.putStone(x,y,state)
    }


    fun changeTurn() {
        isMyTurn = !isMyTurn
        turn++
    }
}