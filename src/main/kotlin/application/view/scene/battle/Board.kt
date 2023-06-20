package application.view.scene.battle

import application.view.Panel
import java.awt.Color
import java.awt.Dimension
import javax.swing.ImageIcon
import javax.swing.JButton

class Board : Panel() {
    private val blackStoneImage = ImageIcon("src/main/resources/images/game_reversi_black.png")
    private val gridSize  = 6 - 1
    private val squares = Array(6) {
        arrayOfNulls<JButton>(6)
    }

    init {
        layout = null
        size = Dimension(vw(80.0),vw(80.0))
        setLocation(vw(10.0),vh(15.0))
        isOpaque = true

        for (x in 0 .. gridSize) {
            for (y in 0 .. gridSize) {
                val squareWidth = size.width /6
                val squareHeight = size.height /6
                val localX = size.width /6 * x
                val localY = size.width /6 * y

                squares[y][x] = JButton()
//                squares[y][x]!!.background = Color.GREEN

                squares[y][x]!!.layout = null
                squares[y][x]!!.isOpaque = true
                squares[y][x]!!.isContentAreaFilled = true
                squares[y][x]!!.size  = Dimension(squareWidth,squareHeight)
                squares[y][x]!!.setLocation(localX,localY)
                add(squares[y][x])
            }
        }

        squares[2][2]!!.background = Color.WHITE
        squares[2][3]!!.background = Color.BLACK
        squares[3][2]!!.background = Color.BLACK
        squares[3][3]!!.background = Color.WHITE
        validate()
        repaint()
    }


//    override fun paintComponent(g: Graphics) {
//        super.paintComponent(g)
//        val toolkit = Toolkit.getDefaultToolkit()
//        val stoneWhiteImage = toolkit.getImage("src/main/resources/images/game_reversi_black.png")
//        g.drawImage(stoneWhiteImage,squares[2][2]!!.location.x,squares[2][2]!!.location.y,squares[2][2])
//        squares[2][2]!!.validate()
//        squares[2][2]!!.repaint()
//    }
}