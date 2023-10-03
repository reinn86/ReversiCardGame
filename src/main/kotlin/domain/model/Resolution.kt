package domain.model

import java.awt.Dimension

enum class Resolution(private val width: Int, private val height: Int) {
    WIDE_SVGA(480,854),
    aaa(960,1708),
    FULL_HIGH_DEFINITION(1080,1920);

    fun toDimension(): Dimension{
        return Dimension(width,height)
    }
}