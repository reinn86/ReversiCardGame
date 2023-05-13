package domain.model

class ApplicationEnvironment {
    val appDimension = Resolution.WIDE_SVGA.toDimension()
    val bgmVolume = 0
    val seVolume = 0

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
}