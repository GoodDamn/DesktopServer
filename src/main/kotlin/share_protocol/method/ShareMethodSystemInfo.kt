package good.damn.filesharing.share_protocol.method

import good.damn.filesharing.Application
import good.damn.filesharing.utils.ResponseUtils
import java.io.File
import java.io.OutputStream

class ShareMethodSystemInfo
: ShareMethod(
    "info".toByteArray(
        Application.CHARSET_ASCII
    )
){

    companion object {
        private val msg = "Nothing"
    }

    override fun makeResponse(
        os: OutputStream,
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    ) {
        ResponseUtils.responseMessage16Id(
            os,
            msg
        )
    }

}