package application.view

import java.awt.Color
import java.awt.Dimension
import javax.swing.JPanel

open class SceneView(dimension: Dimension) :JPanel(){
    @JvmField
    val SCENE_WIDTH = dimension.width
    @JvmField
    val SCENE_HEIGHT = dimension.height

    init {
        size = dimension
        background = Color.CYAN
        layout = null
    }

    fun vw(double: Double): Int {
        return (SCENE_WIDTH * (double / 100.0)).toInt()
    }

    fun vh(double: Double): Int {
        return (SCENE_HEIGHT * (double / 100.0)).toInt()
    }
}