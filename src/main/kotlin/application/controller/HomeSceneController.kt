package application.controller

import application.view.scene.home.Home
import application.view.scene.matchmake.MatchMake
import java.awt.event.ActionEvent
import javax.swing.JPanel

object HomeSceneController : AbstractController(){
    //このコントローラーで受け取る処理命令
    const val MOVE_TITLE = "MOVE_TITLE"
    const val MOVE_TEST_BATTLE_S = "MOVE_CASUAL_BATTLE"
    const val MOVE_TEST_BATTLE_C = "MOVE_RANK_BATTLE"
    const val MOVE_BATTLE = "MOVE_BATTLE"

    //scene
    val scene = Home()
    override var mainPanel: JPanel = scene
    private val matchMake = MatchMake()
    override fun onStart() {}
    override fun onEnd() {}
    override fun onControllerChange() {}

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.actionCommand) {
            MOVE_TITLE -> {
                changeController(TitleSceneController)
            }
//            MOVE_TEST_BATTLE_S -> {
//                //TODO ホストとクライアントを自動で決める機能の作成
//                changeController(BattleSceneController)
////                BattleSceneController.connectServer()
//            }
//            MOVE_TEST_BATTLE_C -> {
//                changeController(BattleSceneController)
////                BattleSceneController.connectClient()
//            }
            MOVE_BATTLE -> {
                changeController(MatchMakeSceneController)
            }
        }
    }
}