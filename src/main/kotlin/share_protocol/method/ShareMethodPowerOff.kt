package good.damn.filesharing.share_protocol.method

import good.damn.filesharing.Application
import java.io.File
import java.io.OutputStream

class ShareMethodPowerOff
: ShareMethod(
    "pff".toByteArray(
        Application.CHARSET_ASCII
    )
){

    companion object {
        private const val TAG = "ShareMethodPowerOff"
    }

    override fun makeResponse(
        os: OutputStream,
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    ) {
        Application.SERVER?.stop()
        Application.SERVER_SSL?.stop()
    }

}