package good.damn.filesharing.http.paths

import good.damn.filesharing.http.HTTPPath
import java.io.OutputStream

abstract class HTTPPathAction {
    abstract fun execute(
        to: OutputStream,
        httpPath: HTTPPath
    )
}