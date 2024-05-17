package good.damn.filesharing.utils

import good.damn.filesharing.Application
import java.io.*

class FileUtils {

    companion object {
        private val TAG = "FileUtils"

        fun readFile(
            file: File,
            buffer: ByteArray
        ): ByteArray {
            val fis = FileInputStream(file)
            val baos = ByteArrayOutputStream()
            var n: Int
            while (true) {
                n = fis.read(buffer)
                if (n == -1) {
                    break
                }
                baos.write(buffer,0,n)
            }
            val res = baos.toByteArray()
            baos.close()
            fis.close()

            return res
        }

        fun fromDoc(
            fileName: String,
            os: OutputStream
        ): Boolean {
            val docPath = getDocumentsFolder()
                .path

            val file = File("$docPath/$fileName")

            if (!file.exists()) {
                return false
            }

            val inp = FileInputStream(file)
            copyBytes(
                inp,
                os
            )
            inp.close()

            return true
        }

        fun fromDoc(
            fileName: String
        ): ByteArray? {
            val docPath = getDocumentsFolder()
                .path

            val file = File("$docPath/$fileName")

            if (!file.exists()) {
                return null
            }

            val inps = FileInputStream(file)
            val b = readBytes(inps)
            inps.close()

            return b
        }

        fun writeToDoc(
            fileName: String,
            data: ByteArray,
            offset: Int = 0,
            length: Int = data.size
        ): String? {

            val docPath = getDocumentsFolder()
                .path

            val file = File("$docPath/$fileName")

            return writeFile(
                file,
                data,
                offset,
                length
            )
        }

        private fun writeFile(
            file: File,
            data: ByteArray,
            offset: Int = 0,
            length: Int = data.size
        ): String? {
            return try {
                if (!file.exists() && file.createNewFile()) {
                    Log.d(TAG, "writeFile: $file is created")
                }

                val fos = FileOutputStream(file)
                fos.write(data, offset, length)
                fos.close()
                null
            } catch (e: IOException) {
                Log.d(TAG, "writeFile: EXCEPTION: ${e.message}")
                "${e.message} for ${file.path} WHICH EXISTING IS ${file.exists()}"
            }
        }

        fun getDocumentsFolder(): File {
            val dir = Application.CONFIG_FTP.serverDir

            Log.d(TAG, "getDocumentsFolder: $dir")

            if (!dir.exists() && dir.mkdir()) {
                Log.d(TAG, "writeToDoc: dir $dir is created")
            }

            return dir
        }

        fun hasUserRsa(
            user: String
        ): Boolean {
            return getUserRsaFile(
                user
            ).exists()
        }

        fun isUserFolderExists(
            user: String
        ): Boolean {
            return getUserFolder(user)
                .exists()
        }

        fun getUserRsaFile(
            user: String
        ): File {
            return getUserFolder(
                "$user/key"
            )
        }

        fun saveUserRsa(
            user: String,
            rsaKey: String
        ) {
            val folder = getUserFolder(
                user
            ).path

            val file = File("$folder/key")

            Log.d(TAG, "saveUserRsa: $file")
            
            writeFile(
                file,
                rsaKey.toByteArray(
                    Application.CHARSET_ASCII
                )
            )
        }

        fun getUsersRsa(
            buffer: ByteArray
        ): HashSet<String> {
            val hashSet = HashSet<String>()
            val users = getUsers() ?: return hashSet
            for (user in users) {
                val keyFile = File("${user.path}/key")
                if (keyFile.exists()) {
                    val keyData = readFile(
                        keyFile,
                        buffer
                    )
                    hashSet.add(String(
                        keyData,
                        Application.CHARSET_ASCII
                    ))
                }
            }
            return hashSet
        }

        fun getUsers(): Array<File>? {
            return File("${getDocumentsFolder()}/ssh")
                .listFiles()
        }

        fun getUserFolder(
            user: String
        ): File {
            return File("${getDocumentsFolder()}/ssh/${user}")
        }

        fun copyBytes(
            data: ByteArray,
            to: OutputStream,
            buffer: ByteArray = Application.BUFFER_MB
        ) {
            val inp = ByteArrayInputStream(data)
            copyBytes(
                inp,
                to,
                buffer
            )
            inp.close()
        }

        fun copyBytes(
            from: InputStream,
            to: OutputStream,
            buffer: ByteArray = Application.BUFFER_MB
        ) {
            var n: Int

            while (true) {
                n = from.read(buffer)
                if (n == -1) {
                    break
                }
                to.write(buffer,0,n)
            }
        }

        fun readBytes(
            inp: InputStream,
            buffer: ByteArray = Application.BUFFER_MB
        ): ByteArray {

            val outArr = ByteArrayOutputStream()

            var n: Int

            while (true) {
                n = inp.read(buffer)
                if (n == -1) {
                    break
                }
                outArr.write(buffer,0,n)
            }

            val data = outArr.toByteArray()
            outArr.close()

            return data
        }
    }

}