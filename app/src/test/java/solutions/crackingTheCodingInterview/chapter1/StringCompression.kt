package solutions.crackingTheCodingInterview.chapter1

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Chapter 1: Arrays and Strings
 * Question 6: String compression
 */

@ExperimentalStdlibApi
class StringCompression {

    private val testData: List<String> = listOf(
        "aabcccccaaa",
        "aabbbbccdeeeff",
        "abcdefgh"
    )

    @Test
    fun `compress data`() {

        val compress1 = compress(testData[0])
        val compress2 = compress(testData[1])
        val compress3 = compress(testData[2])

        assertEquals(compress1, "a2b1c5a3")
        assertEquals(compress2, "a2b4c2d1e3f2")
        assertEquals(compress3, "abcdefgh")
    }

    private fun compress(value: String): String {

        val stringBuilder = StringBuilder()

        var currentChar: Char = value[0]
        var currentCount = 0

        value.toCharArray().forEach { char ->

            if (char == currentChar) {

                currentCount++
            } else {    // char changed

                stringBuilder.append("$currentChar$currentCount")

                currentChar = char
                currentCount = 1
            }

        }

        stringBuilder.append("$currentChar$currentCount")

        val compressed = stringBuilder.toString()
        return if (compressed.length < value.length) compressed else value
    }

}