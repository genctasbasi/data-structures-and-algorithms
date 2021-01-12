package solutions.leetcode.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
class LongestSubstringWithoutRepeatingCharacters {

    @Test
    fun test() {
        val result = lengthOfLongestSubstring("tmmzuxt")
        Assert.assertEquals(5, result)
    }

    @Test
    fun test1() {
        val result = lengthOfLongestSubstring("abcabcbb")
        Assert.assertEquals(3, result)
    }

    @Test
    fun test2() {
        val result = lengthOfLongestSubstring("bbbbb")
        Assert.assertEquals(1, result)
    }

    @Test
    fun test3() {
        val result = lengthOfLongestSubstring("pwwkew")
        Assert.assertEquals(3, result)
    }

    @Test
    fun test4() {
        val result = lengthOfLongestSubstring("")
        Assert.assertEquals(0, result)
    }

    @Test
    fun test5() {
        val result = lengthOfLongestSubstring("wobgrovw")
        Assert.assertEquals(6, result)
    }

    fun lengthOfLongestSubstring(s: String): Int {

        var max = 0
        var count = 0
        val map = mutableMapOf<Char, Int>()
        var index = 0
        var startIndex = 0

        s.toCharArray().forEach { char ->

            if (map.contains(char)) {
                val existingIndex = map[char]!!
                if ((existingIndex + 1) > startIndex)
                    startIndex = existingIndex + 1

                var newLength = index - startIndex + 1

                if ((index - startIndex) == 0)
                    newLength = 1

                if (count > max)
                    max = count

                count = newLength

            } else {
                count++
            }

            map[char] = index

            if (count > max)
                max = count

            index++
        }

        return max

    }

}