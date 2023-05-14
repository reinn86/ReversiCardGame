package application.view

import domain.model.ApplicationEnvironment
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.WindowConstants

object Window : JFrame() {
    /*
     * TODO モニターのサイズについて
     *  モニターのサイズを取得してウィンドウを最適なサイズにする
     * TODO サイズ変更に関して
     *  設定画面などで変えられるようにする必要があり、
     *  またそれに関連してサイズを大きくしすぎたときの処理
     *  (画面の上部を真ん中に持ってくるなど)をする必要がある
     */
    fun createWindow() {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        isVisible = true
        size = correctionSize(ApplicationEnvironment.appResolution.toDimension())
        title = "ReversiCardGame"

        setLocationRelativeTo(null)
    }

    fun changeResolution(dimension: Dimension) {
        size = correctionSize(dimension)
    }

    /*
     * 受け取ったDimensionをウィンドウのバーのサイズも加味したDimensionに修正する関数
     */
    private fun correctionSize(dimension: Dimension): Dimension {
        return Dimension(dimension.width + 16,dimension.height + 38)
    }
}