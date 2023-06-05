package domain.service.reversi

class Reversi {
    private var isMyTurn = false
    private var troutX = 6
    private var troutY = 6

    val player1 = ""
    val player2 = ""
    val stone = ""

    val board  = Board(6,6)

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