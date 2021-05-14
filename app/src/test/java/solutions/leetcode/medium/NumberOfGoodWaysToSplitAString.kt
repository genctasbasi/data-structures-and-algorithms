package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/number-of-good-ways-to-split-a-string/
 */
class NumberOfGoodWaysToSplitAString {

    @Test
    fun test() {
        assertEquals(2, numSplits("aacaba"))
    }

    @Test
    fun test1() {
        assertEquals(1, numSplits("abcd"))
    }

    @Test
    fun test2() {
        assertEquals(4, numSplits("aaaaa"))
    }

    fun numSplits(s: String): Int {

        val left: HashMap<Char, Int> = HashMap()
        val right: HashMap<Char, Int> = HashMap()

        for (i in s.indices) {
            right[s[i]] = (right[s[i]] ?: 0) + 1
        }

        var count = 0
        
        for (i in s.indices) {
            val curr: Char = s[i]
            left[curr] = left.getOrDefault(curr, 0) + 1

            right[curr] = right.getOrDefault(curr, 0) - 1


            if (right[curr]!! <= 0) right.remove(curr)

            if (left.size == right.size) count++
        }

        return count
    }
}