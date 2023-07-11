package domain.service.reversi

class Reversi {
    val board  = Board(6,6)
    var myStoneColor = 0


    fun decideMyStoneColor(){
        val num = (Math.random() * 10).toInt()
        myStoneColor = if (num < 5) {
            board.STONE_BLACK
        } else {
            board.STONE_WHITE
        }
    }

    fun putStone(x: Int, y: Int, state: Int) {
        board.putStone(x,y,state)
    }
}