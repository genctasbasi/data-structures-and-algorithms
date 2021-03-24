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

    @Test
    fun test2() {
        assertEquals(6, lengthOfLongestSubstringKDistinct("ecebalololo", 2))
    }

    fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {

        if (k == 0 || s.isEmpty()) return 0

        var p1 = 0
        var p2 = p1
        var max = 0

        val usedLetters = LinkedHashMap<Char, Int>()    // KEEPS THE ORDER!
        while (p2 <= s.lastIndex) {

            // eceab
            var char1 = s[p1]
            val char2 = s[p2]

            usedLetters[char2] = (usedLetters[char2] ?: 0) + 1

            while (usedLetters.size > k) {
                usedLetters[char1] = (usedLetters[char1] ?: 0) - 1
                if (usedLetters[char1] == 0)
                    usedLetters.remove(char1)

                p1++
                char1 = s[p1]
            }

            val newDistance = p2 - p1 + 1
            max = Math.max(max, newDistance)
            p2++
        }

        return max
    }

    fun `lengthOfLongestSubstringKDistinct O(n2)`(s: String, k: Int): Int {

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