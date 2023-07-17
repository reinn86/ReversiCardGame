package domain.service.reversi

class Reversi {
    val board  = Board(6,6)
    var myStoneColor = 0

    /*
     * 自分の石の色を決める関数
     */
    fun decideMyStoneColor(){
        val randomNum = (Math.random() * 10).toInt()
        myStoneColor = if (randomNum < 5) {
            StoneStatus.BLACK
        } else {
            StoneStatus.WHITE
        }
    }

    fun putStone(x: Int, y: Int, state: Int) {
        board.changeState(x,y,state)
    }
}