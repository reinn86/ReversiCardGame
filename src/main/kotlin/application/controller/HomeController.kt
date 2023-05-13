package application.controller

import application.view.SceneView
import application.view.Window
import application.view.home.HomeView
import java.awt.event.ActionEvent

object HomeController : Controller(){
    //views
    override val sceneView: SceneView = HomeView(appDimension)
    private val homeView = HomeView(appDimension)

    @Override
    override fun start() {
        Window.contentPane = homeView
        Window.validate()
        Window.repaint()
    }

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        TODO("Not yet implemented")
    }

    fun moveTitle() {
        TitleController.start()
    }
}