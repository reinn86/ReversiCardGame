
import application.controller.TitleController
import application.view.Window
import domain.service.Configure

fun main() {
    Window.createWindow()
    TitleController.start()
    val test = Configure()
    test.createConfigFile()
}