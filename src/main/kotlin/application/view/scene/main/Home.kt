package application.view.scene.main

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
    private val moveRankBattleButtonX = vw(5.0)
    private val moveRankBattleButtonY = vh(60.0)
    private val moveCasualBattleButtonX = vw(5.0)
    private val moveCasualBattleButtonY = vw(80.0)

    //sizes
    private val moveTitleButtonSize = Dimension(vw(10.0),vw(10.0))
    private val moveRankBattleButtonSize = Dimension(vw(90.0),vh(10.0))
    private val moveCasualBattleButtonSize = Dimension(vw(90.0),vh(10.0))

    //components
    private val moveTitleButton = JButton()
    private val moveCasualBattleButton = JButton()
    private val moveRankBattleButton = JButton()

    //texts
    private val moveCasualBattleButtonText = "バトルテスト h"
    private val moveRankBattleButtonText = "バトルテスト c"
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
        //moveTitleButtonの設定
        moveTitleButton.actionCommand = HomeSceneController.MOVE_TITLE
        moveTitleButton.size = moveTitleButtonSize
        moveTitleButton.addActionListener(HomeSceneController)
        moveTitleButton.setLocation(moveTitleButtonX,moveTitleButtonY)

        //moveRankBattleButtonの設定
        moveRankBattleButton.actionCommand = HomeSceneController.MOVE_TEST_BATTLE_C
        moveRankBattleButton.size = moveRankBattleButtonSize
        moveRankBattleButton.text  = moveRankBattleButtonText
        moveRankBattleButton.addActionListener(HomeSceneController)
        moveRankBattleButton.setLocation(moveRankBattleButtonX,moveRankBattleButtonY)

        //moveCasualBattleButtonの設定
        moveCasualBattleButton.actionCommand = HomeSceneController.MOVE_TEST_BATTLE_S
        moveCasualBattleButton.size = moveCasualBattleButtonSize
        moveCasualBattleButton.text  = moveCasualBattleButtonText
        moveCasualBattleButton.addActionListener(HomeSceneController)
        moveCasualBattleButton.setLocation(moveCasualBattleButtonX,moveCasualBattleButtonY)

        //viewの配置
        add(moveTitleButton)
        add(moveRankBattleButton)
        add(moveCasualBattleButton)
    }
}