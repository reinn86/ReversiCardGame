package domain.model

object ApplicationEnvironment {
    var appResolution = Resolution.WIDE_SVGA
    var bgmVolume = 0
    var seVolume = 0

    /*
     * 入力されたbgm音量の値が正しいかチェック
     */
    fun bgmVolumeCheck(bgmVolume: Int): Boolean {
        return bgmVolume in 0..100
    }

    /*
     * 入力されたse音量が正しいかチェック
     */
    fun seVolumeCheck(seVolume: Int): Boolean {
        return seVolume in 0 .. 100
    }

    fun changeAppResolution(resolution: Resolution) {
        /*
         * TODO 設定ファイルに変更を書き込むようにする
         */
        appResolution = resolution
    }
}