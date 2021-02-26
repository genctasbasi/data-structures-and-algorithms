package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 */
class ValidPalindrome {

    @Test
    fun test() {
        Assert.assertEquals(true, validPalindrome("abca"))
    }

    @Test
    fun test1() {
        Assert.assertEquals(true, validPalindrome("xcaggdacx"))
    }

    @Test
    fun test2() {
        Assert.assertEquals(true, validPalindrome("xcadggacx"))
    }

    @Test
    fun test3() {
        Assert.assertEquals(true, validPalindrome("ebcbbececabbacecbbcbe"))
    }

    @Test
    fun test4() {
        Assert.assertEquals(true, validPalindrome("xcdfggMfdcx"))
    }

    @Test
    fun test5() {
        Assert.assertEquals(true, validPalindrome("xcdfMggfdcx"))
    }

    var hadExtraCharacter = false

    fun validPalindrome(s: String): Boolean {

        if (s.length < 2) return true

        var pStart = 0
        var pEnd = s.lastIndex

        while (pStart < pEnd) {
            if (s[pStart] != s[pEnd]) {

                if (hadExtraCharacter) return false

                hadExtraCharacter = true

                val cutLeft = s.substring(pStart + 1, pEnd + 1)
                val cutRight = s.substring(pStart, pEnd)
                val option1 = validPalindrome(cutLeft)
                val option2 = validPalindrome(cutRight)

                return option1 || option2
            }

            pStart++
            pEnd--
        }

        return true
    }
}