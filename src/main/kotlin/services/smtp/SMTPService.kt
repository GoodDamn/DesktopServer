package good.damn.filesharing.services.smtp

import good.damn.filesharing.configs.SMTPConfig
import good.damn.filesharing.utils.Log
import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SMTPService(
    private val mConfig: SMTPConfig
) {

    companion object {
        private const val TAG = "SMTPService"

        //private const val EMAIL_COMMON = "vuzion@yandex.ru"
        //private const val PASSWORD = "zaqwsxEdC1"
    }

    private val mFromInetAddress = InternetAddress(
        mConfig.auth.email
    )

    fun send(
        to: String,
        subject: String,
        body: String
    ) {
        Thread {
            val session = Session.getDefaultInstance(
                mConfig.properties,
                mConfig.auth
            )

            try {

                val msg = MimeMessage(session)
                msg.setFrom(mFromInetAddress)

                msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
                )

                msg.subject = subject

                msg.setText(
                    body
                )

                Transport.send(
                    msg
                )
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: EXCEPTION: ${e.message}")
            }
        }.start()
    }
}