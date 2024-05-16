package good.damn.filesharing

import good.damn.filesharing.servers.TCPServer

fun main() {
    val server = TCPServer(
        8080
    )
    server.start()
    println("Server started!")
}
