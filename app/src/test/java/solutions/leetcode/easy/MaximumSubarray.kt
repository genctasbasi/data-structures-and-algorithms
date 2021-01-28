package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/maximum-subarray/
 */
class MaximumSubarray {

    @Test
    fun test() {
        val result = maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))
        assertEquals(6, result)
    }

    fun maxSubArray(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]

        var max = Int.MIN_VALUE
        var sum: Int? = null
        nums.forEach {

            sum = if (sum == null) {
                it
            } else {

                if (it > (sum!! + it)) {
                    it
                } else {
                    sum!! + it
                }
            }

            if(sum!! > max)
                max = sum!!
        }

        return max
    }

}