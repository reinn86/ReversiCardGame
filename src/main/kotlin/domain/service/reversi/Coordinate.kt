package domain.service.reversi

class Coordinate(x: Int, y: Int) {
    var x = x
        private set
    var y  = y
        private set

    /*
     * 引数の座標とこのオブジェクトの座標を比較する関数
     * 同じ座標ならtrue、違うならfalseを返します
     */
    fun matches(coordinate: Coordinate): Boolean{
        return (x == coordinate.x && y == coordinate.y)
    }
}