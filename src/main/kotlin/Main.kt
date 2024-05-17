package good.damn.filesharing

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

    val ftpConf = File(
        args[0]
    )

    val smtpConf = File(
        args[1]
    )

    val server = TCPServer(
        8080
    )

    server.start()
    println("Server started!")
}
