package good.damn.filesharing.controllers.msgrs
import java.nio.charset.Charset

class Messenger {

    private val mCharset = Charset.forName("UTF-8")
    private var mIndex = 0
    private var messages = ""

    fun addMessage(
        text: String
    ) {
        //messages += "${mIndex++}) $text\n"
        println("${mIndex++} $text")
    }
}