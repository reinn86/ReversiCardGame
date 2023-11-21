package application.view.scene.setting

import application.controller.TitleSceneController
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
    private val bgPath = "src/main/resources/image/bg_setting.png"

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
        prevButton.actionCommand = TitleSceneController.MOVE_TITLE
        prevButton.size = prevButtonSize
        prevButton.text = prevButtonText
        prevButton.addActionListener(TitleSceneController)
        prevButton.setLocation(prevButtonX,prevButtonY)

        //resolutionLabelの設定
        resolutionLabel.background = Color.CYAN
        resolutionLabel.isOpaque = true
        resolutionLabel.size = resolutionLabelSize
        resolutionLabel.text = resolutionLabelText
        resolutionLabel.setLocation(resolutionLabelX,resolutionLabelY)

        //resolutionComboの設定
        resolutionCombo.actionCommand = TitleSceneController.CHANGE_RESOLUTION
        resolutionCombo.size = resolutionComboSize
        for (i in resolutions) {
            val dimension = i.toDimension()
            val displayName = "${dimension.width} × ${dimension.height}"
            resolutionCombo.addItem(displayName)
        }
        resolutionCombo.addActionListener(TitleSceneController)
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

        //sizes
        val bgImageWidth = vw((vh(100.0)/(ImageIO.read(File(bgPath)).height)) * 100.0)
//        val bgImageWidth = ImageIO.read(File(bgPath)).width
        val bgImageHeight = vh(100.0)
//        val bgImageHeight = ImageIO.read(File(bgPath)).height
        //locations
//        val bgLocationX = -(bgImageWidth /2) + ApplicationEnvironment.appResolution.toDimension().width/2 -16
        val bgLocationX = horizontalCenter(bgImageWidth)
        val bgLocationY = 0

        //images
        val bgImage = ImageIO.read(File(bgPath)).getScaledInstance(
                bgImageWidth,
                bgImageHeight,
                Image.SCALE_REPLICATE
            )

        resolutionCombo.font = selectionFont

        g.drawImage(bgImage,bgLocationX,bgLocationY,this)
    }
}