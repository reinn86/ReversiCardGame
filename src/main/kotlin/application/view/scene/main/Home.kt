package application.view.scene.main

import application.controller.HomeController
import application.view.Panel
import java.awt.Dimension
import javax.swing.JButton

class Home : Panel() {
    //views
    private val moveTitleButton = JButton()
    private val moveCasualBattleButton = JButton()
    private val moveRankBattleButton = JButton()

    init {
//        //moveTitleButtonの設定
        moveTitleButton.actionCommand = HomeController.MOVE_TITLE
        moveTitleButton.size = Dimension(vw(10.0),vw(10.0))
        moveTitleButton.addActionListener(HomeController)
        moveTitleButton.setLocation(vw(0.0),vh(0.0))

        //moveRankBattleButtonの設定
        moveRankBattleButton.actionCommand = HomeController.MOVE_RANK_BATTLE
        moveRankBattleButton.size = Dimension(vw(90.0),vh(10.0))
        moveRankBattleButton.text  = "バトルテスト c"
        moveRankBattleButton.addActionListener(HomeController)
        moveRankBattleButton.setLocation(vw(5.0),vh(60.0))

        //moveCasualBattleButtonの設定
        moveCasualBattleButton.actionCommand = HomeController.MOVE_BATTLE
        moveCasualBattleButton.size = Dimension(vw(90.0),vh(10.0))
        moveCasualBattleButton.text  = "バトルテスト s"
        moveCasualBattleButton.addActionListener(HomeController)
        moveCasualBattleButton.setLocation(vw(5.0),vh(80.0))

        //viewの配置
        add(moveTitleButton)
        add(moveRankBattleButton)
        add(moveCasualBattleButton)
    }
}