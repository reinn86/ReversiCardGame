package domain.service.reversi

class Stone {
    val STONE_EMPTY = 0
    val STONE_BLACK = 1
    val STONE_WHITE = 2

    /*
     * 逆の色を返す関数
     * 引数が不正な時-1を返します
     */
    fun getReverseColor(stoneColor: Int): Int {
        if (stoneColor == STONE_BLACK) {
            return STONE_WHITE
        }
        else if(stoneColor == STONE_WHITE) {
            return STONE_BLACK
        }
        return -1
    }
}