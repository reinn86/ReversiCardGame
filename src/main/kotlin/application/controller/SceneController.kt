package application.controller

import application.view.Panel
import application.view.Window
import application.view.Window.repaint
import application.view.Window.validate
import java.awt.event.ActionListener

abstract class SceneController : ActionListener{
    abstract val scene: Panel

    fun start() {
        Window.contentPane = scene
        validate()
        repaint()
    }

    fun changeController(sceneController: SceneController) {
        sceneController.start()
    }
}