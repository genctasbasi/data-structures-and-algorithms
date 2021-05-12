package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
 */
class MinDiffInThreeMoves {

    @Test
    fun test() {
        val result = minDifference(intArrayOf(5, 3, 2, 4))
        assertEquals(0, result)
    }

    @Test
    fun test1() {
        val result = minDifference(intArrayOf(1, 5, 0, 10, 14))
        assertEquals(1, result)
    }

    @Test
    fun test2() {
        val result = minDifference(intArrayOf(6, 6, 0, 1, 1, 4, 6))
        assertEquals(2, result)
    }

    fun minDifference(nums: IntArray): Int {

        if (nums.size <= 4) return 0    // since all numbers will be same
        var min = Int.MAX_VALUE

        val sorted = nums.sorted()

        var left = 0
        var right = nums.lastIndex - 3

        while (right <= nums.lastIndex) {
            val diff = Math.abs(sorted[left] - sorted[right])
            min = Math.min(min, diff)
            left++
            right++
        }

        return min
    }
}