package application.controller

import application.view.Window
import application.view.Window.repaint
import application.view.Window.validate
import java.awt.event.ActionListener
import javax.swing.JPanel

abstract class SceneController : ActionListener{
    abstract var ascene : JPanel

    fun start() {
//        ascene = scene
        Window.contentPane = ascene
        validate()
        repaint()
    }

    fun start(scene: JPanel) {
        Window.contentPane = ascene
        validate()
        repaint()
    }

    fun changeController(sceneController: SceneController) {
        sceneController.start()
    }

    //todo コマンド受け取りの処理は共通なのでオーバライドさせるように作るといいかも
}