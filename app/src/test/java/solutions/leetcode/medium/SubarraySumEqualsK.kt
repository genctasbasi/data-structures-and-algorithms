package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * I could come up with the O(n2) solution but O(n) didn't make much sense
 */
class SubarraySumEqualsK {

    @Test
    fun test() {
        assertEquals(2, subarraySum(intArrayOf(1, 1, 1), 2))
    }

    @Test
    fun test1() {
        assertEquals(2, subarraySum(intArrayOf(1, 2, 3), 3))
    }

    @Test
    fun test2() {
        assertEquals(3, subarraySum(intArrayOf(1, -1, 0), 0))
    }

    @Test
    fun test3() {
        assertEquals(4, subarraySum(intArrayOf(3, 4, 7, 2, -3, 1, 4, 2), 7))
    }

    @Test
    fun test4() {
        assertEquals(4, subarraySum(intArrayOf(3, 4, 7, 2, -3, 1, 4, 2), 7))
    }

    @Test
    fun test5() {
        assertEquals(1, subarraySum(intArrayOf(-1, -1, 1), 0))
    }

    @Test
    fun test6() {
        assertEquals(55, subarraySum(intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0))
    }

    // O(n)
    fun subarraySum(nums: IntArray, k: Int): Int {

        val sums = hashMapOf<Int, Int>()
        var sum = 0
        var count = 0

        nums.forEachIndexed { index, num ->
            sum += num

            if (sum == k) count++

            if (sums.contains(sum - k)) {
                count += sums[sum - k]!!
            }

            sums[sum] = (sums[sum] ?: 0) + 1
        }

        return count
    }

    // O(n2)
    fun subarraySumO2(nums: IntArray, k: Int): Int {

        var count = 0
        nums.forEachIndexed { index, _ ->
            helper(nums, index, k)
            count += x
            x = 0
        }

        return count
    }

    var x = 0

    private fun helper(nums: IntArray, start: Int, k: Int) {
        if (start > nums.lastIndex) return
        if (k == nums[start]) x++
        helper(nums, start + 1, k - nums[start])

    }
}