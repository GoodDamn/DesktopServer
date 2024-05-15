package good.damn.filesharing.http.methods

import good.damn.filesharing.Application
import good.damn.filesharing.http.HTTPPath
import good.damn.filesharing.services.HTTPResponse
import good.damn.filesharing.share_protocol.method.ShareMethod
import good.damn.filesharing.utils.Log
import java.io.File
import java.io.OutputStream

class HTTPPost
: ShareMethod(
    "POS".toByteArray(
        Application.CHARSET_ASCII
    )
) {

    companion object {
        private const val TAG = "HTTPPost"
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

        Log.d(TAG, "response: $httpMessage")


        HTTPResponse.set(
            to = os,
            HTTPPath(
                path,
                hashMapOf()
            )
        )
    }

}