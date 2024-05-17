package good.damn.filesharing

import good.damn.filesharing.configs.FTPConfig
import good.damn.filesharing.configs.SMTPConfig
import good.damn.filesharing.servers.TCPServer
import java.io.File

fun main(
    args: Array<String>
) {

    if (args.isEmpty()) {
        println("Define the path of ftp.conf")
        return
    }

    if (args.size < 2) {
        println("Define the path of smtp.conf")
        return
    }

    try {
        val ftpConf = FTPConfig
            .createFromFile(File(args[0]))

        val smtpConf = SMTPConfig
            .createFromFile(File(args[1]))

        val server = TCPServer(
            8080
        )

        server.start()
        println("Server started!")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
