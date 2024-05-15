package good.damn.filesharing.services

import good.damn.filesharing.http.HTTPHeaders
import good.damn.filesharing.http.HTTPPath
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
            "email/send" to HTTPPathDocument(
                "e.html",
                "text/html"
            )
        )

        fun set(
            to: OutputStream,
            httpPath: HTTPPath
        ) {
            PATHS[httpPath.path]?.execute(
                to
            ) ?: {
                HTTPHeaders.error(
                    to
                )
            }
        }
    }
}