package application.controller

import application.view.Panel
import application.view.scene.battle.Battle
import java.awt.event.ActionEvent

object BattleController : Controller() {

    //scene
    override val scene: Panel = Battle()

    override fun actionPerformed(e: ActionEvent?) {
        TODO("Not yet implemented")
    }
}