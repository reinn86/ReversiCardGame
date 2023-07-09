package domain.service.reversi

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin

class Board(troutX: Int, troutY: Int) {
    constructor(trout:Int) : this(trout,trout)
    constructor() : this(6)

    private var troutX = 0
    private var troutY = 0
    private val boardState = Array(troutX) {
        IntArray(troutY)
    }

    val STONE_EMPTY = 0
    val STONE_BLACK = 1
    val STONE_WHITE = 2

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
     * 指定された座標がボードにない場合サイズが0のリストを返します。
     */
     fun getRayCoordinate(x: Int, y:Int, a: Int, b: Int): ArrayList<Array<Int>> {
        var locationX = x
        var locationY = y
        val coordinates  =  arrayListOf<Array<Int>>()

        //向きの値が異常な時からのリストを返して処理を終わらせる
        if(troutX - 1 < x || x < 0) {
            return coordinates
        }
        if (troutY - 1 < y || y < 0) {
            return coordinates
        }

        while (locationX in 0 until troutX
            && locationY in 0 until  troutY) {
            coordinates.add(arrayOf(locationX,locationY))
            locationX += a
            locationY += b
        }

        return coordinates
    }

    fun getRayCoordinateVector8(x: Int, y: Int): Array<ArrayList<Array<Int>>> {
        val coordinatesVector8 = Array(8) {
            arrayListOf<Array<Int>>()
        }

        for (i in coordinatesVector8.indices){
            val hVector = round(cos(i* PI/4)).toInt()
            val vVector = round(sin(i* PI/4)).toInt()
            val rayCoordinates =  getRayCoordinate(x,y,hVector,vVector)

            coordinatesVector8[i] = rayCoordinates
        }

        return coordinatesVector8
    }

    /*
     * 与えられた座標のリストの石の状態を返す関数
     */
    private fun getRayStoneStatus(coordinates: ArrayList<Array<Int>>): ArrayList<Int> {
        val stoneStatuses  =  arrayListOf<Int>()
        for (coordinate in coordinates) {
            stoneStatuses.add(boardState[coordinate[0]][coordinate[1]])
        }

        return stoneStatuses
    }

    /*
     * ひっくり返せるマスを配列で返す
     */
    private fun findCanTurnOverStone(rayCoordinateVector: ArrayList<Array<Int>>, stoneColor: Int): ArrayList<Array<Int>> {
        val coordinates = arrayListOf<Array<Int>>()

        /*
         * 石は何もないところだけにおかれるべきなので
         * 最初の配列の０番目（初期位置）が空白でない場合に空の配列が返される
         */
        if (boardState[rayCoordinateVector[0][0]][rayCoordinateVector[0][1]] != STONE_EMPTY) {
            return coordinates
        }

        val target = getRayStoneStatus(rayCoordinateVector).indexOf(stoneColor)
        for(i in 1 until target) {
            coordinates.add(arrayOf(rayCoordinateVector[i][0],rayCoordinateVector[i][1]))
        }

        return coordinates
    }

    fun findCanTurnOVerStoneVector8(rayCoordinateVector8: Array<ArrayList<Array<Int>>>, stoneColor: Int): Array<ArrayList<Array<Int>>> {
        val coordinatesVector8 = Array(8) {
            arrayListOf<Array<Int>>()
        }

        for (i in 0 until  8) {
            coordinatesVector8[i] = (findCanTurnOverStone(rayCoordinateVector8[i],stoneColor))
        }
        return coordinatesVector8
    }
}