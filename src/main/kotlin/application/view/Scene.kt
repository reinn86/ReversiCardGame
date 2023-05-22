package application.view

import domain.model.ApplicationEnvironment
import java.awt.CardLayout
import javax.swing.JPanel

open class Scene : JPanel(){
    //レイアウト
    private val cardLayout: CardLayout = CardLayout()

    init {
        size = ApplicationEnvironment.appResolution.toDimension()
        layout = cardLayout
        isVisible = true
    }

    /*
     * pageを変える関数
     */
    open fun first() {
        cardLayout.first(this)
    }

    open fun next() {
        cardLayout.next(this)
    }
}