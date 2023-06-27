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
    private val bg = File(bgPath)
    private val settingIcon = ImageIcon(settingIconPath)

    //locations
    private val settingButtonLocation = arrayOf(vw(84.0),vh(1.0))

    //sizes
    private val settingIconSize = Dimension(vw(15.0),vw(15.0))

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
        settingButton.size = Dimension(vw(15.0),vw(15.0))
        settingButton.addActionListener(TitleController)
        settingButton.setLocation(vw(84.0),vh(1.0))

        //startButtonの設定
        startButton.actionCommand = TitleController.MOVE_HOME
        startButton.border = null
        startButton.isContentAreaFilled = false
        startButton.size = Dimension(vw(100.0),vh(100.0))
        startButton.addActionListener(TitleController)
        startButton.setLocation(vw(0.0),vh(0.0))

        //titleCallLabelの設定
        titleCallLabel.isOpaque = true
        titleCallLabel.size = Dimension(vw(80.0),vh(20.0))
        titleCallLabel.text = "Reversi Card Game"
        titleCallLabel.setLocation(vw(10.0),vh(15.0))

        //viewの配置
        add(settingButton)
        add(titleCallLabel)
        add(startButton)
    }

    @Override
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        val bufferedImage = ImageIO.read(bg)
        val image = bufferedImage.getScaledInstance(vw(100.0),vh(100.0),Image.SCALE_DEFAULT)

        g.drawImage(image,0,0,this)
    }
}
