package application.view.title

import application.controller.TitleController
import application.view.SceneView
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JLabel

class TitleView(dimension: Dimension) : SceneView(dimension),ActionListener{
    init {
        //views
        val settingButton = JButton()
        val startButton = JButton()
        val titleCallLabel = JLabel()

        //settingButtonの設定
        settingButton.size = Dimension(vw(15.0),vh(7.5))
        settingButton.text = "設定"
        settingButton.actionCommand = TitleController.OPEN_SETTING
        settingButton.addActionListener(this)
        settingButton.setLocation(vw(84.0),vh(1.0))
        //startButtonの設定
        startButton.isContentAreaFilled = false
        startButton.size = Dimension(vw(100.0),vh(75.0))
        startButton.text = "start"
        startButton.actionCommand = TitleController.MOVE_HOME
        startButton.addActionListener(this)
        startButton.setLocation(vw(0.0),vh(25.0))
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
    override fun actionPerformed(e: ActionEvent?) {
        TitleController.request(e?.actionCommand)
    }
}
