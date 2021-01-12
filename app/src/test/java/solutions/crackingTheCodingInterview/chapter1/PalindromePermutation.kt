package solutions.crackingTheCodingInterview.chapter1

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Chapter 1: Arrays and Strings
 * Question 4: Check if a string a palindrome permutation
 * Example:
 * Input: "Tact Cao"
 * Output: true permutations: (taco cat, atco cta)
 */

@ExperimentalStdlibApi
class PalindromePermutation {

    private val testData = listOf(
        "Tact Cao",
        "Santa A eDvil Lived As a  NASA At ",  // A Santa Lived As a Devil At NASA
        "Well, this is clearly not a palindrome"
    )

    @Test
    fun `check if palindrome permutation`() {
        // Assert
        assertEquals(true, `is palindrome permutation`(testData[0]))
        assertEquals(true, `is palindrome permutation`(testData[1]))
        assertEquals(false, `is palindrome permutation`(testData[2]))
    }

    /**
     * Here we're counting the characters and allowing only 1 character count to be odd (i.e 1, 3), as that character can exist in the middle of the
     * palindrome. Other than that, if all the character counts are even, it means we can produce a palindrome from that value.
     */
    private fun `is palindrome permutation`(value: String): Boolean {

        var hasOdd = false
        val charCount = mutableMapOf<Char, Int>()

        value.toLowerCase().toCharArray().forEach() {
            if (it == ' ') return@forEach
            charCount[it] = charCount[it]?.plus(1) ?: 1
        }

        charCount.values.forEach {

            if (it.isOdd()) {
                if (hasOdd) return false else hasOdd = true
            }
        }

        return true
    }
}

private fun Int.isOdd() = this.rem(2) == 1
