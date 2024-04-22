package good.damn.filesharing.utils

class Log {

    companion object {

        fun d(
            TAG: String,
            msg: String
        ) {
            println("D/$TAG: $msg")
        }

    }

}