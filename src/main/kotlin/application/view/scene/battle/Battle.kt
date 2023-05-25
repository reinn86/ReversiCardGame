package application.view.scene.battle

import application.view.Panel
import java.awt.Color
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JPanel

class Battle : Panel() {
    //components
    private val boardPanel = JPanel()

    private val gridSize  = 6 - 1


    init {
        //boardPanelの設定
        boardPanel.size = Dimension(vw(80.0),vw(80.0))
        boardPanel.background = Color.BLACK
        boardPanel.layout = null
        boardPanel.setLocation(vw(10.0),vh(15.0))
        val squares = Array(6) {
            arrayOfNulls<JButton>(6)
        }

        for (x in 0 .. gridSize) {
            for (y in 0 .. gridSize) {
                val squareWidth = boardPanel.size.width /6
                val squareHeight = boardPanel.size.height /6
                val localX = boardPanel.size.width /6 * x
                val localY = boardPanel.size.width /6 * y
                
                squares[y][x] = JButton()
                squares[y][x]!!.size  = Dimension(squareWidth,squareHeight)
                squares[y][x]!!.setLocation(localX,localY)
                boardPanel.add(squares[y][x])
            }
        }

        //viewの配置
        add(boardPanel)
    }
}