package application.view

import domain.model.ApplicationEnvironment
import java.awt.Color
import javax.swing.JPanel

open class Panel : JPanel(){

    init {
        size = ApplicationEnvironment.appResolution.toDimension()
        background = Color.CYAN
        layout = RelativeLayout()
        isVisible = true
    }

    open fun vw(double: Double): Int {
        return (size.width * (double / 100.0)).toInt()
    }

    open fun vh(double: Double): Int {
        return (size.height * (double / 100.0)).toInt()
    }
}