package domain.service.reversi

import application.controller.BattleSceneController
import application.controller.BattleSceneController.autoPut
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

class Timer {
    private var time = 20
    var isTimeCome = false
    private var now = 0L
    val remainingTime
        get() = time - now
    private var startTime = System.nanoTime()

    val timer
        get()= Thread {
        println("timer start")
        run {
            while (!isTimeCome) {
                now = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime)
//                println(now)
                BattleSceneController.battleScene.timerLabel.text = BattleSceneController.t.remainingTime.toString()
                if (now >= time) {
                    isTimeCome = true
                    autoPut()
                }
                sleep(500)
            }
        }
    }

    fun reset() {
        println("timer reset")
        BattleSceneController.battleScene.timerLabel.text = "20"
        isTimeCome = false
        startTime = System.nanoTime()
    }



    fun setTime(time: Int) {
        this.time = time
    }
}