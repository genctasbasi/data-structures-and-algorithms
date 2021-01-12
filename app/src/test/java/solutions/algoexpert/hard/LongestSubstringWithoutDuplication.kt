package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Longest%20Substring%20Without%20Duplication
 */
class LongestSubstringWithoutDuplication {

    @Test
    fun test() {
        val string = longestSubstringWithoutDuplication("clementisacap")
        assertEquals("mentisac", string)
    }

    @Test
    fun test2() {
        val string = longestSubstringWithoutDuplication("abcdeabcdefc")
        assertEquals("abcdef", string)
    }

    fun longestSubstringWithoutDuplication(string: String): String {

        val lastIndexMap = mutableMapOf<Char, Int>()

        var maxString = ""
        var longestSB = StringBuilder()

        string.forEachIndexed { index, c ->

            if (lastIndexMap[c] == null) {
                longestSB.append(c)
            } else {
                val lastIndex = lastIndexMap[c]!!

                if (longestSB.length > maxString.length)
                    maxString = longestSB.toString()

                longestSB = java.lang.StringBuilder(
                    longestSB.drop(longestSB.indexOf(c) + 1)
                )
                longestSB.append(c)

                val toBeRemoved = lastIndexMap.filter { it.value <= lastIndex }.keys

                toBeRemoved.forEach {
                    lastIndexMap.remove(it)
                }
            }

            lastIndexMap[c] = index
        }

        if (longestSB.length > maxString.length)
            maxString = longestSB.toString()

        return maxString
    }
}