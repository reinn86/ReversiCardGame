package application.view.scene.welcome

import application.controller.TitleSceneController
import application.view.Panel
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel

class Welcome : Panel(){
    // TODO コンポーネントごとに変数を配置した方がいい
    //filePaths
    private val bgPath = "src/main/resources/image/bg_title.jpeg"
    private val settingIconPath = "src/main/resources/image/icon_setting.png"

    //locations
    private val startButtonX = vw(0.0)
    private val startButtonY = vh(0.0)
    private val startLabelX = vw(10.0)
    private val startLabelY = vh(70.0)
    private val titleCallLabelX = vw(10.0)
    private val titleCallLabelY = vh(15.0)
    private val settingButtonX = vw(84.0)
    private val settingButtonY = vw(1.0)

    //sizes
    private val settingButtonSize = Dimension(vw(15.0),vw(15.0))
    private val startLabelSize = Dimension(vw(80.0),vh(10.0))
    private val startButtonSize = Dimension(vw(100.0),vh(100.0))
    private val titleCallLabelSize = Dimension(vw(80.0),vh(20.0))

    //texts
    private val startLabelText = "Click to start"
    private val titleCallLabelText = "Reversi Card Game"

    //components
    private val settingButton = JButton()
    private val startButton = JButton()
    private val startLabel = JLabel()
    private val titleCallLabel = JLabel()

    init {
        //settingButtonの設定
        settingButton.actionCommand = TitleSceneController.MOVE_SETTING
        settingButton.horizontalTextPosition = JLabel.CENTER
        settingButton.isContentAreaFilled = false
        settingButton.size = settingButtonSize
        settingButton.addActionListener(TitleSceneController)
        settingButton.setLocation(settingButtonX,settingButtonY)

        //startButtonの設定
        startButton.actionCommand = TitleSceneController.MOVE_HOME
        startButton.border = null
        startButton.isContentAreaFilled = false
        startButton.size = startButtonSize
        startButton.addActionListener(TitleSceneController)
        startButton.setLocation(startButtonX,startButtonY)

        //startLabelの設定
        startLabel.isOpaque = false
        startLabel.size = startLabelSize
        startLabel.text = startLabelText
        startLabel.setLocation(startLabelX,startLabelY)

        //titleCallLabelの設定
        titleCallLabel.isOpaque = false
        titleCallLabel.size = titleCallLabelSize
        titleCallLabel.text = titleCallLabelText
        titleCallLabel.setLocation(titleCallLabelX,titleCallLabelY)

        //componentの配置
        //ボタン以外のcomponent
        add(startLabel)
        add(titleCallLabel)
        //ボタンなどのcomponent
        add(settingButton)
        add(startButton)
    }

    @Override
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        //fonts
        val titleFont = Font("Serif",Font.PLAIN,vw(10.0))

        //locations
        val bgLocationX = 0
        val bgLocationY = 0

        //sizes
        val bgImageWidth = vw(100.0)
        val bgImageHeight = vh(100.0)
        val settingIconWidth = vw(13.5)
        val settingIconHeight = vw(13.5)

        //images
        val bgImage = ImageIO.read(File(bgPath)).getScaledInstance(
            bgImageWidth,
            bgImageHeight,
            Image.SCALE_DEFAULT
        )
        val settingIcon = ImageIO.read(File(settingIconPath)).getScaledInstance(
            settingIconWidth,
            settingIconHeight,
            Image.SCALE_DEFAULT
        )

        settingButton.icon = ImageIcon(settingIcon)
        titleCallLabel.font = titleFont

        g.drawImage(bgImage,bgLocationX,bgLocationY,this)
    }
}
