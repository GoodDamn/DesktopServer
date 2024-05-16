package good.damn.filesharing.http.paths.json_models

data class HTTPModelEmail(
    val email: String,
    val subject: String,
    val body: String
)