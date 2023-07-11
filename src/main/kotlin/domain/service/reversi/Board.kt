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
    val boardState = Array(troutX) {
        IntArray(troutY)
    }

    val STONE_EMPTY = 0
    val STONE_BLACK = 1
    val STONE_WHITE = 2

    init {
        this.troutX = troutX
        this.troutY = troutY
        initBoard()
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
     fun getRayCoordinate(x: Int, y:Int, a: Int, b: Int): ArrayList<Coordinate> {
        var locationX = x
        var locationY = y
        val coordinates  =  arrayListOf<Coordinate>()

        //座標が異常な時からのリストを返して処理を終わらせる
        if(troutX - 1 < x || x < 0) {
            return coordinates
        }
        if (troutY - 1 < y || y < 0) {
            return coordinates
        }
        while (locationX in 0 until troutX
            && locationY in 0 until  troutY) {
            coordinates.add(Coordinate(locationX,locationY))
            locationX += a
            locationY += b
        }

        return coordinates
    }

    fun getRayCoordinateVector8(x: Int, y: Int): Array<ArrayList<Coordinate>> {
        val coordinatesVector8 = Array(8) {
            arrayListOf<Coordinate>()
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
    private fun getRayStoneStatus(coordinates: ArrayList<Coordinate>): ArrayList<Int> {
        val stoneStatuses  =  arrayListOf<Int>()
        for (coordinate in coordinates) {
            stoneStatuses.add(boardState[coordinate.getX()][coordinate.getY()])
        }

        return stoneStatuses
    }

    /*
     * ひっくり返せるマスを配列で返す
     */
    fun findCanTurnOverStone(rayCoordinates: ArrayList<Coordinate>, stoneColor: Int): ArrayList<Coordinate> {
        val coordinates = arrayListOf<Coordinate>()

        /*
         * 石は何もないところだけにおかれるべきなので
         * 最初の配列の０番目（初期位置）が空白でない場合に空の配列が返される
         */
        if (boardState[rayCoordinates[0].getX()][rayCoordinates[0].getY()] != STONE_EMPTY) {
            return coordinates
        }
        val target = getRayStoneStatus(rayCoordinates)

        val enmStoneColor = if(stoneColor == STONE_BLACK) {
            STONE_WHITE
            } else {
            STONE_BLACK
            }
//        println(target.indexOf(enmStoneColor) != -1)

//        println(target)

        if (target.indexOf(enmStoneColor) != -1 && target.indexOf(stoneColor) != -1) { //敵の色がある
            for(i in 1 until target.size) { //敵の色がある間
                if (boardState[rayCoordinates[i].getX()][rayCoordinates[i].getY()] == STONE_EMPTY) {
                    return arrayListOf()
                }
                 else if (boardState[rayCoordinates[i].getX()][rayCoordinates[i].getY()] == enmStoneColor //敵の色の間
                    && boardState[rayCoordinates[i].getX()][rayCoordinates[i].getY()] != STONE_EMPTY) {
                    coordinates.add(Coordinate(rayCoordinates[i].getX(),rayCoordinates[i].getY()))
                }
                else if(boardState[rayCoordinates[i].getX()][rayCoordinates[i].getY()] == stoneColor) { //自分の色になったら
                    if(coordinates.size >= 1) {
                        coordinates.add(0,Coordinate(rayCoordinates[0].getX(),rayCoordinates[0].getY()))
                        return coordinates
                    }
                }
            }
        }
        return arrayListOf()
    }

    fun findCanTurnOVerStoneVector8(rayCoordinateVector8: Array<ArrayList<Coordinate>>, stoneColor: Int): Array<ArrayList<Coordinate>> {
        val coordinatesVector8 = Array(8) {
            arrayListOf<Coordinate>()
        }

        for (i in 0 until  8) {
            coordinatesVector8[i] = (findCanTurnOverStone(rayCoordinateVector8[i],stoneColor))
        }
        return coordinatesVector8
    }

    fun search(stoneColor: Int): ArrayList<Coordinate> {
        val a  = arrayListOf<Coordinate>()

        for (nowY in 0 until troutY) {
            for (nowX in 0 until troutX) {
                val canTurnOverCoordinates = findCanTurnOVerStoneVector8(getRayCoordinateVector8(nowX, nowY), stoneColor)
                for (i in canTurnOverCoordinates) {
                    for (j in i) {
                        a.add(Coordinate(j.getX(),j.getY()))
                        print("[${j.getX()},${j.getY()}] ")
                    }
                    if(i.size != 0) {
                        println()
                    }
                }
            }
        }
        return a
    }
}