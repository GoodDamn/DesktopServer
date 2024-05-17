package good.damn.filesharing.configs

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class FTPConfig(
    serverDir: File?,
    error: String? = null
) {
    companion object {
        fun createFromFile(
            file: File
        ): FTPConfig {

            val config = BaseConfig
                .createFromFile(file)

            if (config.error != null) {
                return FTPConfig(
                    null,
                    "FTP: ${config.error}"
                )
            }

            if (config.map == null) {
                return FTPConfig(
                    null,
                    "FTP: Map is null"
                )
            }

            val serverPath = config.map["server_dir"]
                ?: return FTPConfig(
                    null,
                    "FTP: define 'server_dir' property with specific server path"
                )

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