package application.view.home

import application.controller.HomeController
import application.view.SceneAbstractPanel
import java.awt.Dimension
import javax.swing.JButton

class HomeScene : SceneAbstractPanel() {
    //views
    private val moveTitleButton = JButton()
    private val moveCasualBattleButton = JButton()
    private val moveRankBattleButton = JButton()

    init {
        //moveTitleButtonの設定
        moveTitleButton.actionCommand = HomeController.MOVE_TITLE
        moveTitleButton.addActionListener(HomeController)

        //moveRankBattleButtonの設定
        moveRankBattleButton.addActionListener(HomeController)

        //moveCasualBattleButtonの設定
        moveCasualBattleButton.addActionListener(HomeController)

        //viewの配置
        boundsComponent()
        add(moveTitleButton)
        add(moveRankBattleButton)
        add(moveCasualBattleButton)
    }

    @Override
    override fun boundsComponent() {
        moveTitleButton.size = Dimension(vw(10.0),vw(10.0))
        moveTitleButton.setLocation(vw(0.0),vh(0.0))
        moveRankBattleButton.size = Dimension(vw(90.0),vh(10.0))
        moveRankBattleButton.setLocation(vw(5.0),vh(60.0))
        moveCasualBattleButton.size = Dimension(vw(90.0),vh(10.0))
        moveCasualBattleButton.setLocation(vw(5.0),vh(80.0))
    }
}