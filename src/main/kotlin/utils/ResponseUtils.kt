package good.damn.filesharing.utils

import good.damn.filesharing.Application

class ResponseUtils {
    companion object {
        fun responseMessage(
            msg: String
        ): ByteArray {
            val data = msg
                .toByteArray(
                    Application.CHARSET_ASCII
                )

            return byteArrayOf(
                if (data.size > 127)
                    127.toByte()
                else data.size.toByte()
            ) + data
        }

        fun responseMessage16Id(
            msg: String,
            msgId: Int = 2
        ): ByteArray {
            val data = msg
                .toByteArray(
                    Application.CHARSET_ASCII
                )
            return ByteUtils.integer(msgId) + ByteUtils
                .short(data.size) + data
        }

        fun responseMessageId(
            msg: String,
            msgId: Int = 1
        ): ByteArray {
            return ByteUtils.integer(msgId) + responseMessage(
                msg
            )
        }
    }
}