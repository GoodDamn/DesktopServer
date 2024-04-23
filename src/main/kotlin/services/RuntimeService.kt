package good.damn.filesharing.services

import good.damn.filesharing.Application
import good.damn.filesharing.listeners.network.service.SSHServiceListener
import good.damn.filesharing.utils.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.LinkedList

class RuntimeService {

    var delegate: SSHServiceListener? = null

    companion object {
        private const val TAG = "RuntimeService"
        const val PORT = 55556
    }

    private val mRuntime = Runtime
        .getRuntime()

    fun start(
        remoteAddress: InetAddress,
        executionLine: Array<String>
    ) {
        Thread{
            val process = mRuntime.exec(
                executionLine
            )

            val inp = process.inputStream
            val err = process.errorStream

            val reader = BufferedReader(
                InputStreamReader(
                    inp
                )
            )

            val readerErr = BufferedReader(
                InputStreamReader(
                    err
                )
            )

            val socket = DatagramSocket()

            var line: String
            while(true) {
                line = reader.readLine()
                    ?: break

                val dataLine = line
                    .toByteArray(
                        Application.CHARSET_ASCII
                    )
                Log.d(TAG, "start: OUT: $line ${dataLine.size}")
                socket.send(
                    DatagramPacket(
                        dataLine,
                        dataLine.size,
                        remoteAddress,
                        PORT
                    )
                )
            }

            while(true) {
                line = readerErr.readLine()
                    ?: break

                Log.d(TAG, "start: OUT_ERROR: $line")

                val dataLine = line
                    .toByteArray(
                        Application.CHARSET_ASCII
                    )

                socket.send(
                    DatagramPacket(
                        dataLine,
                        dataLine.size,
                        remoteAddress,
                        PORT
                    )
                )
            }

            val d = byteArrayOf(0)

            socket.send(
                DatagramPacket(
                    d,
                    d.size,
                    remoteAddress,
                    PORT
                )
            )

        }.start()

    }
}