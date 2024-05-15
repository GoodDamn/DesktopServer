package good.damn.filesharing.services

import good.damn.filesharing.http.HTTPHeaders
import good.damn.filesharing.utils.FileUtils

class HTTPResponse {
    companion object {
        private const val TAG = "HTTPResponseManager"
        private val PATHS = hashMapOf(
            "email" to ByteArray(0),
            "email/send" to ByteArray(1)
        )

        fun create(
            path: String,
        ): ByteArray {

            val data = FileUtils
                .fromDoc(
                    path.ifEmpty { "welcome" }
                ) ?: return HTTPHeaders.error()

            if (path.contains(".")) {
                return HTTPHeaders.file(
                    data.size,
                    path
                ).plus(data)
            }

            return HTTPHeaders.html(data.size)
                .plus(data)
        }
    }
}