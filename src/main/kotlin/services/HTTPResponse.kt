package good.damn.filesharing.services

import good.damn.filesharing.http.HTTPHeaders
import good.damn.filesharing.http.HTTPPath
import good.damn.filesharing.http.paths.HTTPPathActionEmail
import good.damn.filesharing.http.paths.HTTPPathDocument
import java.io.OutputStream

class HTTPResponse {
    companion object {
        private const val TAG = "HTTPResponseManager"
        private val PATHS = hashMapOf(
            "email" to HTTPPathDocument(
                "email.html",
                "text/html"
            ),
            "email/send" to HTTPPathActionEmail()
        )

        fun set(
            to: OutputStream,
            httpPath: HTTPPath
        ) {
            PATHS[httpPath.path]?.execute(
                to,
                httpPath
            ) ?: {
                HTTPHeaders.error(
                    to
                )
            }
        }
    }
}