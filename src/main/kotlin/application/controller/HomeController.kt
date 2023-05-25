package application.controller

import application.view.scene.main.Home
import java.awt.event.ActionEvent

object HomeController : Controller(){
    //このコントローラーで受け取る処理命令
    const val MOVE_TITLE = "MOVE_TITLE"
    const val MOVE_BATTLE = "MOVE_BATTLE"

    //views
    override val scene = Home()

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.actionCommand) {
            MOVE_TITLE -> {
                changeController(TitleController)
            }
            MOVE_BATTLE -> {
                changeController(BattleController)
            }
        }
    }
}