package application.view.title.setting

import application.controller.TitleController
import application.view.Panel
import application.view.RelativeConstraints
import application.view.RelativeLayout
import domain.model.Resolution
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
        layout = RelativeLayout()
        //prevButtonの設定
        prevButton.actionCommand = TitleController.MOVE_TITLE
//        prevButton.size = Dimension(vw(15.0),vw(15.0))
        prevButton.text = "×"
        prevButton.addActionListener(TitleController)
//        prevButton.setLocation(vw(1.0),vw(1.0))
        val cons = RelativeConstraints()
        cons.x = 1.0
        cons.y = 1.0
        cons.width = 15.0
        cons.height = 15.0
        (layout as RelativeLayout).setConstraints(prevButton,cons)
        println((layout as RelativeLayout).getConstraints(prevButton).width)
//        //resolutionSelectTextの設定
//        resolutionSelectText.background = Color.CYAN
//        resolutionSelectText.isOpaque = true
//        resolutionSelectText.size = Dimension(vw(45.0),vh(10.0))
//        resolutionSelectText.text = "解像度"
//
//        resolutionSelectText.setLocation(vw(5.0),vh(10.0))
//
        //resolutionSelectButtonの設定
        resolutionSelectButton.actionCommand = TitleController.CHANGE_RESOLUTION
//        resolutionSelectButton.size = Dimension(vw(45.0),vh(10.0))
        for (i in resolutions) {
            val dimension = i.toDimension()
            val displayName = "${dimension.width} × ${dimension.height}"
            resolutionSelectButton.addItem(displayName)
        }
        resolutionSelectButton.addActionListener(TitleController)
//        resolutionSelectButton.setLocation(vw(50.0),vh(10.0))
        val cons2 = RelativeConstraints()
        cons2.x = 50.0
        cons2.y = 10.0
        cons2.width = 45.0
        cons2.height = 10.0

        //viewの配置
//        add(prevButton)
        add(prevButton,cons)
//        add(resolutionSelectText)
//        add(resolutionSelectButton)
        add(resolutionSelectButton,cons2)
    }

    fun getNewResolution(): Resolution {
        return resolutions[resolutionSelectButton.selectedIndex]
    }
}