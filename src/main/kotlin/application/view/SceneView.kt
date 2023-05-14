package application.view

import domain.model.ApplicationEnvironment
import java.awt.Color
import javax.swing.JPanel

abstract class SceneView : JPanel() {
    init {
        size = ApplicationEnvironment.appResolution.toDimension()
        background = Color.CYAN
        layout = null
    }

    abstract fun boundsComponent()

    open fun resize() {
        size = ApplicationEnvironment.appResolution.toDimension()
        println(javaClass)
        for (i in components) {
            println(i)
        }

    }

    fun vw(double: Double): Int {
        return (size.width * (double / 100.0)).toInt()
    }

    fun vh(double: Double): Int {
        return (size.height * (double / 100.0)).toInt()
    }
}