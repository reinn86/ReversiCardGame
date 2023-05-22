package application.controller

import application.view.main.home.Home
import java.awt.event.ActionEvent

object HomeController : Controller(){
    //このコントローラーで受け取る処理命令
    const val MOVE_TITLE = "MOVE_TITLE"

    //views
    override val scene = Home()

    @Override
    override fun actionPerformed(e: ActionEvent?) {
    }
}