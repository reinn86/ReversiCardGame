package application.view.welcome

import application.controller.TitleController
import application.view.Panel
import domain.model.Resolution
import java.awt.Color
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JLabel

class Setting : Panel(){
    //views
    private val prevButton = JButton()
    private val resolutionSelectText = JLabel()
    private val resolutionSelectButton = JComboBox<String>()
    //解像度リスト
    private val resolutions = Resolution.values()

    init {
        //prevButtonの設定
        prevButton.actionCommand = TitleController.MOVE_TITLE
        prevButton.size = Dimension(vw(11.1),vw(11.1))
        prevButton.text = "×"
        prevButton.addActionListener(TitleController)
        prevButton.setLocation(vw(1.0),vw(1.0))

        //resolutionSelectTextの設定
        resolutionSelectText.background = Color.CYAN
        resolutionSelectText.isOpaque = true
        resolutionSelectText.size = Dimension(vw(45.0),vh(10.0))
        resolutionSelectText.text = "解像度"
        resolutionSelectText.setLocation(vw(5.0),vh(10.0))

        //resolutionSelectButtonの設定
        resolutionSelectButton.actionCommand = TitleController.CHANGE_RESOLUTION
        resolutionSelectButton.size = Dimension(vw(45.0),vh(10.0))
        for (i in resolutions) {
            val dimension = i.toDimension()
            val displayName = "${dimension.width} × ${dimension.height}"
            resolutionSelectButton.addItem(displayName)
        }
        resolutionSelectButton.addActionListener(TitleController)
        resolutionSelectButton.setLocation(vw(50.0),vh(10.0))

        //viewの配置
        add(prevButton)
        add(resolutionSelectText)
        add(resolutionSelectButton)
    }

    fun getNewResolution(): Resolution {
        return resolutions[resolutionSelectButton.selectedIndex]
    }
}