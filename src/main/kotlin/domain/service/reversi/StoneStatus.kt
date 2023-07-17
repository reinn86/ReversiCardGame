package domain.service.reversi

object StoneStatus {
    const val EMPTY = 0
    const val BLACK = 1
    const val WHITE = 2

    /*
     * 逆の色を返す関数
     * 引数が不正な時-1を返します
     */
    fun getReverseColor(stoneColor: Int): Int {
        if (stoneColor == BLACK) {
            return WHITE
        }
        else if(stoneColor == WHITE) {
            return BLACK
        }
        return -1
    }
}