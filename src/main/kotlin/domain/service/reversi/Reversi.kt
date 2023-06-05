package domain.service.reversi

class Reversi {
    private val player1 = ""
    private val player2 = ""
    val board  = Board(6,6)

    private var isMyTurn = false

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