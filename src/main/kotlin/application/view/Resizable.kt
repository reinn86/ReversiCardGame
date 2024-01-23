package application.view

import domain.model.ApplicationEnvironment

interface Resizable {
    fun vw(percentage :Double) :Int{
        val appWidth = ApplicationEnvironment.appResolution.toDimension().width

        return vw(appWidth,percentage)
    }

    fun vw(width:Int,percentage :Double):Int {
        return (width * (percentage / 100.0)).toInt()
    }

    fun vh(percentage :Double) :Int{
        val appHeight = ApplicationEnvironment.appResolution.toDimension().height

        return (appHeight * (percentage / 100.0)).toInt()
    }

    fun vh(height:Int,percentage :Double):Int {
        return  (height * (percentage / 100.0)).toInt()
    }
}
