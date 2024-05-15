package good.damn.filesharing.share_protocol.method

import good.damn.filesharing.Application
import good.damn.filesharing.utils.ByteUtils
import good.damn.filesharing.utils.FileUtils
import good.damn.filesharing.utils.ResponseUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.OutputStream

class ShareMethodGetFile
: ShareMethod(
    byteArrayOf(
        0x67, 0x66 // gf
    )
) {

    override fun makeResponse(
        os: OutputStream,
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    ) {
        val pathLength = request[argsPosition]
            .toInt()

        val path = String(
            request,
            argsPosition+1,
            pathLength,
            Application.CHARSET_ASCII
        )

        val docPath = FileUtils.getDocumentsFolder()
            .path

        val file = File("$docPath/$path")

        if (!file.exists()) {
            ResponseUtils.responseMessageId(
                os,
                "$path not exists"
            )
            return
        }

        val inp = FileInputStream(file)

        os.write(ByteUtils.integer(
            hashCode()
        ))

        os.write(ByteUtils.integer(
            file.length().toInt()
        ))

        FileUtils.copyBytes(
            inp,
            os
        )

        inp.close()
    }
}