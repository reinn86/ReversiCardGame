package domain.service.reversi

class Board(troutX: Int, troutY: Int) {
    constructor(trout:Int) : this(trout,trout)
    constructor() : this(6)

    var troutX = 0
    private var troutY = 0
    var boardState = Array(troutX) {
        IntArray(troutY)
    }

    private val STONE_EMPTY = 0
    private val STONE_BLACK = 1
    private val STONE_WHITE = 2

    init {
        this.troutX = troutX
        this.troutY = troutY
    }

    /*
     * ボードの初期化
     * ゲームを始める前に実行する
     */
    fun initBoard() {
        clearStones()
        firstPutStones()
    }

    /*
     * 石を配置する関数
     * ここでは石が置けるかの探索は行われない
     */
    fun putStone(x: Int, y: Int, state: Int) {
        boardState[x][y] = state
    }

    /*
     * ボードに何も置いてない状態を作る関数
     */
    private fun clearStones() {
        for (x in 0 until troutX) {
            for (y in 0 until troutY) {
                boardState[x][y] = STONE_EMPTY
            }
        }
    }

    /*
     * 石をリバーシの初期状態になるように配置する関数
     */
    private fun firstPutStones() {
        boardState[2][2] = STONE_WHITE
        boardState[2][3] = STONE_BLACK
        boardState[3][2] = STONE_BLACK
        boardState[3][3] = STONE_WHITE
    }

    /*
     * 指定された座標から指定された方向を見たときの存在する座標を返す関数
     *  TODO 座標を格納する配列は現在Xのサイズになっているため
     *   Yのサイズが長い時にも対応できるように変更する
     */
    fun getRayCoordinate(x: Int, y:Int, a: Int, b: Int): ArrayList<Array<Int>> {
        var baseX = -100
        var baseY = -100
        var toX = -100
        var toY = -100

        //X軸でどこまで探索するかを設定
        when (a) {
            -1 -> {
                baseX = 0
                toX = x
            }
            0 -> {
                baseX = x
                toX = x
            }
            1 -> {
                baseX = x
                toX = troutX - 1
            }
        }
        //Y軸でどこまで探索するかを設定
        when (b) {
            -1 -> {
                baseY = 0
                toY = y
            }
            0 -> {
                baseY = y
                toY = y
            }
            1 -> {
                baseY = y
                toY = troutY - 1
            }
        }

        val coordinates  =  arrayListOf<Array<Int>>()

        while (baseX <= toX && baseY <= toY) {
            coordinates.add(arrayOf(baseX,baseY))
            baseX++
            baseY++
        }

        return coordinates
    }
}