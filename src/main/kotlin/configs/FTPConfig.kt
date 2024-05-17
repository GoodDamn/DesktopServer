package good.damn.filesharing.configs

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class FTPConfig(
    val serverDir: File
) {
    companion object {
        fun createFromFile(
            file: File
        ): FTPConfig {

            val config = BaseConfig
                .createFromFile(file)

            val serverPath = config.map["server_dir"]
                ?: throw IllegalStateException("FTP: define 'server_dir' property with specific server path")

            val f = File(serverPath)
            if (!f.exists() && f.mkdirs()) {
                println("$serverPath folder is created")
            }

            return FTPConfig(
                f
            )
        }
    }
}