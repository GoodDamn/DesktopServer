package good.damn.filesharing.services

import good.damn.filesharing.Application
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
            if (!staticFile.exists()) {
                HTTPHeaders.error(
                    to
                )
                return
            }

            if (staticFile.isDirectory) {

                var result = "<!DOCTYPE html>" +
                        "<html>"

                val files = staticFile
                    .listFiles()

                if (files != null) {
                    for (file in files) {
                        result += "<a href=\"${httpPath.path}/${file.name}\">${file.name}</a><br>"
                    }
                }

                result += "</html>"

                val data = result.toByteArray(
                    Application.CHARSET
                )

                HTTPHeaders.document(
                    to,
                    "text/html",
                    data.size
                )

                FileUtils.copyBytes(
                    data,
                    to
                )

                return
            }

            HTTPHeaders.file(
                to,
                staticFile.length().toInt(),
                staticFile.name
            )

            val inp = FileInputStream(
                staticFile
            )

            FileUtils.copyBytes(
                inp,
                to
            )

            inp.close()
        }
    }
}