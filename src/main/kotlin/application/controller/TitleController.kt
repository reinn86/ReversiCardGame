package application.controller

import application.view.Window
import application.view.title.TitleView
import domain.model.Resolution

object TitleController {
    const val OPEN_SETTING = "OPEN_SETTING"
    const val MOVE_HOME = "MOVE_HOME"

    //アプリの表示領域の大きさ関連の変数
    private val appDimension = Resolution.WIDE_SVGA.toDimension()
    //views
//    private val window = Window(appDimension)
    private val titleView = TitleView(appDimension)

    /*
     * 初期化時にはウィンドウを生成する
     * TODO タイトルビューをウィンドウに追加するのは別メソッドでやる
     */
    init {
        Window.createWindow(appDimension)
        Window.contentPane = titleView
    }
    fun request(command: String?) {
        when(command) {
            OPEN_SETTING -> {
                openSetting()
            }
            MOVE_HOME -> {
                moveScene()
            }

        }
    }

    private fun openSetting() {
        /*
         * TODO 設定画面の作成
         */


    }

    private fun moveScene() {
        /*
         * TODO 次のシーンの作成
         */
        HomeController

    }
}