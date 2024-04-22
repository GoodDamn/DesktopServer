package good.damn.filesharing.services

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

class RuntimeService {

    var delegate: Void? = null

    private val mRuntime = Runtime
        .getRuntime()

    fun start(
        executionLine: Array<String>
    ): String {
        val process = mRuntime.exec(
            executionLine
        )

        val inp = process.inputStream
        val err = process.errorStream

        val reader = BufferedReader(
            InputStreamReader(
                inp
            )
        )

        val readerErr = BufferedReader(
            InputStreamReader(
                err
            )
        )

        var output = ""

        var line: String
        while(true) {
            line = reader.readLine()
                ?: break

            output += "$line\n"
        }

        while(true) {
            line = readerErr.readLine()
                ?: break
            output += "$line\n"
        }

        process.waitFor()

        return output
    }
}