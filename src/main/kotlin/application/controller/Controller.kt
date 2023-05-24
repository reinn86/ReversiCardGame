package application.controller

import application.view.Panel
import application.view.Window
import java.awt.event.ActionListener

abstract class Controller : ActionListener{
    abstract val scene: Panel

    fun start() {
        Window.contentPane = scene
    }

    fun changeController(controller: Controller) {
        controller.start()
    }
}