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

    init {
        //boardPanelの設定
        boardPanel.layout = null
        boardPanel.size = Dimension(vw(80.0),vw(80.0))
        boardPanel.setLocation(vw(10.0),vh(15.0))
        boardPanel.isOpaque = true


        //viewの配置
        add(boardPanel)
        validate()
        repaint()
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val toolkit = Toolkit.getDefaultToolkit()
        val boardImage = toolkit.getImage("src/main/resources/images/battle_board.png")

        g.drawImage(boardImage,0,0,boardPanel)
    }
}
