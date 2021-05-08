package solutions.leetcode.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
class LongestSubstringWithAtLeastKRepeatingCharacters {

    @Test
    fun test() {
        val result = longestSubstring("aaabb", 3)
        assertEquals(3, result)
    }

    @Test
    fun test1() {
        val result = longestSubstring("ababbc", 2)
        assertEquals(5, result) // ababb
    }

    @Test
    fun test2() {
        val result = longestSubstring("abababcabababab", 2)
        assertEquals(16, result) // abababcababababc
    }

    /**
     * O(n2) solution
     */
    fun longestSubstring(s: String, k: Int): Int {

        var fast = 0
        var slow = 0
        var maxSubstring = ""
        val map = mutableMapOf<Char, Int>()

        val sb = StringBuilder()
        while (slow <= s.lastIndex) {
            while (fast <= s.lastIndex) {
                val char = s[fast]
                sb.append(char)
                map[char] = (map[char] ?: 0) + 1
                fast++

                val filtered = map.values.filter { it < k }
                if (filtered.isEmpty()) { // valid string
                    if (sb.length > maxSubstring.length) {
                        maxSubstring = sb.toString()
                    }
                }

            }

            sb.clear()
            map.clear()
            slow++
            fast = slow
        }

        return maxSubstring.length
    }
}