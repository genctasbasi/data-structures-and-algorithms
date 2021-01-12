package solutions.algoexpert.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Caesar%20Cipher%20Encryptor
 */
class CaesarCipherEncryptor {

    @Test
    fun test() {
        assertEquals("zab", caesarCipherEncryptor("xyz", 2))
        assertEquals("abc", caesarCipherEncryptor("abc", 0))
    }

    fun caesarCipherEncryptor(string: String, key: Int): String {

        val sb = StringBuilder()
        string.toCharArray().forEach {
            val base = it.toInt() - 97
            val newCharKey = (base + key).rem(26) + 97
            sb.append(newCharKey.toChar())
        }

        return sb.toString()
    }
}