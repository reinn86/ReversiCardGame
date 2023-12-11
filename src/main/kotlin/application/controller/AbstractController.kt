package application.controller

import application.view.Window
import application.view.Window.repaint
import application.view.Window.validate
import java.awt.event.ActionListener
import javax.swing.JPanel

abstract class AbstractController : ActionListener{
    abstract var mainPanel : JPanel

    abstract fun onStart()
    abstract fun onControllerChange()
    abstract fun onEnd()

    fun start() {
        Window.contentPane = mainPanel
        onStart()
        validate()
        repaint()
    }

    fun start(scene: JPanel) {
        Window.contentPane = scene
        onStart()
        validate()
        repaint()
    }
    fun st() {
        onStart()
        validate()
        repaint()
    }
    fun changeController(sceneController: AbstractController) {
        onControllerChange()
        sceneController.start()
    }
}