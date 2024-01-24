package application.view.scene.battle

import application.controller.BattleSceneController
import application.view.Panel
import domain.service.reversi.Coordinate
import domain.service.reversi.StoneStatus
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.ImageIcon
import javax.swing.JButton

class Board : Panel(),ActionListener {
    private val blackStoneImage = ImageIcon("src/main/resources/image/game_reversi_black.png")
    private val whiteStoneImage = ImageIcon("src/main/resources/image/game_reversi_white.png")
    private val gridSize  = 6 - 1
    public val squares = Array(6) {
        arrayOfNulls<JButton>(6)
    }
    private var clickedButton = ""

    init {
        layout = null
        background = Color.GREEN
        size = Dimension(vw(80.0),vw(80.0))
        setLocation(vw(10.0),vh(15.0))
        isOpaque = true
        for (y in 0 .. gridSize) {
            for (x in 0 .. gridSize) {
                val squareWidth = size.width /6.0
                val squareHeight = size.height /6.0
                val localX = size.width /6.0 * x
                val localY = size.width /6.0 * y

                squares[x][y] = JButton()
                squares[x][y]!!.name = "${x}_${y}"
                squares[x][y]!!.layout = null
                squares[x][y]!!.isOpaque = true
                squares[x][y]!!.isContentAreaFilled = true
                squares[x][y]!!.background = null
                squares[x][y]!!.actionCommand = "${x}_${y}"
                squares[x][y]!!.size  = Dimension(squareWidth.toInt(),squareHeight.toInt())
                squares[x][y]!!.addActionListener(this)
                squares[x][y]!!.setLocation(localX.toInt(),localY.toInt())
                add(squares[x][y])
            }
        }
        //TODO 初期石の描画処理は個別てやるべき
//        val b = blackStoneImage.image.getScaledInstance(vw(10.0),vw(10.0),0)
//        val w = whiteStoneImage.image.getScaledInstance(vw(10.0),vw(10.0),0)
//        squares[2][2]!!.icon = ImageIcon(w)
//        squares[2][3]!!.icon = ImageIcon(b)
//        squares[3][2]!!.icon = ImageIcon(b)
//        squares[3][3]!!.icon = ImageIcon(w)

    }

    fun initBoard() {
        for (y in 0 .. gridSize) {
            for (x in 0 .. gridSize) {
                squares[x][y]!!.icon = null
            }
        }
        //fixme 47行目と処理がかぶっている
        val b = blackStoneImage.image.getScaledInstance(vw(10.0),vw(10.0),0)
        val w = whiteStoneImage.image.getScaledInstance(vw(10.0),vw(10.0),0)
        squares[2][2]!!.icon = ImageIcon(w)
        squares[2][3]!!.icon = ImageIcon(b)
        squares[3][2]!!.icon = ImageIcon(b)
        squares[3][3]!!.icon = ImageIcon(w)
    }

    override fun actionPerformed(e: ActionEvent) {
        val btn = JButton()

        clickedButton = e.actionCommand
        btn.actionCommand = BattleSceneController.PUT_STONE
        btn.addActionListener(BattleSceneController)
        btn.doClick()
    }

    fun getClickedButton(): Coordinate {
        val x = clickedButton.split("_")[0].toInt()
        val y = clickedButton.split("_")[1].toInt()
        return Coordinate(x,y)
    }

    fun changeImage(color: Int,x: Int,y:Int) {
        if(color == StoneStatus.BLACK) {
            val a = blackStoneImage.image.getScaledInstance(vw(10.0),vw(10.0),0)
            squares[x][y]!!.icon = ImageIcon(a)
        } else if(color == StoneStatus.WHITE) {
            val a = whiteStoneImage.image.getScaledInstance(vw(10.0),vw(10.0),0)
            squares[x][y]!!.icon = ImageIcon(a)
        }
    }
}