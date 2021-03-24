package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/palindrome-permutation/
 */
class PalindromePermutation {

    @Test
    fun test1() {
        assertEquals(true, canPermutePalindrome("carerac"))
    }

    @Test
    fun test2() {
        assertEquals(false, canPermutePalindrome("code"))
    }

    fun canPermutePalindrome(s: String): Boolean {

        val map = hashMapOf<Char, Int>()
        s.forEach {
            map[it] = (map[it] ?: 0) + 1
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