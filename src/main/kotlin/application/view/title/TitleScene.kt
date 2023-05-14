package application.view.title

import application.controller.TitleController
import application.view.SceneView
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JLabel

class TitleScene : SceneView(){
    //views
    private val settingButton = JButton()
    private val startButton = JButton()
    private val titleCallLabel = JLabel()

    init {

        //settingButtonの設定
        settingButton.text = "設定"
        settingButton.actionCommand = TitleController.MOVE_SETTING
        settingButton.addActionListener(TitleController)
        //startButtonの設定
        startButton.isContentAreaFilled = false
        startButton.text = "start"
        startButton.actionCommand = TitleController.MOVE_HOME
        startButton.addActionListener(TitleController)
        //titleCallLabelの設定
        titleCallLabel.isOpaque = true
        titleCallLabel.text = "Reversi Card Game"
        boundsComponent()
        //viewの配置
        add(settingButton)
        add(titleCallLabel)
        add(startButton)
    }

    @Override
    override fun boundsComponent() {
        settingButton.size = Dimension(vw(15.0),vh(7.5))
        settingButton.setLocation(vw(84.0),vh(1.0))
        startButton.size = Dimension(vw(100.0),vh(75.0))
        startButton.setLocation(vw(0.0),vh(25.0))
        titleCallLabel.size = Dimension(vw(80.0),vh(20.0))
        titleCallLabel.setLocation(vw(10.0),vh(15.0))
    }

    @Override
    override fun resize() {
        super.resize()
        boundsComponent()
        validate()
        repaint()
    }
}
