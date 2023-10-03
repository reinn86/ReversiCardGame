package application.view

import domain.model.ApplicationEnvironment

interface Resizable {
    fun vw(percentage :Double) :Int{
        val appWidth = ApplicationEnvironment.appResolution.toDimension().width

        return (appWidth * (percentage / 100.0)).toInt()
    }

    fun vh(percentage :Double) :Int{
        val appHeight = ApplicationEnvironment.appResolution.toDimension().height

        return (appHeight * (percentage / 100.0)).toInt()
    }
}
