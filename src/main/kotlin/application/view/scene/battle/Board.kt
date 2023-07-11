package application.view.scene.battle

import application.controller.BattleController
import application.view.Panel
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.ImageIcon
import javax.swing.JButton

class Board : Panel(),ActionListener {
    private val blackStoneImage = ImageIcon("src/main/resources/images/game_reversi_black.png")
    private val gridSize  = 6 - 1
    val squares = Array(6) {
        arrayOfNulls<JButton>(6)
    }
    private var clickedButton = ""

    init {
        layout = null
        size = Dimension(vw(80.0),vw(80.0))
        setLocation(vw(10.0),vh(15.0))
        isOpaque = true
        for (y in 0 .. gridSize) {
            for (x in 0 .. gridSize) {
                val squareWidth = size.width /6
                val squareHeight = size.height /6
                val localX = size.width /6 * x
                val localY = size.width /6 * y

                squares[x][y] = JButton()
                squares[x][y]!!.layout = null
                squares[x][y]!!.isOpaque = true
                squares[x][y]!!.isContentAreaFilled = true
                squares[x][y]!!.background = null
                squares[x][y]!!.actionCommand = BattleController.PUT_STONE + "_${x}_${y}"
                squares[x][y]!!.size  = Dimension(squareWidth,squareHeight)
                squares[x][y]!!.addActionListener(BattleController)
                squares[x][y]!!.addActionListener(this)
                squares[x][y]!!.setLocation(localX,localY)
                add(squares[x][y])
            }
        }
        squares[2][2]!!.background = Color.WHITE
        squares[2][3]!!.background = Color.BLACK
        squares[3][2]!!.background = Color.BLACK
        squares[3][3]!!.background = Color.WHITE

    }

    fun initBoard() {
        for (y in 0 .. gridSize) {
            for (x in 0 .. gridSize) {
                val squareWidth = size.width /6
                val squareHeight = size.height /6
                val localX = size.width /6 * x
                val localY = size.width /6 * y

                squares[x][y]!!.background = null
            }
        }
        squares[2][2]!!.background = Color.WHITE
        squares[2][3]!!.background = Color.BLACK
        squares[3][2]!!.background = Color.BLACK
        squares[3][3]!!.background = Color.WHITE

    }

    override fun actionPerformed(e: ActionEvent) {
        clickedButton = e.actionCommand

    }

    fun getClickedButton(): String {
        return clickedButton
    }
}