package application.view

import domain.model.ApplicationEnvironment
import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.LayoutManager2

class RelativeLayout : LayoutManager2{
    private var componentsMap = HashMap<Component, RelativeConstraints>()
    private val defaultConstraints = RelativeConstraints()

    override fun addLayoutComponent(comp: Component, constraints: Any) {
        if(constraints is  RelativeConstraints) {
            setConstraints(comp,constraints)
        } else {
            throw IllegalAccessException("レイアウトを追加できませんでした")
        }
    }

    override fun addLayoutComponent(name: String, comp: Component) {}

    override fun removeLayoutComponent(comp: Component) {
        componentsMap.remove(comp)
    }

    override fun getLayoutAlignmentX(target: Container): Float {
        return 0.5f
    }

    override fun getLayoutAlignmentY(target: Container): Float {
        return 0.5f
    }

    override fun invalidateLayout(target: Container) {}

    fun setConstraints(comp: Component, constraints: RelativeConstraints) {
        componentsMap[comp] = constraints.clone() as RelativeConstraints
    }

    fun getConstraints(comp: Component): RelativeConstraints {
        return lookupConstraints(comp).clone() as RelativeConstraints
    }

    private fun lookupConstraints(comp: Component): RelativeConstraints {
        var constraints = componentsMap[comp]
        if(constraints == null) {
            setConstraints(comp,defaultConstraints)
            constraints = componentsMap[comp]
        }
        return constraints as RelativeConstraints
    }

    override fun preferredLayoutSize(parent: Container): Dimension {
        return ApplicationEnvironment.appResolution.toDimension()
    }

    override fun maximumLayoutSize(target: Container): Dimension {
        return ApplicationEnvironment.appResolution.toDimension()
    }

    override fun minimumLayoutSize(parent: Container): Dimension {
        return ApplicationEnvironment.appResolution.toDimension()
    }

    override fun layoutContainer(parent: Container) {
        parent.size = ApplicationEnvironment.appResolution.toDimension()

        val comps = parent.components

        for(comp in comps) {
            val parentWidth = comp.width
            val parentHeight = comp.height
            val cons = lookupConstraints(comp)

            println(parent.size.height)
            val x = (parent.size.width * (cons.x / 100.0)).toInt()
            val y = (parent.size.height * (cons.y / 100.0)).toInt()
            val width = (parent.size.width * (cons.width / 100.0)).toInt()
            val height = (parent.size.width * (cons.height / 100.0)).toInt()
            comp.setBounds(x,y,width,height)
        }
    }
}