package application.controller

class ConnectThread :Thread() {
    init {
        while (!MatchMakeSceneController.wsc.isConnect) {
            sleep(500)
        }
        if (MatchMakeSceneController.wsc.isConnect) {
//            changeController(BattleSceneController)
            BattleSceneController.start()
        }
    }
}