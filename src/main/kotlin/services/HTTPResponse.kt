package good.damn.filesharing.services

import good.damn.filesharing.http.HTTPHeaders
import good.damn.filesharing.http.HTTPPath
import good.damn.filesharing.http.paths.HTTPPathActionEmail
import good.damn.filesharing.http.paths.HTTPPathDocument
import good.damn.filesharing.utils.FileUtils
import java.io.File
import java.io.FileInputStream
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
            val res = PATHS[httpPath.path]?.execute(
                to,
                httpPath
            )

            if (res != null) {
                return
            }

            val serverDir = FileUtils
                .getDocumentsFolder()

            val staticFile = File("$serverDir/${httpPath.path}")
            if (!(staticFile.exists() && staticFile.isFile)) {
                HTTPHeaders.error(
                    to
                )
                return
            }

            val p = httpPath.path

            val fileName = p
                .substring(
                    p.lastIndexOf("/") + 1
                )

            HTTPHeaders.file(
                to,
                staticFile.length().toInt(),
                fileName
            )

            val inp = FileInputStream(
                staticFile
            )

            FileUtils.copyBytes(
                inp,
                to
            )
        }
    }
}