package good.damn.filesharing.services.smtp

import javax.mail.Authenticator
import javax.mail.PasswordAuthentication

class SMTPAuth(
    private val email: String,
    private val password: String
): Authenticator() {

    override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(
            email,
            password
        )
    }

}