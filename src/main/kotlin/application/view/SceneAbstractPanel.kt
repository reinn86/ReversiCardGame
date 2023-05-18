package application.view

import domain.model.ApplicationEnvironment
import java.awt.Color

abstract class SceneAbstractPanel : AbstractPanel(){
    
    init {
        size = ApplicationEnvironment.appResolution.toDimension()
        background = Color.CYAN
        layout = null
    }
}