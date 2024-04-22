package good.damn.filesharing.share_protocol.method

import good.damn.filesharing.Application
import good.damn.filesharing.utils.ResponseUtils
import java.io.File

class ShareMethodSystemInfo
: ShareMethod(
    "info".toByteArray(
        Application.CHARSET_ASCII
    )
){

    companion object {
        private val msg = "Nothing"
    }

    override fun response(
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    ): ByteArray {
        return ResponseUtils.responseMessage16Id(
            msg
        )
    }

}