package application.view.scene.welcome

import application.controller.TitleController
import application.view.Panel
import domain.model.Resolution
import java.awt.*
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JLabel

class Setting : Panel(){
    //filePaths
    private val bgPath = "src/main/resources/images/title_bg.jpeg"

    //locations
    private val prevButtonX = vw(1.0)
    private val prevButtonY = vw(1.0)
    private val resolutionLabelX = vw(5.0)
    private val resolutionLabelY = vh(10.0)
    private val resolutionComboX = vw(50.0)
    private val resolutionComboY = vh(10.0)

    //sizes
    private val prevButtonSize = Dimension(vw(11.1),vw(11.1))
    private val resolutionLabelSize = Dimension(vw(45.0),vh(10.0))
    private val resolutionComboSize = Dimension(vw(45.0),vh(10.0))

    //components
    private val prevButton = JButton()
    private val resolutionLabel = JLabel()
    private val resolutionCombo = JComboBox<String>()

    //texts
    private val prevButtonText = "×"
    private val resolutionLabelText = "解像度"

    //解像度リスト
    private val resolutions = Resolution.values()

    init {
        //prevButtonの設定
        prevButton.actionCommand = TitleController.MOVE_TITLE
        prevButton.size = prevButtonSize
        prevButton.text = prevButtonText
        prevButton.addActionListener(TitleController)
        prevButton.setLocation(prevButtonX,prevButtonY)

        //resolutionLabelの設定
        resolutionLabel.background = Color.CYAN
        resolutionLabel.isOpaque = true
        resolutionLabel.size = resolutionLabelSize
        resolutionLabel.text = resolutionLabelText
        resolutionLabel.setLocation(resolutionLabelX,resolutionLabelY)

        //resolutionComboの設定
        resolutionCombo.actionCommand = TitleController.CHANGE_RESOLUTION
        resolutionCombo.size = resolutionComboSize
        for (i in resolutions) {
            val dimension = i.toDimension()
            val displayName = "${dimension.width} × ${dimension.height}"
            resolutionCombo.addItem(displayName)
        }
        resolutionCombo.addActionListener(TitleController)
        resolutionCombo.setLocation(resolutionComboX,resolutionComboY)

        //viewの配置
        add(prevButton)
        add(resolutionLabel)
        add(resolutionCombo)
    }

    fun getNewResolution(): Resolution {
        return resolutions[resolutionCombo.selectedIndex]
    }

    @Override
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        //fonts
        val selectionFont = Font("Serif", Font.PLAIN,vw(5.0))
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

        resolutionCombo.font = selectionFont

        g.drawImage(bgImage,bgLocationX,bgLocationY,this)
    }
}