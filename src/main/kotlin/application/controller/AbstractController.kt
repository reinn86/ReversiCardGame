package application.controller

import application.view.Window
import application.view.Window.repaint
import application.view.Window.validate
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JPanel

abstract class AbstractController : ActionListener{
    abstract var mainPanel : JPanel


    fun show(scene:JPanel) {
        Window.contentPane = scene
        validate()
        repaint()
    }
    fun start() {
        Window.contentPane = mainPanel
        validate()
        repaint()
    }

    fun start(scene: JPanel) {
        Window.contentPane = mainPanel
        validate()
        repaint()
    }

    fun changeController(sceneController: AbstractController) {
        sceneController.start()
    }
}