package domain.service

import com.moandjiezana.toml.Toml
import domain.model.Resolution
import java.io.FileInputStream
import java.lang.Class.forName

/*
 * TODO いんたーふぇーすにしたほうがいいかも
 */
interface Configurable {
    val path: String
    val inputStream: FileInputStream
    val toml: Toml

    fun createConfigFile() {
        val tes = forName(getResolution())
        println(Resolution.values().forEach { it.name })
        val test = getByName("WIDE_SVGA")
        if (test != null) {
            println(test.toDimension().width)
        }
    }

    fun getByName(name: String) : Resolution? {
        Resolution.values().forEach {
            println(it)
            if(name == it.toString()) {
                return it
            }
        }
        return null
    }

    fun getResolution(): String {
        return toml.getString("resolution")
    }
}