
import application.controller.TitleController
import application.view.Window
import domain.service.Configurable

fun main() {
    Window.createWindow()
    TitleController.start()
    val test = Configurable()
    test.createConfigFile()
}