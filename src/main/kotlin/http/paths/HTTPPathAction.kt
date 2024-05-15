package good.damn.filesharing.http.paths

import java.io.OutputStream

abstract class HTTPPathAction {
    abstract fun execute(
        to: OutputStream
    )
}