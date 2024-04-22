package good.damn.filesharing.utils

import good.damn.filesharing.Application
import java.security.MessageDigest
import java.util.*

class CryptoUtils {
    companion object {

        private val mDigestSha256 = MessageDigest
            .getInstance("SHA-256")

        fun sha256Base64(
            input: String
        ): String {
            val hash = sha256(
                input
            )

            return Base64.getUrlEncoder().encodeToString(
                hash
            ).replace("\\s+".toRegex(), "")
        }

        fun sha256Base64(
            input: ByteArray,
            offset: Int = 0,
            len: Int = input.size - offset
        ): String {

            val i = Arrays.copyOfRange(
                input,
                offset,
                offset+len
            )

            return Base64.getUrlEncoder().encodeToString(
                i
            ).replace("\\s+".toRegex(), "")
        }

        private fun sha256(
            input: String
        ): ByteArray {
            mDigestSha256.reset()
            return mDigestSha256.digest(
                input.toByteArray(
                    Application.CHARSET_ASCII
                )
            )
        }
    }
}