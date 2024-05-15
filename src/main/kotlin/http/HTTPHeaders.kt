package good.damn.filesharing.http

import good.damn.filesharing.Application

class HTTPHeaders {
    companion object {
        fun file(
            contentSize: Int,
            fileName: String
        ): ByteArray {
            return (
              "HTTP/1.0 200 OK\r\n" +
              "Content-Length: $contentSize\r\n" +
              "Content-Type: application/octet-stream;\r\n" +
              "Content-Disposition: inline; filename=\"$fileName\"\r\n\r\n"
            ).toByteArray(Application.CHARSET)
        }

        fun error(): ByteArray {
            return (
               "HTTP/1.0 404 Not Found\r\n" +
               "Content-Length: 9\r\n" +
               "Content-Type: text/html; \r\n" +
               "Date: Mon, 29 Jan 2024 17:09:46 GMT\r\n\r\nNot found"
            ).toByteArray(Application.CHARSET)
        }

        fun html(
            contentSize: Int
        ): ByteArray {
            return (
              "HTTP/1.0 200 OK\r\n" +
              "Content-Length: $contentSize\r\n" +
              "Content-Type: text/html; charset=UTF-8\r\n" +
              "Date: Mon, 29 Jan 2024 17:09:46 GMT\r\n\r\n"
            ).toByteArray(Application.CHARSET)
        }
    }
}