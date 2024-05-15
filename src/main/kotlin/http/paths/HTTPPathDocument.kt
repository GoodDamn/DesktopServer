package good.damn.filesharing.http.paths

import good.damn.filesharing.http.HTTPHeaders
import good.damn.filesharing.utils.FileUtils
import java.io.File
import java.io.FileInputStream
import java.io.OutputStream

class HTTPPathDocument(
    docPath: String,
    private val mimeType: String
): HTTPPathAction() {

    companion object {
        private val SERVER_DIR = FileUtils
            .getDocumentsFolder()
    }

    private val mFile = File(
        "$SERVER_DIR/$docPath"
    )

    override fun execute(
        to: OutputStream
    ) {
        if (!mFile.exists()) {
            HTTPHeaders.error(
                to
            )
            return
        }

        val inp = FileInputStream(
            mFile
        )

        val contentSize = mFile.length()
            .toInt()
        HTTPHeaders.document(
            to,
            mimeType,
            contentSize
        )

        FileUtils.copyBytes(
            inp,
            to
        )
    }
}