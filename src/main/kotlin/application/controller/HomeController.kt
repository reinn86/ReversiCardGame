package application.controller

import application.view.home.HomeScene
import java.awt.event.ActionEvent

object HomeController : Controller(){
    //このコントローラーで受け取る処理命令
    const val MOVE_TITLE = "MOVE_TITLE"

    //views
    override val sceneView = HomeScene()

    @Override
    override fun start() {
        startScene()
    }

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.actionCommand) {
            MOVE_TITLE -> {
                moveScene(TitleController)
            }
        }
    }
}