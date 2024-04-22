package good.damn.filesharing.utils

import good.damn.filesharing.Application

class ResponseUtils {
    companion object {

        fun responseMessage16Id(
            msg: String,
            msgId: Int = 2
        ): ByteArray {
            val data = msgBytes(msg)
            return ByteUtils.integer(msgId) + ByteUtils
                .short(data.size) + data
        }

        fun responseMessageId(
            msg: String,
            msgId: Int = 1
        ): ByteArray {
            val data = msgBytes(msg)
            return ByteUtils.integer(msgId) + byteArrayOf(
                data.size.toByte()
            ) + data
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