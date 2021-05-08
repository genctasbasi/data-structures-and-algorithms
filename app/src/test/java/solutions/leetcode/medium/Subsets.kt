package solutions.leetcode.medium

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/subsets/
 */
class Subsets {

    @Test
    fun test() {
        val result = subsets(intArrayOf(1, 2, 3, 4))
        assertEquals(9, result.size)
    }

    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()

        result.add(mutableListOf())

        nums.forEach { num ->
            for (list in result.toList()) {
                val x = list.toMutableList()
                x.add(num)
                result.add(x)
            }
        }

        return result
    }
}