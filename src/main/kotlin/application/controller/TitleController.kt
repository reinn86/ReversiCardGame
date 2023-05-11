package application.controller

import application.view.Window
import application.view.title.TitleView
import domain.model.Resolution

object TitleController {
    const val OPEN_SETTING = "OPEN_SETTING"
    const val MOVE_HOME = "MOVE_HOME"
    /*
     * 初期化時にはウィンドウを生成する
     */
    init {
        //アプリの表示領域の大きさ関連の変数
        val appDimension = Resolution.WIDE_SVGA.toDimension()
        //views
        val window = Window(appDimension)
        val titleView = TitleView(appDimension)

        window.add(titleView)
        
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
         * TODO 親クラスを作ってそこに記述する
         * TODO 次のシーンの作成
         */
    }
}