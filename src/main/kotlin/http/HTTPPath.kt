package good.damn.filesharing.http

data class HTTPPath(
    val path: String,
    val json: HashMap<String, String>
)