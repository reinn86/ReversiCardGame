package application.controller

import application.view.title.TitleScene
import java.awt.Desktop
import java.awt.event.ActionEvent
import java.net.URI

object TitleController : Controller(){
    //このコントローラーで受け取る処理命令
    const val MOVE_SETTING = "OPEN_SETTING"
    const val MOVE_HOME = "MOVE_HOME"
    //scene
    override val sceneView = TitleScene()

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.actionCommand) {
            MOVE_HOME -> {
                test()
                moveScene(HomeController)
            }
            MOVE_SETTING -> {
                moveScene(SettingController)
            }
        }
    }

    @Override
    override fun start() {
        startScene()
    }

    private fun test() {
        val str = "https://accounts.google.com/o/oauth2/auth?response_type=code" +
                "&client_id=232804140396-l1uctpa0d9goikf3k4dft39c6imklns9.apps.googleusercontent.com" +
                "&redirect_uri=https://www.google.co.jp/" +
                "&scope=email" +
                "&access_type=offline&approval_prompt=force"


//        val url :URL = URL(str)
//        val cn : HttpURLConnection = url.openConnection() as HttpURLConnection
//        cn.requestMethod = "POST"
//        cn.connect()
//        println(cn.responseCode)
//        println(cn.responseMessage)
//        println(cn.url)
        val desktop = Desktop.getDesktop()
        println(System.getProperties())
        desktop.browse(URI(str))
    }
}
