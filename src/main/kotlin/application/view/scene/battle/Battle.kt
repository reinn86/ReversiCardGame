package application.view.scene.battle

import application.view.Panel
import domain.service.reversi.Coordinate
import java.awt.*
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JLabel
import javax.swing.JPanel

class Battle : Panel() {
    //sizes
    private val boardPanelSize = Dimension(vw(80.0),vw(80.0))
    private val timerLabelSize = Dimension(vw(20.0),vw(20.0))
    private val myStoneCountLabelSize = Dimension(vw(20.0),vw(20.0))
    private val rivalStoneCountLabelSize = Dimension(vw(20.0),vw(20.0))

    //locations
    private val boardPanelX = vw(10.0)
    private val boardPanelY = vh(15.0)
    private val timerLabelX = vw(75.0)
    private val timerLabelY = vh(70.0)
    private val myStoneCountX = vw(5.0)
    private val myStoneCountY = vw(0.0)
    private val rivalStoneCountX = vw(75.0)
    private val rivalStoneCountY = vw(0.0)

    //components
    val boardPanel = Board()
    private val resultLabel = JLabel()
    val myStoneCountLabel = JLabel("自分:0")
    val rivalStoneCountLabel = JLabel("相手:0")
    val timerLabel = JLabel("20")
    val dPanel = JPanel()
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
        timerLabel.font = Font("Serif", Font.PLAIN,vw(12.0))
        g.drawImage(bgImage,bgLocationX,bgLocationY,this)
    }
    init {
        //boardPanelの設定
        boardPanel.isOpaque = true
        boardPanel.size = boardPanelSize
        boardPanel.setLocation(boardPanelX,boardPanelY)

        //timerLabelの設定
        timerLabel.foreground = Color.RED
        timerLabel.isOpaque = false
        timerLabel.size = timerLabelSize
        timerLabel.setLocation(timerLabelX,timerLabelY )

        myStoneCountLabel.isOpaque = true
        myStoneCountLabel.size = myStoneCountLabelSize
        myStoneCountLabel.setLocation(myStoneCountX,myStoneCountY)

        rivalStoneCountLabel.isOpaque = true
        rivalStoneCountLabel.size = rivalStoneCountLabelSize
        rivalStoneCountLabel.setLocation(rivalStoneCountX,rivalStoneCountY)

        //viewの配置
        add(boardPanel)
        add(timerLabel)
        add(resultLabel)
        add(myStoneCountLabel)
        add(rivalStoneCountLabel)
    }

    fun getClickedBoardCoordinate(): Coordinate {
        return boardPanel.getClickedButton()
    }
}