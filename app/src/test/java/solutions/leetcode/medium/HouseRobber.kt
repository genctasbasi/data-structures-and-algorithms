package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/house-robber/
 */
class HouseRobber {

    @Test
    fun test() {
        assertEquals(4, rob(intArrayOf(1, 2, 3, 1)))
    }

    @Test
    fun test1() {
        assertEquals(12, rob(intArrayOf(2, 7, 9, 3, 1)))
    }

    @Test
    fun test2() {
        assertEquals(4, rob(intArrayOf(2, 1, 1, 2)))
    }

    @Test
    fun test3() {
        assertEquals(103, rob(intArrayOf(1, 3, 1, 3, 100)))
    }

    @Test
    fun test4() {
        assertEquals(39, rob(intArrayOf(6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3)))
    }

    fun rob(nums: IntArray): Int {

        if (nums.isEmpty()) return 0
        if (nums.size < 3) return nums.max()!!

        val dp = Array(nums.size) { 0 }
        dp[0] = nums[0]
        dp[1] = Math.max(nums[0], nums[1])

        (2..dp.lastIndex).forEach {

            val option1 = dp[it - 2] + nums[it]
            val option2 = dp[it - 1]
            dp[it] = listOf(option1, option2).max()!!
        }

        return Math.max(dp.last(), dp[dp.lastIndex - 1])
    }

}