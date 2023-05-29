package application.view

import domain.model.ApplicationEnvironment
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.WindowConstants

object Window : JFrame() {
    /*
     * TODO モニターのサイズについて
     *  モニターのサイズを取得してウィンドウを最適なサイズにする
     */
    fun createWindow() {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null
        size = correctionSize(ApplicationEnvironment.appResolution.toDimension())
        title = "ReversiCardGame"

        setLocationRelativeTo(null)
        isVisible = true
    }

    /*
     * 解像度を変更する関数
     */
    fun changeResolution(dimension: Dimension) {
        size = correctionSize(dimension)
    }

    /*
     * 受け取ったDimensionをウィンドウのバーのサイズも加味した大きさに修正する関数
     */
    private fun correctionSize(dimension: Dimension): Dimension {
        return Dimension(dimension.width + 16,dimension.height + 38)
    }
}