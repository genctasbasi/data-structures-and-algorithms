package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-common-prefix/
 */
class LongestCommonPrefix {

    @Test
    fun test() {
        val result = longestCommonPrefix(arrayOf("flower", "flow", "flight"))
        Assert.assertEquals("fl", result)
    }

    @Test
    fun test2() {
        val result = longestCommonPrefix(arrayOf("ab", "a"))
        Assert.assertEquals("fl", result)
    }

    fun longestCommonPrefix(strs: Array<String>): String {

        if (strs.isEmpty() || strs[0].isEmpty()) return ""

        val maxStringLength = 200
        val stringCount = strs.size
        var currentChar: Char?

        var currentCharIndex = 0
        var currentWordIndex = 0
        var currentWord: String
        val prefixBuilder = StringBuilder()

        while (currentCharIndex <= maxStringLength) {

            if (currentCharIndex >= strs[0].length)
                return prefixBuilder.toString()

            currentChar = strs[0][currentCharIndex]

            while (currentWordIndex < stringCount) {

                if (currentCharIndex >= strs[currentWordIndex].length)
                    return prefixBuilder.toString()

                currentWord = strs[currentWordIndex]
                if (currentWord[currentCharIndex] != currentChar) {   // already broken
                    return prefixBuilder.toString()
                }

                currentWordIndex++
            }

            prefixBuilder.append(currentChar)
            currentWordIndex = 0
            currentCharIndex++
        }

        return prefixBuilder.toString()
    }
}