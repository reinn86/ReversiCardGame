package application.view.layout

import domain.model.ApplicationEnvironment
import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.LayoutManager2

class RelativeLayout : LayoutManager2{
    private var componentsMap = HashMap<Component, RelativeConstraints>()
    private val defaultConstraints = RelativeConstraints()

    override fun addLayoutComponent(comp: Component, constraints: Any?) {
        val app = ApplicationEnvironment.appResolution.toDimension()
        val cons  = RelativeConstraints()

        cons.x = (comp.x.toDouble() / (app.width.toDouble() / 9.0))
        cons.y = (comp.y.toDouble() / (app.height.toDouble() / 16.0))
        cons.width = (comp.width.toDouble() / (app.width.toDouble() / 9.0))
        cons.height = (comp.height.toDouble() / (app.height.toDouble() / 16.0))

        if(constraints is RelativeConstraints) {
            setConstraints(comp,constraints)
        } else {
            setConstraints(comp,cons)
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

    private fun setConstraints(comp: Component, constraints: RelativeConstraints) {
        componentsMap[comp] = constraints.clone() as RelativeConstraints
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
            val cons = lookupConstraints(comp)
            val x = (parent.size.width * (cons.x / 9.0)).toInt()
            val y = (parent.size.height * (cons.y / 16.0)).toInt()
            val width = (parent.size.width * (cons.width / 9.0)).toInt()
            val height = (parent.size.height * (cons.height / 16.0)).toInt()
            comp.setBounds(x,y,width,height)
        }
    }
}