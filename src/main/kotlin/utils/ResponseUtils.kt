package good.damn.filesharing.utils

import good.damn.filesharing.Application
import java.io.OutputStream

class ResponseUtils {
    companion object {

        fun responseMessage16Id(
            os: OutputStream,
            msg: String,
            msgId: Int = 2
        ) {
            val data = msgBytes(msg)
            os.write(ByteUtils
                .integer(msgId)
            )

            os.write(ByteUtils
                .short(data.size)
            )

            os.write(data)
        }

        fun responseMessageId(
            os: OutputStream,
            msg: String,
            msgId: Int = 1
        ) {
            val data = msgBytes(msg)
            os.write(ByteUtils
                .integer(msgId)
            )
            os.write(data.size)
            os.write(data)
        }

        private fun msgBytes(
            msg: String
        ): ByteArray {
            return msg.toByteArray(
                Application.CHARSET_ASCII
            )
        }
    }
}