package domain.service

import com.moandjiezana.toml.Toml
import java.io.File
import java.io.FileInputStream

class Configure {
    fun createConfigFile() {
        val path = "./src/main/resources/config.toml"
        val inputStream = FileInputStream(File(path))
        val toml = Toml().read(inputStream)
        println(toml.getString("userName"))
//        val file = File("./src/main/resources/config.toml")
//        val files  = file.listFiles()
//        for (i in files!!) {
//            println(i)
//        }
    }
}