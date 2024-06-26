package good.damn.filesharing.configs

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class BaseConfig(
    val map: HashMap<String,String>
) {
    companion object {
        fun createFromFile(
            file: File
        ): BaseConfig {
            if (!file.exists()) {
                throw Exception("Config not exists: $file")
            }

            val inp = BufferedReader(
                InputStreamReader(
                    FileInputStream(
                        file
                    )
                )
            )

            val map = HashMap<String, String>()

            var s: String?
            while (true) {
                s = inp.readLine()
                if (s == null) {
                    break
                }

                if (s.isBlank()) {
                    continue
                }

                val eqin = s.indexOf("=")
                if (eqin == -1) {
                    continue
                }

                val key = s.substring(
                    0,eqin
                )

                val value = s.substring(
                    eqin+1
                )

                map[key] = value
            }

            return BaseConfig(
                map
            )
        }
    }
}