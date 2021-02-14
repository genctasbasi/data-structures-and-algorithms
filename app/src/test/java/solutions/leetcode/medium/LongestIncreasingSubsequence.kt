package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */
class LongestIncreasingSubsequence {

    @Test
    fun test() {
        assertEquals(4, lengthOfLIS(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18)))
    }

    fun lengthOfLIS(nums: IntArray): Int {

        if (nums.isEmpty()) return 0
        val dp = Array(nums.size) { 1 }

        (1..nums.lastIndex).forEach { i ->
            (0 until i).forEach { j ->

                val iNum = nums[i]
                val jNum = nums[j]

                if (iNum > jNum && dp[i] <= dp[j])
                    dp[i] = dp[j] + 1
            }
        }

        return dp.max() ?: 0
    }
}