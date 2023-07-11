package application.view.scene.battle

import application.view.Panel
import java.awt.Dimension
import javax.swing.JLabel

class Battle : Panel() {
    //sizes
    private val boardPanelSize = Dimension(vw(80.0),vw(80.0))
    private val resultLabelSize = Dimension(vw(80.0),vh(10.0))
    //locations
    private val boardPanelX = vw(10.0)
    private val boardPanelY = vh(15.0)
    private val resultLabelSizeX = vw(10.0)
    private val resultLabelSizeY = vw(60.0)

    //components
    val boardPanel = Board()
    private val resultLabel = JLabel()

    init {
        //boardPanelの設定
        boardPanel.isOpaque = true
        boardPanel.size = boardPanelSize
        boardPanel.setLocation(boardPanelX,boardPanelY)

        //viewの配置
        add(boardPanel)
        add(resultLabel)
    }

    fun getClickedSquares(): String {
        return boardPanel.getClickedButton()
    }
}
