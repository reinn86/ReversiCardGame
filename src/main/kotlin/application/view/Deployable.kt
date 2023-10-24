package application.view

import domain.model.ApplicationEnvironment

interface Deployable {
    fun horizontalCenter(objWidth: Int) :Int{
        val appWidth = ApplicationEnvironment.appResolution.toDimension().width

        return (-objWidth + appWidth) / 2
    }

    fun verticalCenter(objHeight: Int) :Int{
        val appHeight = ApplicationEnvironment.appResolution.toDimension().height

        return (-objHeight + appHeight) / 2
    }
}