package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/target-sum/
 */
class TargetSum {

    @Test
    fun test() {
        assertEquals(5, findTargetSumWays(intArrayOf(1, 1, 1, 1, 1), 3))
    }

    var count = 0
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return 0
        helper(nums, 0, 0, target)
        return count
    }

    fun helper(nums: IntArray, start: Int, sum: Int, target: Int) {

        if (start > nums.lastIndex) return

        if (start == nums.lastIndex) {
            if (nums.last() + sum == target) count++
            if (sum - nums.last() == target) count++
        } else {
            helper(nums, start + 1, sum + nums[start], target)
            helper(nums, start + 1, sum - nums[start], target)
        }
    }
}