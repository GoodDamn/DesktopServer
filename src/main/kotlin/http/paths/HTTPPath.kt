package good.damn.filesharing.http.paths

open class HTTPPath(
    val header: ByteArray,
    val body: ByteArray
) {

    override fun toString(): String {
        return "header(${header.size}); body(${body.size})"
    }

    override fun equals(
        other: Any?
    ): Boolean {
        // Are you stupid to equal it? :)
        return false
    }

    override fun hashCode(): Int {
        return 0
    }
}