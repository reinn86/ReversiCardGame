package application.view

import domain.model.ApplicationEnvironment
import javax.swing.JPanel

abstract class AbstractPanel : JPanel() {
    fun vw(double: Double): Int {
        return (size.width * (double / 100.0)).toInt()
    }

    fun vh(double: Double): Int {
        return (size.height * (double / 100.0)).toInt()
    }
    abstract fun boundsComponent()

    open fun resize() {
        size = ApplicationEnvironment.appResolution.toDimension()
        boundsComponent()
        validate()
        repaint()
    }
}