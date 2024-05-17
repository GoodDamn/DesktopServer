package good.damn.filesharing

import good.damn.filesharing.configs.BaseConfig
import good.damn.filesharing.configs.FTPConfig
import good.damn.filesharing.configs.SMTPConfig
import good.damn.filesharing.http.paths.HTTPPathActionEmail
import good.damn.filesharing.http.paths.HTTPPathDocument
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

    if (args.size < 3) {
        println("Define the path of documents.conf")
        return
    }

    try {
        Application.CONFIG_FTP = FTPConfig
            .createFromFile(File(args[0]))

        Application.CONFIG_SMTP = SMTPConfig
            .createFromFile(File(args[1]))

        Application.PATHS = hashMapOf(
            "email" to HTTPPathDocument(
                "email.html",
                "text/html"
            ),
            "email/send" to HTTPPathActionEmail()
        )

        val docConfig = BaseConfig
            .createFromFile(File(args[2]))

        docConfig.map.forEach {
            Application.PATHS[it.key] = HTTPPathDocument(
                it.value,
                "text/html"
            )
        }

        val server = TCPServer(
            8080
        )

        server.start()
        println("Server started!")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
