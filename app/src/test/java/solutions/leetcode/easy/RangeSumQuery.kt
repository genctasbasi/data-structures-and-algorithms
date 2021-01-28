package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/range-sum-query-immutable/
 */
class RangeSumQuery {

    @Test
    fun test() {
        val arr = NumArray(intArrayOf(-2, 0, 3, -5, 2, -1))
        val result = arr.sumRange(2, 5)
        Assert.assertEquals(-1, result)
    }

    class NumArray(val nums: IntArray) {

        fun sumRange(i: Int, j: Int): Int {
            var sum = 0

            (i..j).forEach {
                sum += nums[it]
            }

            return sum
        }
    }
}