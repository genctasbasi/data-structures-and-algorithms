package solutions.crackingTheCodingInterview.chapter1

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Integer.max
import kotlin.math.abs

/**
 * Chapter 1: Arrays and Strings
 * Question 5: Check if a string a palindrome permutation
 */

@ExperimentalStdlibApi
class OneAway {

    private val testData = listOf(
        Pair("pale", "ale"),
        Pair("pale", "ple"),
        Pair("pales", "pale"),
        Pair("pale", "bale"),
        Pair("pal", "pale"),
        Pair("pale", "bake"),
        Pair("pale", "bakery")
    )

    @Test
    fun `test if one edit away`() {
        // Assert
        assertEquals(true, `is one edit away`(testData[0]))
        assertEquals(true, `is one edit away`(testData[1]))
        assertEquals(true, `is one edit away`(testData[2]))
        assertEquals(true, `is one edit away`(testData[3]))
        assertEquals(true, `is one edit away`(testData[4]))
        assertEquals(false, `is one edit away`(testData[5]))
        assertEquals(false, `is one edit away`(testData[6]))
    }

    private fun `is one edit away`(pair: Pair<String, String>): Boolean {

        var differenceCount = 0

        if (abs(pair.first.length - pair.second.length) > 1) return false

        var indexPointer1 = 0
        var indexPointer2 = 0

        (0 until max(pair.first.length, pair.second.length)).forEach { _ ->

            // if this is the case, it means we came to the end of the one of the strings
            // and the length difference is 1 anyway
            if (indexPointer1 >= pair.first.length || indexPointer2 >= pair.second.length)
                return differenceCount <= 1

            val char1 = pair.first[indexPointer1]
            val char2 = pair.second[indexPointer2]

            if (char1 != char2) {   // that's a difference

                if (differenceCount != 0) return false
                differenceCount++

                if (pair.first.length > pair.second.length) {
                    indexPointer1++
                } else if (pair.first.length < pair.second.length) {
                    indexPointer2++
                }

            }

            // characters are same, move to next comparison
            indexPointer1++
            indexPointer2++
        }

        return true
    }
}
