package good.damn.filesharing.share_protocol.method

import good.damn.filesharing.Application
import good.damn.filesharing.utils.ByteUtils
import good.damn.filesharing.utils.FileUtils
import good.damn.filesharing.utils.Log
import good.damn.filesharing.utils.ResponseUtils
import java.io.File
import java.io.OutputStream

class ShareMethodSetFile
: ShareMethod(
    "sf".toByteArray(
        Application.CHARSET_ASCII
    )
) {

    override fun makeResponse(
        os: OutputStream,
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    ) {

        // TO DO: Capture a input stream to safe RAM
        // If it has a large file

        var position = argsPosition

        val fileLen = request[
            position
        ].toInt()

        position++
        val fileName = String(
            request,
            position,
            fileLen,
            Application.CHARSET_ASCII
        )

        Log.d("ShareMethodSetFile:", "response: $fileName $argsCount")

        position += fileLen
        val dataLen = ByteUtils
            .integer(
                request,
                position
            )
        position += 4
        FileUtils.writeToDoc(
            fileName,
            request,
            position,
            dataLen
        )

        ResponseUtils.responseMessageId(
            os,
            "File $fileName has been saved"
        )
    }

}