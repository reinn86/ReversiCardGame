package application.controller

import application.view.Window
import application.view.setting.SettingScene
import domain.model.ApplicationEnvironment
import java.awt.event.ActionEvent

object SettingController : Controller(){
    //このコントローラーで受け取る処理命令
    const val MOVE_TITLE = "MOVE_TITLE"
    const val CHANGE_RESOLUTION = "CHANGE_RESOLUTION"

    //scene
    override val scenePanel = SettingScene()

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.actionCommand) {
            MOVE_TITLE -> {
                moveScene(TitleController)
            }
            CHANGE_RESOLUTION -> {
                ApplicationEnvironment.appResolution = scenePanel.getSelectResolution()
                Window.changeResolution(ApplicationEnvironment.appResolution.toDimension())
                SettingController.resize()
                TitleController.resize()
                HomeController.resize()
            }
        }
    }

    @Override
    override fun start() {
        startScene()
    }
}
