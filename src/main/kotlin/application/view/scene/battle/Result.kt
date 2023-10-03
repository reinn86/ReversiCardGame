package application.view.scene.battle

import application.controller.BattleSceneController
import application.controller.BattleSceneController.MOVE_HOME
import application.view.Panel
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JLabel

class Result : Panel() {
    private val resultText = "あなたの${getResult()}です"
    private val resultTextLabel = JLabel()
    private val resultTextLabelSize =Dimension(vw(80.0),vh(20.0))
    private val resultTextLabelX = vw(10.0)
    private val resultTextLabelY = vh(10.0)

    private val moveHomeButton = JButton()
    private val moveHomeButtonText = "ホームに戻る"
    private val moveHomeButtonSize = Dimension(vw(80.0),(vh(10.0)))
    private val moveHomeButtonX = vw(10.0)
    private val moveHomeButtonY = vh(70.0)

    init {
        resultTextLabel.isOpaque = true
        resultTextLabel.size = resultTextLabelSize
        resultTextLabel.text = resultText
        resultTextLabel.setLocation(resultTextLabelX,resultTextLabelY)

        moveHomeButton.isOpaque = true
        moveHomeButton.size = moveHomeButtonSize
        moveHomeButton.text = moveHomeButtonText
        moveHomeButton.actionCommand = MOVE_HOME
        moveHomeButton.addActionListener(BattleSceneController)
        moveHomeButton.setLocation(moveHomeButtonX,moveHomeButtonY)

        add(resultTextLabel)
        add(moveHomeButton)
    }

    fun getResult(): String {
        val myStoneColor = BattleSceneController.reversi.myStoneColor
        val rivalStoneColor = BattleSceneController.reversi.getRivalStoneColor()
        val myStoneCount = BattleSceneController.reversi.board.countStone(myStoneColor)
        val rivalStoneCount = BattleSceneController.reversi.board.countStone(rivalStoneColor)
        if(myStoneCount > rivalStoneCount) {
            return "勝ち"
        } else if(myStoneColor < rivalStoneCount) {
            return "負け"
        } else {
            return "引き分け"
        }
    }
}