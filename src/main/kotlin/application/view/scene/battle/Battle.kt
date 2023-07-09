package application.view.scene.battle

import application.controller.BattleController
import application.view.Panel
import java.awt.Dimension
import javax.swing.JButton

class Battle : Panel() {
    //sizes
    private val boardPanelSize = Dimension(vw(80.0),vw(80.0))

    //locations
    private val boardPanelX = vw(10.0)
    private val boardPanelY = vh(15.0)

    //components
    val boardPanel = Board()
    private val putStoneButton = JButton()

    init {
        //boardPanelの設定
        boardPanel.isOpaque = true
        boardPanel.size = boardPanelSize
        boardPanel.setLocation(boardPanelX,boardPanelY)

        //putStoneButton
        putStoneButton.actionCommand = BattleController.PUT_STONE
        putStoneButton.addActionListener(BattleController)
        //viewの配置
        add(boardPanel)
        add(putStoneButton)
    }

    fun getClickedSquares(): String {
        return boardPanel.getClickedButton()
    }
}
