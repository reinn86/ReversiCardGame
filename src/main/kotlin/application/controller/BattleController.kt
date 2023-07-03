package application.controller

import application.view.Panel
import application.view.scene.battle.Battle
import java.awt.event.ActionEvent

object BattleController : Controller() {
    const val MOVE_SETTING = "PUT_STONE"


    //scene
    override val scene: Panel = Battle()

    override fun actionPerformed(e: ActionEvent?) {
        println("test")
    }

}