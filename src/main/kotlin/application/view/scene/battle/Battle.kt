package application.view.scene.battle

import application.view.Panel
import domain.service.reversi.Coordinate
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO
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
    private val bgPath = "src/main/resources/images/title_bg.jpeg"
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
        g.drawImage(bgImage,bgLocationX,bgLocationY,this)
    }
    init {
        //boardPanelの設定
        boardPanel.isOpaque = true
        boardPanel.size = boardPanelSize
        boardPanel.setLocation(boardPanelX,boardPanelY)

        //viewの配置
        add(boardPanel)
        add(resultLabel)
    }

    fun getClickedBoardCoordinate(): Coordinate {
        return boardPanel.getClickedButton()
    }
}
