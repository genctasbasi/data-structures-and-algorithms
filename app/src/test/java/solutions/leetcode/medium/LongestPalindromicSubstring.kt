package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-palindromic-substring/submissions/
 */
class LongestPalindromicSubstring {

    @Test
    fun test() {
        val result = longestPalindrome("cbbd")
        assertEquals("bb", result)
    }

    fun longestPalindrome(s: String): String {

        if (s.length < 2) return s

        var iStart = 0
        var iStart2 = 0
        var iEnd = s.lastIndex
        var iEnd2 = iEnd
        var longestPalindrome = ""

        while (iStart <= iEnd) {

            var startedTracking = false
            var cutStart = 0
            var cutEnd = 0
            while (iStart2 <= iEnd2) {

                val startChar2 = s[iStart2]
                val endChar2 = s[iEnd2]

                if (startChar2 == endChar2) {

                    if (!startedTracking) {
                        startedTracking = true
                        cutStart = iStart2
                        cutEnd = iEnd2
                    }

                    iStart2++
                    iEnd2--
                } else {
                    startedTracking = false
                    cutStart = 0
                    cutEnd = 0
                    iStart2 = iStart
                    iEnd--
                    iEnd2 = iEnd
                }
            }

            if (startedTracking) {
                val potential = s.substring(cutStart, cutEnd + 1)
                if (potential.length > longestPalindrome.length)
                    longestPalindrome = potential
            }

            iStart++
            iStart2 = iStart
            iEnd = s.lastIndex
            iEnd2 = iEnd
        }

        return longestPalindrome
    }
}