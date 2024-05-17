package good.damn.filesharing.configs

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class FTPConfig(
    serverDir: File?
) {
    companion object {
        fun createFromFile(
            file: File
        ): FTPConfig? {

            val config = BaseConfig
                .createFromFile(file)
                ?: return null

            val serverPath = config.map["server_dir"]
                ?: return null

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