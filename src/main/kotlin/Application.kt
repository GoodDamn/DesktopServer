package good.damn.filesharing

import good.damn.filesharing.servers.SSLServer
import good.damn.filesharing.servers.TCPServer
import java.nio.charset.Charset

class Application {
    companion object {
        val BUFFER_MB = ByteArray(1024 * 1024)
        val CHARSET = Charset.forName("UTF-8")
        val CHARSET_ASCII = Charset.forName("US-ASCII")
        const val SERVER_PATH = "/home/gooddamn/Desktop/serverDir"
        var SERVER: TCPServer? = null
        var SERVER_SSL: SSLServer? = null

        /*fun createSSLContext(
            resources: Resources
        ): SSLContext {

                        val password = "123456"
                            .toCharArray()

                        val keyStore = KeyStore.getInstance("JKS")
                        keyStore.load(
                            resources.openRawResource(
                                R.raw.multi
                            ),
                            password
                        )

                        val trustStore = KeyStore.getInstance("JKS")
                        trustStore.load(
                            resources.openRawResource(
                                R.raw.multi_trust
                            ),
                            password
                        )

                        val keyManager = KeyManagerFactory.getInstance(
                            KeyManagerFactory.getDefaultAlgorithm()
                        )

                        keyManager.init(
                            keyStore,
                            password
                        )

                        val trustManager = TrustManagerFactory.getInstance(
                            TrustManagerFactory.getDefaultAlgorithm()
                        )

                        trustManager.init(
                            trustStore
                        )

                        val SSL = SSLContext.getInstance(
                            "TLS"
                        )

                        SSL.init(
                            keyManager.keyManagers,
                            trustManager.trustManagers,
                            null
                        )

            return SSLContext.getDefault()
        }*/
    }
}