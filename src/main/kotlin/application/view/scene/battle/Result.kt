package application.view.scene.battle

import application.controller.BattleSceneController
import application.controller.BattleSceneController.MOVE_HOME
import application.view.Panel
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class Result : Panel() {
    private val resultText = getResult()
    private val resultTextLabel = JLabel()
    private val resultTextLabelSize = Dimension(vw(80.0),vh(50.0))
    private val resultTextLabelX = vw(10.0)
    private val resultTextLabelY = vh(10.0)

    private val moveHomeButton = JButton()
    private val moveHomeButtonText = "ホームに戻る"
    private val moveHomeButtonSize = Dimension(vw(80.0),(vh(10.0)))
    private val moveHomeButtonX = vw(10.0)
    private val moveHomeButtonY = vh(70.0)

    private val imagePanel = JPanel()
    private val imagePanelSize = Dimension(vw(70.0),vw(70.0))
    private val imagePanelLabelX = vw(15.0)
    private val imagePanelLabelY = vh(10.0)

    init {
        resultTextLabel.isOpaque = false
        resultTextLabel.size = resultTextLabelSize
        resultTextLabel.text = resultText
        resultTextLabel.setLocation(resultTextLabelX,resultTextLabelY)

        moveHomeButton.isOpaque = true
        moveHomeButton.size = moveHomeButtonSize
        moveHomeButton.text = moveHomeButtonText
        moveHomeButton.actionCommand = MOVE_HOME
        moveHomeButton.addActionListener(BattleSceneController)
        moveHomeButton.setLocation(moveHomeButtonX,moveHomeButtonY)

        imagePanel.isOpaque = true
        imagePanel.size = imagePanelSize
        imagePanel.setLocation(imagePanelLabelX,imagePanelLabelY)

        add(resultTextLabel)
//        add(imagePanel)
        add(moveHomeButton)
    }

    fun getResult(): String {
        val myStoneColor = BattleSceneController.reversi.myStoneColor
        val rivalStoneColor = BattleSceneController.reversi.getRivalStoneColor()
        val myStoneCount = BattleSceneController.reversi.board.countStone(myStoneColor)
        val rivalStoneCount = BattleSceneController.reversi.board.countStone(rivalStoneColor)
        if(myStoneCount > rivalStoneCount) {
            return "勝利！"
        } else if(myStoneColor < rivalStoneCount) {
            return "敗北…"
        } else {
            return "どろー"
        }
    }

    private val bgPath = "src/main/resources/image/bg_battle.jpg"

    @Override
    override fun paintComponent(g: Graphics) {
        //locations
        val bgLocationX = 0
        val bgLocationY = 0

        //sizes
        val bgImageWidth = vw(100.0)
        val bgImageHeight = vh(100.0)

        //images
        val bgImage = ImageIO.read(File(bgPath)).getScaledInstance(
            bgImageWidth,
            bgImageHeight,
            Image.SCALE_DEFAULT
        )
        resultTextLabel.font = Font("Serif", Font.PLAIN,vw(25.0))
        g.drawImage(bgImage,bgLocationX,bgLocationY,this)
    }
}