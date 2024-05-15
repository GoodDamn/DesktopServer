package good.damn.filesharing.share_protocol.method

import good.damn.filesharing.Application
import good.damn.filesharing.services.HTTPResponse
import good.damn.filesharing.utils.Log
import java.io.File

class ShareMethodHTTPGet
: ShareMethod(
    byteArrayOf(0x47,0x45,0x54) // GET - ASCII Codes
) {

    companion object {
        private const val TAG = "ShareMethodHTTPGet"
    }

    override fun response(
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    ): ByteArray {

        val httpMessage = String(
            request,
            Application.CHARSET
        )

        val path = httpMessage
            .substring(
                5,
                httpMessage.indexOf(
                    " ",
                    5
                )
            )

        Log.d(TAG, "response: $path")

        return HTTPResponse
            .create(path)
    }

}