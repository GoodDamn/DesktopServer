package good.damn.filesharing.listeners.network

import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.Charset

interface NetworkInputListener {

    fun onListenChunkData(
        data: ByteArray,
        readBytes:Int,
        last: Int)

    fun onGetFile(
        data: ByteArray,
        offset:Int,
        fileName: String)

    fun onGetText(
        msg: String)

    fun onHttpGet(
        request: String
    )

}