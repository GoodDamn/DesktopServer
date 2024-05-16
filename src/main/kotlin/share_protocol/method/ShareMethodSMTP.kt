package good.damn.filesharing.share_protocol.method

import good.damn.filesharing.Application
import good.damn.filesharing.utils.ResponseUtils
import java.io.File
import java.io.OutputStream

class ShareMethodSMTP
: ShareMethod(
    "mail".toByteArray(
        Application.CHARSET_ASCII
    )
) {

    companion object {
        private const val TAG = "ShareMethodSMTP"
    }

    override fun makeResponse(
        os: OutputStream,
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    ) {

        var email = ""
        var subject = ""
        var body = ""

        if (argsCount >= 3) {
            var pos = argsPosition
            val emailLen = request[pos]
                .toInt()
            pos++
            email = String(
                request,
                pos,
                emailLen,
                Application.CHARSET_ASCII
            )

            pos += emailLen

            val subjectLen = request[pos]
                .toInt()

            pos++
            subject = String(
                request,
                pos,
                subjectLen,
                Application.CHARSET_ASCII
            )

            pos += subjectLen

            val bodyLen = request[pos]
                .toInt()

            pos++
            body = String(
                request,
                pos,
                bodyLen,
                Application.CHARSET_ASCII
            )

        } else {
            ResponseUtils.responseMessageId(
                os,
                "Not enough arguments. At least 3 (email, subject, body)"
            )
            return
        }

        if (argsCount >= 4) {
            // With attachment
        }

        ResponseUtils.responseMessageId(
            os,
            "Email sent"
        )
    }

}