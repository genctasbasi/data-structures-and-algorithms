package solutions.crackingTheCodingInterview.chapter1

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Chapter 1: Arrays and Strings
 * Question 3: Replace all spaces in a string with %20.
 * Requirement: Use char array if implementing with Java
 * Example:
 * Input: "Mr John Smith     ", 13
 * Output: "Mr%20John%20Smith"
 */

@ExperimentalStdlibApi
class URLify {

    private val testData: List<Pair<String, Int>> = listOf(
        Pair("Mr John Smith    ", 13),
        Pair("It's OK  ", 7)
    )

    @Test
    fun `url-ify`() {

        val testData0Replaced = replaceSpaces(testData[0].first, testData[0].second)
        val testData1Replaced = replaceSpaces(testData[1].first, testData[1].second)

        assertEquals(testData0Replaced, "Mr%20John%20Smith")
        assertEquals(testData1Replaced, "It's%20OK")
    }

    /**x
     * We need 2 pointers, one to point the current char we're looping, other one to point
     * the placement char index (this is where we'll place our char to)
     */
    private fun replaceSpaces(value: String, length: Int): String {

        val valueAsArray = value.toCharArray()

        var placementIndex = value.length - 1

        for (charIndex in length - 1 downTo 0) {

            val char = value[charIndex]

            if (char != ' ') {
                valueAsArray[placementIndex] = char
                placementIndex -= 1
            } else {

                valueAsArray[placementIndex] = '0'
                placementIndex -= 1
                valueAsArray[placementIndex] = '2'
                placementIndex -= 1
                valueAsArray[placementIndex] = '%'
                placementIndex -= 1
            }
        }

        return valueAsArray.concatToString()
    }

}