package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-string-chain/
 */
class LongestStringChain {

    @Test
    fun test() {
        assertEquals(4, longestStrChain(arrayOf("a", "b", "ba", "bca", "bda", "bdca")))
    }

    @Test
    fun test2() {
        assertEquals(5, longestStrChain(arrayOf("xbc", "pcxbcf", "xb", "cxbc", "pcxbc")))
    }

    @Test
    fun test3() {
        assertEquals(2, longestStrChain(arrayOf("a", "b", "ab", "bac")))
    }

    @Test
    fun test4() {
        assertEquals(4, longestStrChain(arrayOf("bdca", "bda", "ca", "dca", "a")))
    }

    @Test
    fun test5() {
        assertEquals(4, longestStrChain(arrayOf("a", "ab", "ac", "bd", "abc", "abd", "abdd")))
    }

    fun longestStrChain(words: Array<String>): Int {

        if (words.size == 1) return 1

        var max = 1
        val sorted = words.sortedWith(compareBy { it.length })

        val map = mutableMapOf<String, Int>()

        sorted.forEach {
            map[it] = 1

            (it.indices).forEach { letterIndex ->
                val sb = StringBuilder(it)
                val checkIfExits = sb.deleteCharAt(letterIndex).toString()
                if (map[checkIfExits] != null) {
                    map[it] = map[checkIfExits]!! + 1

                    if (map[it]!! > max)
                        max = map[it]!!
                }
            }
        }

        return max
    }
}