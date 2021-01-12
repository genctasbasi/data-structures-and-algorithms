package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Longest%20Palindromic%20Substring
 */

class LongestPalindromicSubstring {

    @Test
    fun test1() {
        val result = longestPalindromicSubstring("abaxyzzyxf")
        Assert.assertEquals("xyzzyx", result)
    }

    @Test
    fun test3() {
        val result = longestPalindromicSubstring("noon")
        Assert.assertEquals("noon", result)
    }

    fun longestPalindromicSubstring(string: String): String {

        if(string.length == 1) return string

        val charArray = string.toCharArray()
        val endIndex = charArray.size - 1

        var longestPalindrome = ""
        charArray.forEachIndexed { index, char ->

            for (i in endIndex downTo index) {

                if (charArray[i] == char) {
                    val subString = string.subSequence(index, i + 1).toString()
                    if (isPalindrome(subString)) {
                        if (subString.length > longestPalindrome.length)
                            longestPalindrome = subString
                    }
                }
            }
        }

        return longestPalindrome
    }

    private fun isPalindrome(string: String): Boolean {

        if (string.isEmpty()) return false
        if (string.length == 1) return true
        var indexStart = 0
        var indexEnd = string.length - 1

        while (indexStart < indexEnd) {
            if (string[indexStart] != string[indexEnd])
                return false

            indexStart++
            indexEnd--
        }

        return true
    }
}