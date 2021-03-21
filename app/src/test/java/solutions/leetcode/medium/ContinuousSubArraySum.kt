package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/continuous-subarray-sum/
 */
class ContinuousSubArraySum {

    @Test
    fun test() {
        val result = checkSubarraySum(intArrayOf(23, 2, 4, 6, 7), 6)
        assertEquals(true, result)
    }

    @Test
    fun test1() {
        val result = checkSubarraySum(intArrayOf(23, 2, 4, 6, 7), 6)
        assertEquals(true, result)
    }

    @Test
    fun test2() {
        val result = checkSubarraySum(intArrayOf(23, 2, 4, 6, 21), 21)
        assertEquals(false, result)
    }

    @Test
    fun test3() {
        val result = checkSubarraySum(intArrayOf(23, 2, 4, 6, 21), 0)
        assertEquals(false, result)
    }

    @Test
    fun test4() {
        val result = checkSubarraySum(intArrayOf(0, 0), 0)
        assertEquals(true, result)
    }

    @Test
    fun test5() {
        val result = checkSubarraySum(intArrayOf(0, 1, 0), 0)
        assertEquals(false, result)
    }

    @Test
    fun test6() {
        val result = checkSubarraySum(intArrayOf(0), 0)
        assertEquals(false, result)
    }

    /**
     * Hashmap solution
     */
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {

        if (nums.size < 2) return false
        val map = hashMapOf<Int, Int>()

        var sum = 0
        nums.forEachIndexed { index, it ->

            sum += it

            if (k == 0) {
                if (sum == 0 && index > 0) return true
            } else {

                val rem = sum.rem(k)
                if (map.contains(rem))
                    if (index - map[rem]!! > 1) return true

                map[rem] = index
            }
        }

        return false
    }

    /**
     * BruteForce solution
     */
    fun checkSubArraySumBF(nums: IntArray, k: Int): Boolean {

        var p1 = 0
        var p2 = 1

        if (nums.size < 2) return false

        while (p1 < nums.size) {

            var total = nums[p1]

            while (p2 < nums.size) {
                val num2 = nums[p2]
                total += num2

                if (k == 0) {
                    if (total == 0) return true
                } else if (total.rem(k) == 0) return true

                p2++
            }

            p1++
            p2 = p1 + 1
        }

        return false
    }
}