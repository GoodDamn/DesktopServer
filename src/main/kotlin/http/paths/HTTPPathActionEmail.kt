package good.damn.filesharing.http.paths

import com.google.gson.Gson
import good.damn.filesharing.http.HTTPPath
import good.damn.filesharing.http.paths.json_models.HTTPModelEmail
import good.damn.filesharing.utils.Log
import java.io.OutputStream

class HTTPPathActionEmail
: HTTPPathAction() {

    companion object {
        private const val TAG = "HTTPPathActionEmail"
    }

    private val mGson = Gson()

    override fun execute(
        to: OutputStream,
        httpPath: HTTPPath
    ) {
        val payload = mGson.fromJson(
            httpPath.json,
            HTTPModelEmail::class.java
        )



    }

}