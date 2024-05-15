package good.damn.filesharing.http

import good.damn.filesharing.Application
import java.io.OutputStream

class HTTPHeaders {
    companion object {
        fun file(
            os: OutputStream,
            contentSize: Int,
            fileName: String
        ) {
            os.write((
              "HTTP/1.0 200 OK\r\n" +
              "Content-Length: $contentSize\r\n" +
              "Content-Type: application/octet-stream;\r\n" +
              "Content-Disposition: inline; filename=\"$fileName\"\r\n\r\n"
            ).toByteArray(Application.CHARSET))
        }

        fun error(
            os: OutputStream
        ) {
            os.write((
               "HTTP/1.0 200 OK\r\n" +
               "Content-Length: 9\r\n" +
               "Content-Type: text/html; \r\n" +
               "Connection: close; \r\n" +
               "Date: Mon, 29 Jan 2024 17:09:46 GMT\r\n\r\nNot found"
               ).toByteArray(Application.CHARSET)
            )
        }

        fun document(
            os: OutputStream,
            mimeType: String,
            contentSize: Int
        ) {
            os.write((
              "HTTP/1.0 200 OK\r\n" +
              "Content-Length: $contentSize\r\n" +
              "Content-Type: $mimeType; charset=UTF-8\r\n" +
              "Date: Mon, 29 Jan 2024 17:09:46 GMT\r\n\r\n"
            ).toByteArray(Application.CHARSET))
        }
    }
}