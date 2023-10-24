package application.view

import application.view.layout.RelativeLayout
import domain.model.ApplicationEnvironment
import java.awt.Color
import javax.swing.JPanel

open class Panel : JPanel(),Resizable,Deployable{

    init {
        size = ApplicationEnvironment.appResolution.toDimension()
        background = Color.CYAN
        layout = RelativeLayout()
        isOpaque = true
        isVisible = true
    }
}