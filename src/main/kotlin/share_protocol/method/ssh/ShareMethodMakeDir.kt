package good.damn.filesharing.share_protocol.method.ssh

import good.damn.filesharing.Application
import good.damn.filesharing.share_protocol.method.ShareMethod
import good.damn.filesharing.utils.ResponseUtils
import java.io.File
import java.io.OutputStream

class ShareMethodMakeDir
: ShareMethod(
    byteArrayOf(
        0x6D, 0x6B, 0x64 // mkd
    )
) {
    companion object {
        private const val TAG = "ShareMethodMakeDir"
    }

    override fun makeResponse(
        os: OutputStream,
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    ) {
        if (argsCount <= 0) {
            return ResponseUtils.responseMessageId(
                os,
                "No folder name for creating directory"
            )
        }

        val folderLen = request[argsPosition]
            .toInt()

        val folderPath = String(
            request,
            argsPosition + 1,
            folderLen,
            Application.CHARSET_ASCII
        )

        val path = File("$userFolder/$folderPath")

        if (path.exists()) {
            ResponseUtils.responseMessageId(
                os,
                "$folderPath already exists"
            )
            return
        }

        if (path.mkdirs()) {
            ResponseUtils.responseMessageId(
                os,
                "Folder at $folderPath created"
            )
            return
        }

        ResponseUtils.responseMessageId(
            os,
            "Couldn't create a dir $folderPath"
        )

        return
    }

}