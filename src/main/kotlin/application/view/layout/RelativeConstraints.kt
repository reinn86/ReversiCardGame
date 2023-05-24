package application.view.layout

import java.io.Serializable

class RelativeConstraints : Cloneable,Serializable {
    var x = 0.0
    var y = 0.0
    var width = 1.0
    var height = 1.0

    constructor() {
        RelativeConstraints(0.0,0.0,1.0,1.0)
    }

    constructor(x:Double, y:Double, width:Double, height:Double) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    public override fun clone(): Any {
        try {
            return super.clone()
        } catch (ex: CloneNotSupportedException) {
            ex.stackTrace
        }
        return super.clone()
    }
}