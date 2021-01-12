package solutions.crackingTheCodingInterview.chapter1

import domain.Utils
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Chapter 1: Arrays and Strings
 * Question 2: Given two strings, check if one is permutation of another one.
 *
 * So first we need to clarify what a 'permutation of each other' means. That basically
 * means they have the same characters, in different order.
 */
class CheckPermutation {

    @Test
    fun `is permutation test data`() {

        // Arrange
        val string1 = "that seems easy really"
        val string2 = "seems that really easy"
        val string3 = "that seems eaZy really"

        // Act
        assertTrue(`is permutation by sorting`(string1, string2))
        assertFalse(`is permutation by sorting`(string2, string3))

        assertTrue(`is permutation by counting chars`(string1, string2))
        assertFalse(`is permutation by counting chars`(string2, string3))
    }

    private fun `is permutation by sorting`(string1: String, string2: String): Boolean {

        if (string1.length != string2.length) return false

        val string1Sorted = Utils.sort(string1)
        val string2Sorted = Utils.sort(string2)

        return string1Sorted == string2Sorted
    }

    private fun `is permutation by counting chars`(string1: String, string2: String): Boolean {

        val string1Chars: HashMap<Char, Int> = hashMapOf()
        val string2Chars: HashMap<Char, Int> = hashMapOf()

        string1.forEach {
            string1Chars[it] = string1Chars[it]?.plus(1) ?: 0
        }
        string2.forEach { string2Chars[it] = string2Chars[it]?.plus(1) ?: 0 }

        if (string1Chars.entries.size != string2Chars.entries.size) return false

        string1Chars.forEach {
            if (it.value != string2Chars[it.key]) return false
        }

        return true
    }
}