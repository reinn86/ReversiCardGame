package application.controller

import application.view.Scene
import application.view.Window
import application.view.title.setting.Setting
import application.view.title.welcom.Welcome
import domain.model.ApplicationEnvironment
import domain.service.OAuthCertifiable
import java.awt.event.ActionEvent

object TitleController : Controller(),OAuthCertifiable {
    //このコントローラーで受け取る処理命令
    const val MOVE_SETTING = "OPEN_SETTING"
    const val MOVE_HOME = "MOVE_HOME"
    const val MOVE_TITLE = "MOVE_TITLE"
    const val CHANGE_RESOLUTION = "CHANGE_RESOLUTION"

    //scene
    override val scene = Scene()
    private val welcome = Welcome()
    private val setting = Setting()
//    private val login = Login()

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.actionCommand) {
            MOVE_HOME -> {
                authenticateGoogle()
//                moveScene(HomeController)
            }
            MOVE_SETTING -> {
                scene.next()
            }
            MOVE_TITLE -> {
                scene.first()
            }
            CHANGE_RESOLUTION -> {
                val newResolution = setting.getNewResolution()

                ApplicationEnvironment.changeAppResolution(newResolution)
                Window.changeResolution(newResolution.toDimension())
            }
        }
    }

    @Override
    override fun start() {
        super.start()
        scene.add(welcome)
        scene.add(setting)
        scene.first()
    }
}
