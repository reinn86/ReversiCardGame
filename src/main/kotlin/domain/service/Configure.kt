package domain.service

import com.moandjiezana.toml.Toml
import domain.model.Resolution
import java.io.File
import java.io.FileInputStream
import java.lang.Class.forName

class Configure {
    val path = "./src/main/resources/config.toml"
    val inputStream = FileInputStream(File(path))
    val toml = Toml().read(inputStream)

    private val resolution = getResolution()

    fun createConfigFile() {
        val tes = forName(getResolution())
        println(Resolution.values().forEach { it.name })
        val test = getByName("WIDE_SVGA")
        if (test != null) {
            println(test.toDimension().width)
        }
//        println(tes)
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

//    fun write(attr: String, any: Any): Boolean {
//        if() {
//           toml.entrySet()
//        }
//        return false
//    }

    fun getResolution(): String {
        return toml.getString("resolution")
    }
}