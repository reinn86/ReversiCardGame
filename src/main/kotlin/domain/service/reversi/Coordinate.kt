package domain.service.reversi

class Coordinate(x: Int, y: Int) {
    private val coordinate = arrayListOf(x,y)

    init {
        setCoordinateX(x)
        setCoordinateY(y)
    }

    private fun setCoordinateX(x: Int) {
        coordinate[0] = x
    }

    private fun setCoordinateY(y: Int) {
        coordinate[1] = y
    }
    
    fun getX(): Int {
        return coordinate[0]
    }

    fun getY(): Int {
        return coordinate[1]
    }
    fun matches(coordinate: Coordinate): Boolean{
        return (this.getX() == coordinate.getX()
                && this.getY() == coordinate.getY())
    }
}