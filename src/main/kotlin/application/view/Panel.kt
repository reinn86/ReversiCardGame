package application.view

import application.view.layout.RelativeLayout
import domain.model.ApplicationEnvironment
import java.awt.Color
import javax.swing.JPanel

open class Panel : JPanel(),Resizable{

    init {
        size = ApplicationEnvironment.appResolution.toDimension()
        background = Color.CYAN
        layout = RelativeLayout()
        isOpaque = true
        isVisible = true
    }

//    open fun vw(double: Double): Int {
//        return (size.width * (double / 100.0)).toInt()
//    }
//
//    open fun vh(double: Double): Int {
//        return (size.height * (double / 100.0)).toInt()
//    }
}