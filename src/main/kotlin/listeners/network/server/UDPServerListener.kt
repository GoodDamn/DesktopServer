package good.damn.filesharing.listeners.network.server

import java.net.DatagramSocket

interface UDPServerListener {

    fun onCreateDatagram(
        socket: DatagramSocket
    )

    fun onResponse(
        data: ByteArray
    )

}