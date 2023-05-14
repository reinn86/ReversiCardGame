package application.view.home

import application.controller.HomeController
import application.view.SceneView
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton

class HomeScene : SceneView(),ActionListener {
    private val moveTitleButton = JButton()

    init {
        moveTitleButton.addActionListener(this)
        boundsComponent()
        add(moveTitleButton)
    }

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        HomeController.moveTitle()
    }

    @Override
    override fun boundsComponent() {
        moveTitleButton.size = Dimension(vw(10.0),vw(10.0))
        moveTitleButton.setLocation(vw(0.0),vh(0.0))
    }

    @Override
    override fun resize() {
        super.resize()
        moveTitleButton.size = Dimension(vw(10.0),vw(10.0))
        moveTitleButton.setLocation(vw(0.0),vh(0.0))
    }
}