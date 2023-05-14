package application.controller

import application.view.Window
import application.view.home.HomeScene
import java.awt.event.ActionEvent

object HomeController : Controller(){
    //views
    override val sceneView = HomeScene()

    @Override
    override fun start() {
        startScene()
    }

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        TODO("Not yet implemented")
    }

    fun moveTitle() {
        TitleController.start()
    }
}