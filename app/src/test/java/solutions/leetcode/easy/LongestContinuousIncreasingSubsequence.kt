package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-continuous-increasing-subsequence/submissions/
 */
class LongestContinuousIncreasingSubsequence {

    @Test
    fun test() {
        val result = findLengthOfLCIS(intArrayOf(7, 1, 5, 3, 6, 8, 12, 17, 4))
        Assert.assertEquals(5, result)
    }

    fun findLengthOfLCIS(nums: IntArray): Int {

        if (nums.isEmpty()) return 0

        var max = 0
        var prev = nums[0]
        var currentCount = 1

        (1..nums.lastIndex).forEach {

            val num = nums[it]

            if (num > prev)
                currentCount++
            else {
                max = Math.max(max, currentCount)
                currentCount = 1
            }

            prev = nums[it]
        }

        max = Math.max(max, currentCount)

        return max

    }
}