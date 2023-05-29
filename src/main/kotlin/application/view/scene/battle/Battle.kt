package application.view.scene.battle

import application.view.Panel
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Toolkit
import javax.swing.ImageIcon

class Battle : Panel() {
    //components
    val boardImage = ImageIcon("../resources/images/battle_board.png")

    private val boardPanel = Board()

    private val gridSize  = 6 - 1


    init {
        //boardPanelの設定
        boardPanel.layout = null
        boardPanel.size = Dimension(vw(80.0),vw(80.0))
        boardPanel.setLocation(vw(10.0),vh(15.0))
        boardPanel.isOpaque = true
//        val squares = Array(6) {
//            arrayOfNulls<JButton>(6)
//        }
//        for (x in 0 .. gridSize) {
//            for (y in 0 .. gridSize) {
//                val squareWidth = boardPanel.size.width /6
//                val squareHeight = boardPanel.size.height /6
//                val localX = boardPanel.size.width /6 * x
//                val localY = boardPanel.size.width /6 * y
//
//                squares[y][x] = JButton()
//                squares[y][x]!!.isContentAreaFilled = false
//                squares[y][x]!!.size  = Dimension(squareWidth,squareHeight)
//                squares[y][x]!!.setLocation(localX,localY)
//                boardPanel.add(squares[y][x])
//            }
//        }

        //viewの配置
        add(boardPanel)
        validate()
        repaint()
    }

    override fun paintComponent(g: Graphics) {
        val toolkit = Toolkit.getDefaultToolkit()
        val boardImage = toolkit.getImage("src/main/resources/images/battle_board.png")

        super.paintComponent(g)
        g.drawImage(boardImage,0,0,boardPanel)
    }
}
