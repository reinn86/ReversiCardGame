package application.controller

import application.view.SceneView
import domain.model.ApplicationEnvironment
import java.awt.event.ActionListener

abstract class Controller : ActionListener{
    //アプリの表示領域の大きさ関連の変数
    open val appDimension = ApplicationEnvironment().appDimension
    //コントローラーが扱うシーンビュー
    abstract val sceneView : SceneView

    abstract fun start()
}