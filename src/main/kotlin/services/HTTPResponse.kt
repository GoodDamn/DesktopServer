package good.damn.filesharing.services

import good.damn.filesharing.http.HTTPHeaders
import good.damn.filesharing.utils.FileUtils
import good.damn.filesharing.utils.FileUtils.Companion.getDocumentsFolder
import good.damn.filesharing.utils.FileUtils.Companion.readBytes
import java.io.File
import java.io.FileInputStream
import java.io.OutputStream

class HTTPResponse {
    companion object {
        private const val TAG = "HTTPResponseManager"
        private val PATHS = hashMapOf(
            "email" to ByteArray(0),
            "email/send" to ByteArray(1)
        )

        fun set(
            to: OutputStream,
            path: String
        ) {
            val docPath = getDocumentsFolder()
                .path

            val file = File("$docPath/$path")

            if (!file.exists()) {
                HTTPHeaders.error(
                    to
                )
                return
            }

            val inp = FileInputStream(file)

            val contentSize = file.length()
                .toInt()

            if (path.contains(".")) {
                HTTPHeaders.file(
                    to,
                    contentSize,
                    path
                )

                FileUtils.copyBytes(
                    inp,
                    to
                )

                inp.close()
                return
            }

            HTTPHeaders.html(
                to,
                contentSize
            )

            FileUtils.copyBytes(
                inp,
                to
            )

            inp.close()
        }
    }
}