package application.controller

import application.view.Scene
import application.view.Window
import java.awt.event.ActionListener

abstract class Controller : ActionListener{
    abstract val scene: Scene

    open fun start() {
        Window.contentPane = scene
    }
}