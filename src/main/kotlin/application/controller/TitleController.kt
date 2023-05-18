package application.controller

import application.view.title.WelcomeAbstractPanel
import domain.service.OAuthCertifiable
import java.awt.event.ActionEvent

object TitleController : Controller(),OAuthCertifiable {
    //このコントローラーで受け取る処理命令
    const val MOVE_SETTING = "OPEN_SETTING"
    const val MOVE_HOME = "MOVE_HOME"

    //scene
    override val scenePanel = WelcomeAbstractPanel()

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.actionCommand) {
            MOVE_HOME -> {
                authenticateGoogle()
//                moveScene(HomeController)
            }
            MOVE_SETTING -> {
                moveScene(SettingController)
            }
        }
    }

    @Override
    override fun start() {
        startScene()
    }
}
