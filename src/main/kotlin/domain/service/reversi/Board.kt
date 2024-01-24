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
    private fun getState(x: Int, y:Int): Int {
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
    private fun printTest(r:ArrayList<Coordinate>) {
        for (i in r) {
            print(boardState[i.x][i.y])
        }
        println()
    }

    //置ける座標か判断する
    private fun getReversibleCoordinates(rayCoordinates: ArrayList<Coordinate>, stoneColor: Int): ArrayList<Coordinate> {
        val rivalStoneColor = StoneStatus.getReverseColor(stoneColor)
        val rc = arrayListOf<Coordinate>()
        /* 最初が空白ではないまたは
         * 次が敵の石ではない
         * 場合は処理を終了する
         */
        if (getState(rayCoordinates[0].x,rayCoordinates[0].y) != StoneStatus.EMPTY) {
            return rc
        }
        else if(rayCoordinates.size >= 2
            && getState(rayCoordinates[1].x,rayCoordinates[1].y) != rivalStoneColor) {
            return rc
        }
        //直線座標読み込み
        for ((j, r) in rayCoordinates.withIndex()) {
            //座標にあるステータスが空白になったら処理終了
            if (boardState[r.x][r.y] == StoneStatus.EMPTY && j != 0) {
                return arrayListOf()
            }
            //自分の色になったらそれまでの座標は返せるのでリストに追加
            else if(boardState[r.x][r.y] == stoneColor) {
                rc.add(Coordinate(rayCoordinates[0].x,rayCoordinates[0].y))
                return rc
            }
        }
        return arrayListOf()
    }

    fun a(x: Int, y: Int, stoneColor: Int): ArrayList<Coordinate> {
        val coordinates = arrayListOf<Coordinate>()

        for (i in 0 until 8) {
            val hVector = round(cos(i* PI/4)).toInt()
            val vVector = round(sin(i* PI/4)).toInt()
            val rayCoordinate = getRayCoordinate(x,y,hVector,vVector)
            val canTurnOverCoordinates = b(rayCoordinate,stoneColor)

            for (j in 0 until canTurnOverCoordinates.size) {
                coordinates.add(canTurnOverCoordinates[j])
            }
        }
        return coordinates
    }
    //返せる座標を配列で返す
    private fun b(rayCoordinates: ArrayList<Coordinate>, stoneColor: Int): ArrayList<Coordinate> {
        val rivalStoneColor = StoneStatus.getReverseColor(stoneColor)
        val rc = arrayListOf<Coordinate>()
        /* 最初が空白ではないまたは
         * 次が敵の石ではない
         * 場合は処理を終了する
         */
        if (getState(rayCoordinates[0].x,rayCoordinates[0].y) != StoneStatus.EMPTY) {
            return rc
        }
        else if(rayCoordinates.size >= 2
            && getState(rayCoordinates[1].x,rayCoordinates[1].y) != rivalStoneColor) {
            return rc
        }
        var j = 0
        //直線座標読み込み
        printTest(rayCoordinates)
        for (r in rayCoordinates) {
            //座標にあるステータスが空白になったら処理終了
            if (boardState[r.x][r.y] == StoneStatus.EMPTY && j != 0) {
                return arrayListOf()
            }
            //自分の色になったらそれまでの座標は返せるのでリストに追加
            else if(boardState[r.x][r.y] == stoneColor) {
//                rc.add(Coordinate(rayCoordinates[0].x,rayCoordinates[0].y))
//                println("rc:${rc[0].x},${rc[0].y}")
//                return rc
                for(i in 0 until j) {
                    rc.add(i,Coordinate(rayCoordinates[i].x,rayCoordinates[i].y))
                    println("rc:${rc[i].x},${rc[i].y}")
                }
                return rc
            }
            j++
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
    fun firstPutStones() {
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