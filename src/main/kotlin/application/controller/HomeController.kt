package application.controller

import application.view.scene.main.Home
import application.view.scene.main.MatchMake
import java.awt.event.ActionEvent

object HomeController : Controller(){
    //このコントローラーで受け取る処理命令
    const val MOVE_TITLE = "MOVE_TITLE"
    const val MOVE_TEST_BATTLE_S = "MOVE_BATTLE"
    const val MOVE_TEST_BATTLE_C = "MOVE_RANK_BATTLE"

    //scene
    override val scene = Home()
    private val matchMake = MatchMake()

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.actionCommand) {
            MOVE_TITLE -> {
                changeController(TitleController)
            }
            MOVE_TEST_BATTLE_S -> {
                changeController(BattleController)
                BattleController.connectServer()
            }
            MOVE_TEST_BATTLE_C -> {
                changeController(BattleController)
                BattleController.connectClient()
            }
        }
    }
}