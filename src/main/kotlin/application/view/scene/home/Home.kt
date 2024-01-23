package application.view.scene.home

import application.controller.HomeSceneController
import application.view.Panel
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JButton

class Home : Panel() {
    //locations
    private val moveTitleButtonX = vw(0.0)
    private val moveTitleButtonY = vh(0.0)
    private val moveBattleButtonX = vw(5.0)
    private val moveBattleButtonY = vh(75.0)

    //sizes
    private val moveTitleButtonSize = Dimension(vw(10.0),vw(10.0))
    private val moveBattleSize = Dimension(vw(90.0),vh(10.0))

    //texts
    private val moveBattleButtonText = "対戦する！"

    //filePaths
    private val bgPath = "src/main/resources/image/bg_title.jpeg"

    //components
    private val moveTitleButton = JButton()
    private val moveBattleButton = JButton()

    //texts
    private val moveCasualBattleButtonText = "バトルテスト h"
    private val moveRankBattleButtonText = "バトルテスト c"
    private val bgPath = "src/main/resources/image/bg_title.jpeg"

    private val bgImageWidth
        get() = vw(100.0)
    private val bgImageHeight
        get() = vh(100.0)

    @Override
    override fun paintComponent(g: Graphics) {
        //locations
        val bgLocationX = 0
        val bgLocationY = 0

        //sizes
//        val bgImageWidth = vw(100.0)
//        val bgImageHeight = vh(100.0)

        //images
        val bgImage = ImageIO.read(File(bgPath)).getScaledInstance(
            bgImageWidth,
            bgImageHeight,
            Image.SCALE_DEFAULT
        )
        g.drawImage(bgImage,bgLocationX,bgLocationY,this)
    }

    init {
        //moveTitleButtonの設定
        moveTitleButton.actionCommand = HomeSceneController.MOVE_TITLE
        moveTitleButton.size = moveTitleButtonSize
        moveTitleButton.addActionListener(HomeSceneController)
        moveTitleButton.setLocation(moveTitleButtonX,moveTitleButtonY)

//        moveBattleButtonの設定
        moveBattleButton.actionCommand = HomeSceneController.MOVE_BATTLE
        moveBattleButton.size = moveBattleSize
        moveBattleButton.text = moveBattleButtonText
        moveBattleButton.addActionListener(HomeSceneController)
        moveBattleButton.setLocation(moveBattleButtonX,moveBattleButtonY)

        //viewの配置
        add(moveTitleButton)
        add(moveBattleButton)
    }
}