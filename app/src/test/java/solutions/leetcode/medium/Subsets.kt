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

    private fun subsets(nums: IntArray): List<List<Int>> {
        return rec(nums, 0)
    }

    private fun rec(nums: IntArray, start: Int): MutableList<MutableList<Int>> {

        if (start > nums.lastIndex)
            return mutableListOf(mutableListOf())

        val output = mutableListOf(mutableListOf<Int>())
        nums.forEachIndexed { index, it ->
            val list = rec(nums, start + index + 1)
            blend(list, it)
            output += list
        }

        return output
    }

    private fun blend(list: MutableList<MutableList<Int>>, value: Int) {
        list.forEach {
            it.add(value)
        }
    }

    fun subsets2(nums: IntArray): List<List<Int>> {
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