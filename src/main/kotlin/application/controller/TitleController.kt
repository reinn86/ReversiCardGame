package application.controller

import application.view.Window
import application.view.title.TitleView
import java.awt.Desktop
import java.awt.event.ActionEvent
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

object TitleController : Controller(){
    /*
     * このコントローラーで受け取る処理命令
     */
    const val OPEN_SETTING = "OPEN_SETTING"
    const val MOVE_HOME = "MOVE_HOME"

    override val sceneView = TitleView(appDimension)

    /*
     * 初期化時にはウィンドウを生成する
     */
    init {
        Window.createWindow(appDimension)
        start()
    }

    @Override
    override fun actionPerformed(e: ActionEvent?) {
        when(e?.actionCommand) {
            MOVE_HOME -> {
                moveHomeScene()
            }
            OPEN_SETTING -> {
                openSetting()
            }
        }
    }

    @Override
    override fun start() {
        Window.contentPane = sceneView
        Window.validate()
        Window.repaint()
    }

    private fun openSetting() {
        /*
         * TODO 設定画面の作成
         */
    }

    private fun moveHomeScene() {
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

//        HomeController.start()
    }
}
