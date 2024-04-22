package good.damn.filesharing.servers

import java.net.ServerSocket
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLServerSocket

class SSLServer(
    port: Int = 4443
) : TCPServer(
   port
) {

    override fun serverType(): String {
        return "SSL"
    }

    override fun onCreateSocket(): ServerSocket {
        val socket = SSLContext
            .getDefault()
            .serverSocketFactory
            .createServerSocket(
                port
            )
        return socket
    }
}