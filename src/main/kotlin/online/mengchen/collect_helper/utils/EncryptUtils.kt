package online.mengchen.collect_helper.utils

import java.security.MessageDigest
import java.security.SecureRandom

/**
 * @Author mengchen
 * @create 2020-24-17 10:24
 */
object EncryptUtils {

    const val SALT_LENGTH = 12

    /**
     * 使用 SHA256 进行加密
     */
    fun encryptUserSHA256(str: String): String {
        // 随机生成盐值
        val random = SecureRandom()
        val salt = ByteArray(SALT_LENGTH)
        random.nextBytes(salt)
        val encryptByteArray = encryptUseSalt(str, salt)
        val res = ByteArray(encryptByteArray.size + SALT_LENGTH)
        salt.copyInto(res, 0, 0)
        encryptByteArray.copyInto(res, SALT_LENGTH, 0)
        return byteToHexString(res)
    }

    fun validPassword(password: String, passwordInDB: String): Boolean {
        val salt = hexStringToByte(passwordInDB).copyOfRange(0, SALT_LENGTH)
        val encryptByteArray = encryptUseSalt(password, salt)
        val res = ByteArray(encryptByteArray.size + SALT_LENGTH)
        salt.copyInto(res, 0, 0)
        encryptByteArray.copyInto(res, SALT_LENGTH, 0)
        val entryPass = byteToHexString(res)
        if (entryPass == passwordInDB) {
            return true
        }
        return false
    }

    private fun encryptUseSalt(str: String, salt: ByteArray): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256")
        digest.update(salt)
        return digest.digest(str.toByteArray())
    }

    private fun hexStringToByte(hex: String): ByteArray {
        val hexString = hex.toLowerCase();
        val byteArray = ByteArray(hexString.length shr 1)
        var index = 0
        for (i in hexString.indices) {
            if (index > hexString.length - 1) {
                return byteArray
            }
            val highDit = (hexString[index].digit(16) and 0xFF).toByte()
            val lowDit = (hexString[index + 1].digit(16) and 0xFF).toByte()
            byteArray[i] = (highDit.toInt() shl 4 or lowDit.toInt()).toByte()
            index += 2;
        }
        return byteArray;
    }


    /**
     * 将指定byte数组转换成16进制字符串
     * @param b
     * @return
     */
    private fun byteToHexString(bytes: ByteArray): String {
        val hexString = StringBuffer()
        for (i in bytes.indices) {
            var hex = (bytes[i].toInt() and 0xFF).toString(16)
            if (hex.length == 1) {
                hex = "0$hex";
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    private fun Char.digit(radix: Int): Int {
        return Character.digit(this, radix)
    }
}

fun main() {
    val passwordInDb = EncryptUtils.encryptUserSHA256("123456")
    val res = EncryptUtils.validPassword("123456", passwordInDb)
    println(res)
}