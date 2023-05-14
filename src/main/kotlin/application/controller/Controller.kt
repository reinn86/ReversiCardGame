package application.controller

import application.view.SceneView
import application.view.Window
import java.awt.event.ActionListener

abstract class Controller : ActionListener{
    abstract val sceneView: SceneView
    abstract fun start()

    open fun startScene() {
        Window.contentPane = sceneView
        Window.validate()
        Window.repaint()
    }

    open fun resize() {
        sceneView.resize()
    }

    open fun moveScene(controller: Controller) {
        controller.start()
    }
}