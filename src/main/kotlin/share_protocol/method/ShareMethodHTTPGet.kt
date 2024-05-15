package good.damn.filesharing.share_protocol.method

import good.damn.filesharing.Application
import good.damn.filesharing.services.HTTPResponse
import good.damn.filesharing.utils.Log
import java.io.File
import java.io.OutputStream

class ShareMethodHTTPGet
: ShareMethod(
    byteArrayOf(0x47,0x45,0x54) // GET - ASCII Codes
) {

    companion object {
        private const val TAG = "ShareMethodHTTPGet"
    }

    override fun makeResponse(
        os: OutputStream,
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    ) {

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


        HTTPResponse.set(
            to = os,
            path
        )
    }

}