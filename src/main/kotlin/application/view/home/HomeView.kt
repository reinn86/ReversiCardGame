package application.view.home

import application.controller.HomeController
import application.view.SceneView
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton

class HomeView(dimension: Dimension) : SceneView(dimension),ActionListener {
    init {
        val moveTitleButton = JButton()

        moveTitleButton.size = Dimension(vw(10.0),vw(10.0))
        moveTitleButton.setLocation(vw(0.0),vh(0.0))

        add(moveTitleButton)
    }

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        HomeController.moveTitle()
    }

    init {
        background = Color.BLUE
    }
}