package application.controller

import application.view.Window
import application.view.title.TitleView
import domain.model.Resolution

object TitleController {
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

    fun test() {
        println("test")
    }
}