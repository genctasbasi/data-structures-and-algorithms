package solutions.algoexpert.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Run-Length%20Encoding
 */
class RunLengthEncoding {

    @Test
    fun test() {
        val encoded = runLengthEncoding("AAAAAAAAAAAAABBCCCCDD")
        Assert.assertEquals("9A4A2B4C2D", encoded)
    }

    fun runLengthEncoding(string: String): String {

        var sbEncoded = StringBuilder()

        // var currentChar: Char
        var prevChar: Char? = null
        var count = 0

        string.toCharArray().forEach { currentChar ->

            val hasChanged = prevChar != null && currentChar != prevChar
            val charLimit = count == 9

            if (charLimit || hasChanged) {    // changed
                sbEncoded.append("$count$prevChar")
                count = 0
            }

            count++
            prevChar = currentChar
        }

        sbEncoded.append("$count$prevChar")
        return sbEncoded.toString()
    }

}