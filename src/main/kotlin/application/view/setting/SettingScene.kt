package application.view.setting

import application.controller.SettingController
import application.view.SceneView
import domain.model.Resolution
import java.awt.Color
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JLabel

class SettingScene : SceneView() {
    //views
    private val prevButton = JButton()
    private val resolutionSelectText = JLabel()
    private val resolutionSelectButton = ResolutionSelectButton()

    init {
        //prevButtonの設定
        prevButton.actionCommand = SettingController.MOVE_TITLE
        prevButton.text = "戻る"
        prevButton.addActionListener(SettingController)

        //resolutionSelectTextの設定
        resolutionSelectText.background = Color.CYAN
        resolutionSelectText.isOpaque = true
        resolutionSelectText.text = "解像度"

        //resolutionSelectButtonの設定
        resolutionSelectButton.actionCommand = SettingController.CHANGE_RESOLUTION
        resolutionSelectButton.addActionListener(SettingController)

        //viewの配置
        boundsComponent()
        add(prevButton)
        add(resolutionSelectText)
        add(resolutionSelectButton)
    }

    fun getSelectResolution() : Resolution {
        return resolutionSelectButton.resolutions[resolutionSelectButton.selectedIndex]
    }

    @Override
    override fun boundsComponent() {
        prevButton.size = Dimension(vw(15.0),vw(15.0))
        resolutionSelectText.size = Dimension(vw(45.0),vh(10.0))
        resolutionSelectButton.size = Dimension(vw(45.0),vh(10.0))
        prevButton.setLocation(vw(1.0),vw(1.0))
        resolutionSelectText.setLocation(vw(5.0),vh(10.0))
        resolutionSelectButton.setLocation(vw(50.0),vh(10.0))
    }
}