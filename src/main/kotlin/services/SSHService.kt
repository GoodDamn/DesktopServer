package good.damn.filesharing.services

import good.damn.filesharing.Application
import good.damn.filesharing.share_protocol.interfaces.Responsible
import good.damn.filesharing.share_protocol.method.ssh.ShareMethodMakeDir
import good.damn.filesharing.share_protocol.ssh.SSHAuth
import good.damn.filesharing.utils.Log
import good.damn.filesharing.utils.ResponseUtils

class SSHService {

    private val mRequests: Array<Responsible> = arrayOf(
        ShareMethodMakeDir()
    )

    private val mRuntimeService = RuntimeService()

    companion object {
        private const val TAG = "SSHService"
    }

    fun makeResponse(
        auth: SSHAuth,
        request: ByteArray,
    ): ByteArray {

        var offset = auth.contentOffset

        val argsCount = request[offset]
            .toInt()

        val cmdLine = Array(argsCount) { "" }
        offset++
        for (i in 0 until argsCount) {
            val len = request[offset]
                .toInt()
            offset++
            cmdLine[i] = String(
                request,
                offset,
                len,
                Application.CHARSET_ASCII
            )
            offset += len
            Log.d(TAG,"CMD_PART: ${cmdLine[i]}")
        }

        return ResponseUtils.responseMessage(
            try {
                mRuntimeService.start(
                    cmdLine
                )
            } catch (e: Exception) {
                "No such command"
            }
        )
    }
}