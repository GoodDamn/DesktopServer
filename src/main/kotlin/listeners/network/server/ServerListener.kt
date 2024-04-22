package good.damn.filesharing.listeners.network.server

import good.damn.filesharing.listeners.network.NetworkInputListener
import java.net.ServerSocket
import java.net.Socket

interface ServerListener : NetworkInputListener {

    fun onCreateServer(server: ServerSocket)

    fun onStartListen()

    fun onDropClient(socket: Socket)

    fun onDropServer()
}