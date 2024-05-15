package good.damn.filesharing.servers

import good.damn.filesharing.Application
import good.damn.filesharing.listeners.network.server.ServerListener
import good.damn.filesharing.services.ResponseService
import good.damn.filesharing.utils.Log
import java.io.ByteArrayOutputStream
import java.net.ServerSocket
import java.net.SocketException
import java.net.SocketTimeoutException

open class TCPServer(
    port: Int
): BaseServer<ServerListener>(
    port
), Runnable {

    private val TAG = "TCPServer"

    private var mServer: ServerSocket? = null

    private val mResponseService = ResponseService()

    final override var delegate: ServerListener?
        get() = super.delegate
        set(value) {
            mResponseService.delegate = value
            super.delegate = value
        }

    final override fun run() {
        mServer = onCreateSocket()
        mServer?.reuseAddress = true
        
        delegate?.onCreateServer(
            mServer!!
        )

        while (listen(Application.BUFFER_MB)) {
            // Listen...
        }
    }

    override fun serverType(): String {
        return "No SSL"
    }

    final override fun start() {
        Thread(this)
            .start()
    }

    final override fun stop() {
        mServer?.close()
        mServer = null
        delegate?.onDropServer()
    }

    internal open fun onCreateSocket(): ServerSocket {
        return ServerSocket(
            port
        )
    }

    private fun listen(
        buffer: ByteArray
    ): Boolean {

        delegate?.onStartListen()
        try {
            val clientSocket = mServer!!.accept()
            clientSocket.soTimeout = 13000

            val out = clientSocket.getOutputStream()
            val inp = clientSocket.getInputStream()

            val outArr = ByteArrayOutputStream()

            var n: Int

            var attempts = 0

            while (true) {
                if (inp.available() == 0) {
                    if (attempts >= 1000) {
                        break
                    }
                    attempts++
                    continue
                }
                n = inp.read(buffer)
                Log.d(TAG, "listen: READ $n ${outArr.size()}")
                delegate?.onListenChunkData(
                    buffer,
                    n,
                    inp.available()
                )

                if (n == -1) {
                    break
                }

                outArr.write(
                    buffer,
                    0,
                    n
                )
            }

            val data = outArr.toByteArray()
            outArr.close()

            Log.d(TAG, "listen: DATA_SIZE: ${data.size}")

            if (data.isEmpty()) {
                out.write(0)
                out.close()
                return true
            }

            mResponseService
                .manage(data,out)

            if (mServer == null) {
                out.close()
                return false
            }

            delegate?.onDropClient(
                clientSocket
            )

            out.close()
        } catch (e: SocketException) {
            Log.d(TAG, "listen: EXCEPTION:  ${e.message}")
            return false
        } catch (e: SocketTimeoutException) {
            Log.d(TAG, "listen: TIMED_OUT: ${e.message}")
        }

        return true
    }
}