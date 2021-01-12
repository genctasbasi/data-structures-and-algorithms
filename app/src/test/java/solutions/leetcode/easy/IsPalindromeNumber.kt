package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/palindrome-number/
 */
class IsPalindromeNumber {

    @Test
    fun test() {
        val result = isPalindrome(121)
        Assert.assertEquals(true, result)
    }

    fun isPalindrome(x: Int): Boolean {

        if (x < 0) return false
        if (x < 10) return true

        if (x.rem(10) == 0) return false

        var xStr = x.toString()
        var p1 = 0
        var p2 = xStr.lastIndex
        while (p1 < p2) {
            if (xStr[p1] != xStr[p2]) return false
            p1++
            p2--
        }

        return true
    }
}