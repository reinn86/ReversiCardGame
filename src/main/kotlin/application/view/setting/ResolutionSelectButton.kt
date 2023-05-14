package application.view.setting

import domain.model.Resolution
import javax.swing.JComboBox

class ResolutionSelectButton : JComboBox<String>() {
    val resolutions = Resolution.values()

    init {
        for (i in Resolution.values()) {
            val dimension = i.toDimension()
            addItem("${dimension.width} × ${dimension.height}")
        }
    }
}