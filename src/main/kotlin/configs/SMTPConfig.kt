package good.damn.filesharing.configs

import good.damn.filesharing.services.smtp.SMTPAuth
import java.io.File
import java.util.Properties

class SMTPConfig(
    val auth: SMTPAuth,
    val properties: Properties,
) {
    companion object {
        fun createFromFile(
            file: File
        ): SMTPConfig {

            val config = BaseConfig
                .createFromFile(file)

            val email = config.map["email"]
                ?: throw IllegalStateException("SMTP: define 'email' for your sender")

            val password = config.map["password"]
                ?: throw IllegalStateException("SMTP: define 'password' for your email auth")

            val hostMail = config.map["mail.smtp.host"]
                ?: throw IllegalStateException("SMTP: define \"mail.smtp.host\" of your smtp provider")

            val hostPort = config.map["mail.smtp.port"]
                ?: throw IllegalStateException("SMTP: define \"mail.smtp.port\" of your smtp provider port")

            val auth = SMTPAuth(
                email,
                password
            )

            val props = Properties()
            props["mail.smtp.host"] = hostMail
            props["mail.smtp.socketFactory.port"] = hostPort
            props["mail.smtp.auth"] = "true"
            props["mail.smtp.port"] = hostPort
            props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory";

            return SMTPConfig(
                auth,
                props
            )
        }
    }
}