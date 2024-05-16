package good.damn.filesharing.http.paths

import com.google.gson.Gson
import good.damn.filesharing.http.HTTPHeaders
import good.damn.filesharing.http.HTTPPath
import good.damn.filesharing.http.paths.json_models.HTTPModelEmail
import good.damn.filesharing.services.smtp.SMTPService
import good.damn.filesharing.utils.Log
import java.io.OutputStream

class HTTPPathActionEmail
: HTTPPathAction() {

    companion object {
        private const val TAG = "HTTPPathActionEmail"
    }

    private val mSmtpService = SMTPService()
    private val mGson = Gson()

    override fun execute(
        to: OutputStream,
        httpPath: HTTPPath
    ) {
        try {
            val payload = mGson.fromJson(
                httpPath.json,
                HTTPModelEmail::class.java
            )

            mSmtpService.send(
                payload.email,
                payload.subject,
                payload.body
            )
        } catch (jsonEx: Exception) {
            HTTPHeaders.error(
                to
            )
        }
    }

}