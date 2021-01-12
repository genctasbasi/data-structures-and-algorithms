package solutions.algoexpert.easy

import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Palindrome%20Check
 */
class PalindromeCheck {

    @Test
    fun test() {
        assertTrue(isPalindrome("abcdcba"))
        assertFalse(isPalindrome("abcdcbaxy"))
    }

    fun isPalindrome(string: String): Boolean {

        if (string.length < 2) return true

        var left = 0
        var right = string.length - 1

        while (left < right) {

            if (string.elementAt(left) != string.elementAt(right)) return false

            left++
            right--
        }

        return true
    }

    fun canBePalindrome(string: String): Boolean {

        if (string.length < 2) return true
        val map = HashMap<Char, Int>()

        string.toCharArray().forEach {
            map[it] = (map[it] ?: 0).plus(1)
        }

        var hasOdd = false
        map.values.forEach {

            if (it.rem(2) == 1) {
                if (hasOdd) return false else hasOdd = true
            }
        }

        return true
    }
}