package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/ones-and-zeroes/solution/
 */
class OnesAndZeros {

    @Test
    fun test() {
        val result =
            findMaxForm(arrayOf("00000111", "10", "0001", "111001", "1", "0"), 5, 3)
        assertEquals(4, result)
    }

    @Test
    fun test1() {
        val result =
            findMaxForm(arrayOf("10", "0", "1"), 1, 1)
        assertEquals(2, result)
    }

    fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        val count = count(strs, 0, m, n, 0, hashMapOf())
        return count
    }

    fun count(
        strs: Array<String>,
        start: Int,
        m: Int,
        n: Int,
        count: Int,
        mem: HashMap<String, Int>
    ): Int {

        if (start > strs.lastIndex || (m == 0 && n == 0)) return count

        val currentItem = strs[start]

        val key = "$m-$n-$start"
        if (mem[key] != null) return mem[key]!!

        val mCount = currentItem.count { it == '0' }
        val nCount = currentItem.count { it == '1' }

        var option1Count = 0

        // option 1: take it
        if (m >= mCount && n >= nCount) {
            option1Count = count(strs, start + 1, m - mCount, n - nCount, count + 1, mem)
            val keyOption1 = "${m - mCount}-${n - nCount}-${start + 1}"
            mem[keyOption1] = option1Count
        }

        val option2Count = count(strs, start + 1, m, n, count, mem)
        val keyOption2 = "$m-$n-${start + 1}"
        mem[keyOption2] = option2Count

        return Math.max(option1Count, option2Count)
    }
}