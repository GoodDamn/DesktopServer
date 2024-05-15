package good.damn.filesharing.share_protocol.interfaces

import java.io.File
import java.io.OutputStream

interface Responsible {

    fun makeResponse(
        os: OutputStream,
        request: ByteArray,
        argsCount: Int,
        argsPosition: Int,
        userFolder: File
    )

}