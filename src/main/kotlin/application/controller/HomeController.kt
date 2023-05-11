package application.controller

import application.view.Window
import application.view.home.HomeView
import domain.model.Resolution

object HomeController {
    //アプリの表示領域の大きさ関連の変数
    private val appDimension = Resolution.WIDE_SVGA.toDimension()
    //views
    private val homeView = HomeView(appDimension)

    init {

        println("test")
        Window.contentPane = homeView
        Window.validate()
        Window.repaint()
    }
}