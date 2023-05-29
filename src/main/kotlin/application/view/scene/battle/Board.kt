package application.view.scene.battle

import application.view.Panel
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Toolkit
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

                squares[y][x]!!.size  = Dimension(squareWidth,squareHeight)
                squares[y][x]!!.setLocation(localX,localY)
                add(squares[y][x])


            }
        }
    }

    override fun paintComponent(g: Graphics) {
        val toolkit = Toolkit.getDefaultToolkit()
        val boardImage = toolkit.getImage("src/main/resources/images/battle_board.png")

        super.paintComponent(g)
        g.drawImage(boardImage,0,0,this)
    }
}