package application.view.scene.welcome

import application.controller.TitleController
import application.view.Panel
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel

class Welcome : Panel(){
    //filePaths
    private val bgPath = "src/main/resources/images/title_bg.jpeg"
    private val settingIconPath = "src/main/resources/images/icon_setting.png"

    //images
     private val settingIcon = ImageIcon(settingIconPath)

    //locations
    private var settingButtonLocationX = vw(84.0)
    private var settingButtonLocationY = vw(1.0)
    private val startButtonLocationX = vw(0.0)
    private val startButtonLocationY = vh(0.0)
    private val titleCallLabelX = vw(10.0)
    private val titleCallLabelY = vh(15.0)

    //sizes
    private val settingIconSize = Dimension(vw(15.0),vw(15.0))
    private val settingButtonSize = Dimension(vw(15.0),vw(15.0))
    private val startButtonSize = Dimension(vw(100.0),vh(100.0))
    private val titleCallLabelSize = Dimension(vw(80.0),vh(20.0))

    //views
    private val settingButton = JButton()
    private val startButton = JButton()
    private val titleCallLabel = JLabel()

    init {
        //settingButtonの設定
        settingButton.actionCommand = TitleController.MOVE_SETTING
        settingButton.horizontalTextPosition = JLabel.CENTER
        settingButton.icon = ImageIcon(settingIcon.image.getScaledInstance(
            settingIconSize.width,
            settingIconSize.height,
            Image.SCALE_DEFAULT)
        )
        settingButton.isContentAreaFilled = false
//        settingButton.border = null
        settingButton.size = settingButtonSize
        settingButton.addActionListener(TitleController)
        settingButton.setLocation(settingButtonLocationX,settingButtonLocationY)

        //startButtonの設定
        startButton.actionCommand = TitleController.MOVE_HOME
        startButton.border = null
        startButton.isContentAreaFilled = false
        startButton.size = startButtonSize
        startButton.addActionListener(TitleController)
        startButton.setLocation(startButtonLocationX,startButtonLocationY)

        //titleCallLabelの設定
        titleCallLabel.isOpaque = true
        titleCallLabel.size = titleCallLabelSize
        titleCallLabel.text = "Reversi Card Game"
        titleCallLabel.setLocation(titleCallLabelX,titleCallLabelY)

        //viewの配置
        add(settingButton)
        add(titleCallLabel)
        add(startButton)

        repaint()
    }

    @Override
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val bgImageFile = File(bgPath)
        val settingImageFile = File(settingIconPath)

        val bgBufferedImage = ImageIO.read(bgImageFile)
        val settingBufferedImage = ImageIO.read(settingImageFile)
        val bgImage = bgBufferedImage.getScaledInstance(vw(100.0),vh(100.0),Image.SCALE_DEFAULT)
        val settingImage = settingBufferedImage.getScaledInstance(settingIconSize.width,settingIconSize.height,Image.SCALE_DEFAULT)

        g.drawImage(bgImage,0,0,this)
        g.drawImage(settingImage,settingButtonLocationX,settingButtonLocationY,this)
        repaint()
    }
}
