package application.controller

import application.view.scene.main.Home
import domain.model.network.Client
import domain.model.network.Server
import java.awt.event.ActionEvent

object HomeController : Controller(){
    //このコントローラーで受け取る処理命令
    const val MOVE_TITLE = "MOVE_TITLE"
    const val MOVE_BATTLE = "MOVE_BATTLE"
    const val MOVE_RANK_BATTLE = "MOVE_RANK_BATTLE"

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
                val thread = Thread() {
                    run {
                        val s = Server()
                    }
                }

            }
            MOVE_RANK_BATTLE -> {
                changeController(BattleController)
                val c = Client()
            }
        }
    }
}