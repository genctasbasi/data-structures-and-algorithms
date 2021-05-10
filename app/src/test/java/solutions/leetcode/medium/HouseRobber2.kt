package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/house-robber-ii/
 */
class HouseRobber2 {

    @Test
    fun test() {
        assertEquals(3, rob(intArrayOf(2, 3, 2)))
    }

    @Test
    fun test1() {
        assertEquals(4, rob(intArrayOf(1, 2, 3, 1)))
    }

    @Test
    fun test2() {
        assertEquals(16, rob(intArrayOf(2, 3, 2, 4, 1, 9)))
    }


    @Test
    fun test3() {
        assertEquals(16, rob(intArrayOf(2, 3, 2, 4, 1, 9)))
    }

    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size < 3) return Math.max(nums[0], nums[1])

        val option1 = robHelper(nums.copyOfRange(0, nums.lastIndex))
        val option2 = robHelper(nums.copyOfRange(1, nums.lastIndex + 1))

        return Math.max(option1, option2)
    }

    fun robHelper(nums: IntArray): Int {

        val dp = Array(nums.size) { 0 }

        dp[0] = nums[0]
        dp[1] = Math.max(dp[0], nums[1])

        (2..nums.lastIndex).forEach {

            val option1 = dp[it - 2] + nums[it]
            val option2 = dp[it - 1]

            dp[it] = Math.max(option1, option2)
        }

        return Math.max(dp.last(), dp[dp.lastIndex - 1])
    }

}