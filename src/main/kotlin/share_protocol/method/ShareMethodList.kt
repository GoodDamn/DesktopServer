package good.damn.filesharing.share_protocol.method

import good.damn.filesharing.Application
import good.damn.filesharing.utils.ByteUtils
import good.damn.filesharing.utils.FileUtils
import good.damn.filesharing.utils.ResponseUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.OutputStream

class ShareMethodList
: ShareMethod(
    byteArrayOf(0x6C, 0x69) // li
) {
    override fun makeResponse(
        os: OutputStream,
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    ) {

        val files = FileUtils
            .getDocumentsFolder()
            .listFiles()

        if (files == null) {
            ResponseUtils.responseMessageId(
                os,
                "No files inside this server"
            )
            return
        }

        os.write(ByteUtils.integer(
            hashCode()
        ))

        os.write(
            files.size
        )

        for (file in files) {
            val fileName = file.name.toByteArray(
                Application.CHARSET_ASCII
            )

            os.write(
                fileName.size
            )

            os.write(
                fileName
            )
        }
    }
}