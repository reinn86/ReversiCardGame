package domain.service.reversi

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin

class Board(troutX: Int, troutY: Int) {
    private val troutX = troutX
    private val troutY = troutY
    private val boardState: Array<IntArray> = Array(troutX) {
        IntArray(troutY)
    }

    fun countStone(color: Int): Int {
        var count = 0

        for (x in 0 until troutX) {
            for (y in 0 until troutY) {
                    if(boardState[x][y] == color) {
                        count++
                    }
            }
        }

        return count
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
     * 指定した座標の状態を変化させる関数
     */
    fun changeState(x: Int, y: Int, state: Int) {
        boardState[x][y] = state
    }

    /*
     * 指定した座標の状態を返す関数
     */
    fun getState(x: Int, y:Int): Int {
        return boardState[x][y]
    }

    fun getReversibleStoneCoordinatesVector8(x: Int, y: Int, stoneColor: Int): ArrayList<Coordinate> {
        val coordinates = arrayListOf<Coordinate>()

        for (i in 0 until 8) {
            val hVector = round(cos(i* PI/4)).toInt()
            val vVector = round(sin(i* PI/4)).toInt()
            val rayCoordinate = getRayCoordinate(x,y,hVector,vVector)
            val canTurnOverCoordinates = getReversibleCoordinates(rayCoordinate,stoneColor)

            for (j in 0 until canTurnOverCoordinates.size) {
                coordinates.add(canTurnOverCoordinates[j])
            }
        }
        return coordinates
    }

    /*
     * ひっくり返せる座標を配列で返す
     */
    private fun getReversibleCoordinates(rayCoordinates: ArrayList<Coordinate>, stoneColor: Int): ArrayList<Coordinate> {
        val reversibleCoordinates = arrayListOf<Coordinate>()
        //TODO 処理が煩雑なのでもうちょい見やすく書き直す
        /*
         * 石は何もないところだけにおかれるべきなので
         * 最初の配列の０番目（初期位置）が空白でない場合に空の配列が返される
         */
        if (getState(rayCoordinates[0].x,rayCoordinates[0].y) != StoneStatus.EMPTY) {
            return reversibleCoordinates
        }
        val target = arrayListOf<Int>()
        for (coordinate in rayCoordinates) {
            target.add(boardState[coordinate.x][coordinate.y])
        }
        val rivalStoneColor = StoneStatus.getReverseColor(stoneColor)


        if (target.indexOf(rivalStoneColor) != -1 && target.indexOf(stoneColor) != -1) { //敵の色がある
            for(i in 1 until target.size) { //敵の色がある間
                if (boardState[rayCoordinates[i].x][rayCoordinates[i].y] == StoneStatus.EMPTY) {
                    return arrayListOf()
                }
                else if (boardState[rayCoordinates[i].x][rayCoordinates[i].y] == rivalStoneColor //敵の色の間
                    && boardState[rayCoordinates[i].x][rayCoordinates[i].y] != StoneStatus.EMPTY) {
                    reversibleCoordinates.add(Coordinate(rayCoordinates[i].x,rayCoordinates[i].y))
                }
                else if(boardState[rayCoordinates[i].x][rayCoordinates[i].y] == stoneColor) { //自分の色になったら
                    if(reversibleCoordinates.size >= 1 && boardState[rayCoordinates[i].x][rayCoordinates[i].y] != rivalStoneColor) {
                        reversibleCoordinates.add(0,Coordinate(rayCoordinates[0].x,rayCoordinates[0].y))
                        println("${rayCoordinates[0].x}:${rayCoordinates[0].y}")
                        return reversibleCoordinates
                    }
                    return arrayListOf()
                }
            }
        }
        return arrayListOf()
    }

    /*
     * すべての座標に対して石が置ける場所があるかを探索する関数
     * 石が置ける場所は座標リストで返されます
     */
    fun searchPlaceableCoordinate(stoneColor: Int): ArrayList<Coordinate> {
        val coordinates  = arrayListOf<Coordinate>()

        for (nowY in 0 until troutY) {
            for (nowX in 0 until troutX) {
                val canTurnOverCoordinates = getReversibleStoneCoordinatesVector8(nowX, nowY, stoneColor)
                for (i in canTurnOverCoordinates) {
                    coordinates.add(i)
                }
            }
        }
        return coordinates
    }

    /*
     * ボードに何も置いてない状態を作る関数
     */
    private fun clearStones() {
        for (x in 0 until troutX) {
            for (y in 0 until troutY) {
                boardState[x][y] = StoneStatus.EMPTY
            }
        }
    }

    /*
     * 石をリバーシの初期状態になるように配置する関数
     * TODO 初期配置を変えられるようにするためにオーバーロードした関数を作る
     */
    private fun firstPutStones() {
        boardState[2][2] = StoneStatus.WHITE
        boardState[2][3] = StoneStatus.BLACK
        boardState[3][2] = StoneStatus.BLACK
        boardState[3][3] = StoneStatus.WHITE
    }

    /*
     * 指定された座標から指定された方向を見たときに存在する座標を返す関数
     * 指定された座標がボードにない場合サイズが0のリストを返します。
     */
    private fun getRayCoordinate(x: Int, y:Int, a: Int, b: Int): ArrayList<Coordinate> {
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
}