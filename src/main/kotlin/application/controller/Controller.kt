package application.controller

import application.view.SceneAbstractPanel
import application.view.Window
import java.awt.event.ActionListener

abstract class Controller : ActionListener{
    abstract val scenePanel: SceneAbstractPanel
    abstract fun start()

    open fun startScene() {
        Window.contentPane = scenePanel
        Window.validate()
        Window.repaint()
    }

    open fun resize() {
        scenePanel.resize()
    }

    open fun moveScene(controller: Controller) {
        controller.start()
    }
}