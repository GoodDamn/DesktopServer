package good.damn.filesharing.configs

import good.damn.filesharing.services.smtp.SMTPAuth
import java.io.File
import java.util.Properties

class SMTPConfig(
    val auth: SMTPAuth,
    val properties: Properties
) {
    companion object {
        fun createFromFile(
            file: File
        ): SMTPConfig? {

            val config = BaseConfig
                .createFromFile(file) ?: return null

            val email = config.map["email"] ?: return null
            val password = config.map["password"] ?: return null

            val hostMail = config.map["mail.smtp.host"] ?: return null
            val hostSmtp = config.map["mail.smtp.socketFactory.port"] ?: return null
            val smtpAuth = config.map["mail.smtp.auth"] ?: return null
            val smtpPort = config.map["mail.smtp.port"] ?: return null

            val auth = SMTPAuth(
                email,
                password
            )

            val props = Properties()
            props["mail.smtp.host"] = hostMail
            props["mail.smtp.socketFactory.port"] = hostSmtp
            props["mail.smtp.auth"] = smtpAuth
            props["mail.smtp.port"] = smtpPort
            props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory";

            return SMTPConfig(
                auth,
                props
            )
        }
    }
}