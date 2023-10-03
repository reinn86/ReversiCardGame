package application.controller

import application.view.Window
import application.view.scene.welcome.Setting
import application.view.scene.welcome.Welcome
import domain.model.ApplicationEnvironment
import domain.service.OAuthCertifiable
import java.awt.event.ActionEvent
import javax.swing.JPanel

object TitleSceneController : SceneController(),OAuthCertifiable {
    //このコントローラーで受け取る処理命令
    const val MOVE_SETTING = "OPEN_SETTING"
    const val MOVE_HOME = "MOVE_HOME"
    const val MOVE_TITLE = "MOVE_TITLE"
    const val CHANGE_RESOLUTION = "CHANGE_RESOLUTION"

    //scene
    val scene = Welcome()
    override var ascene: JPanel = scene
    /*
     * TODO 本来はシーンパネルの中に入ってるべきなので修正
     */
    private val setting = Setting()

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.actionCommand) {
            MOVE_HOME -> {
//                authenticateGoogle()
                changeController(HomeSceneController)
            }
            MOVE_SETTING -> {
                Window.contentPane = setting
            }
            MOVE_TITLE -> {
                Window.contentPane = scene
            }
            CHANGE_RESOLUTION -> {
                /*
                 * TODO そもそもこのコマンドいらないから別の方法で実装
                 */
                val newResolution = setting.getNewResolution()

                ApplicationEnvironment.changeAppResolution(newResolution)
                Window.changeResolution(newResolution.toDimension())
            }
        }
    }
}
