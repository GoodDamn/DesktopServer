package good.damn.filesharing.services.smtp

import javax.mail.Authenticator
import javax.mail.PasswordAuthentication

class SMTPAuth(
    val email: String,
    val password: String
): Authenticator() {

    override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(
            email,
            password
        )
    }

}