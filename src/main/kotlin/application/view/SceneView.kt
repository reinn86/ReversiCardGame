package application.view

import domain.model.ApplicationEnvironment
import java.awt.Color
import javax.swing.JPanel

abstract class SceneView : JPanel(){
    
    init {
        size = ApplicationEnvironment.appResolution.toDimension()
        background = Color.CYAN
        layout = null
    }

    /*
     * TODO 将来的にはboundsComponent()は使わずにリサイズできるようにしたい
     */
    abstract fun boundsComponent()

    open fun resize() {
        size = ApplicationEnvironment.appResolution.toDimension()
        boundsComponent()
        validate()
        repaint()
    }

    fun vw(double: Double): Int {
        return (size.width * (double / 100.0)).toInt()
    }

    fun vh(double: Double): Int {
        return (size.height * (double / 100.0)).toInt()
    }
}