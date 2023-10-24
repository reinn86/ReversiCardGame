
import application.controller.TitleSceneController
import application.view.Window
import javax.swing.SwingUtilities

class Main :Runnable{
    override fun run() {
        Window.createWindow()
        TitleSceneController.start()
    }
}
fun main() {
    SwingUtilities.invokeLater(Main())
}