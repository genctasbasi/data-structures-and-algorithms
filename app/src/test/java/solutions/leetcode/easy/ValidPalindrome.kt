package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/valid-palindrome
 */
class ValidPalindrome {

    @Test
    fun test() {
        Assert.assertEquals(true, isPalindrome("A man, a plan, a canal: Panama"))
    }

    @Test
    fun test1() {
        Assert.assertEquals(false, isPalindrome("race a car"))
    }

    fun isPalindrome(s: String): Boolean {

        var pStart = 0
        var pEnd = s.lastIndex

        while (pEnd > pStart) {

            val cStart = s[pStart].lowercaseChar()
            val cEnd = s[pEnd].lowercaseChar()

            if (!isAlphaNumeric(cStart)) {
                pStart++
                continue
            }

            if (!isAlphaNumeric(cEnd)) {
                pEnd--
                continue
            }

            if (cStart != cEnd) return false

            pStart++
            pEnd--

        }

        return true
    }

    fun isAlphaNumeric(char: Char): Boolean {
        return char in ('a'..'z') || char in ('A'..'Z') || char in ('0'..'9')
    }

}