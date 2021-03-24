package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */
class LongestSubstringWithAtMostKDistinctCharacters {

    @Test
    fun test() {
        assertEquals(3, lengthOfLongestSubstringKDistinct("eceba", 2))
    }

    fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {

        var p1 = 0
        var p2 = p1
        var max = 0
        var count = 0

        val usedLetters = hashSetOf<Char>()
        while (p1 <= s.lastIndex && p2 <= s.lastIndex) {

            while (p2 <= s.lastIndex) {

                val char = s[p2]

                if (usedLetters.size < k) {
                    usedLetters.add(char)
                    count++
                } else if (usedLetters.contains(char)) {
                    count++
                } else break

                p2++
            }

            usedLetters.clear()
            p1++
            p2 = p1
            max = Math.max(max, count)
            count = 0
        }

        return max
    }
}