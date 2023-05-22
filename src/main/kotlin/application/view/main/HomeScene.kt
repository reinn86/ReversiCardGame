package application.view.main

import application.view.Scene
import application.view.main.home.Home

class HomeScene : Scene(){
    private val home = Home()

    init {
        add(home)
    }
}