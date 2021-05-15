package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
 */
class MinimumNumberOfIncrementsOnSubArraysToFormATargetArray {

    @Test
    fun test() {
        val result = minNumberOperations(intArrayOf(1, 2, 3, 2, 1))
        assertEquals(3, result)
    }

    @Test
    fun test1() {
        val result = minNumberOperations(intArrayOf(3, 1, 1, 2))
        assertEquals(4, result)
    }

    @Test
    fun test2() {
        val result = minNumberOperations(intArrayOf(3, 1, 5, 4, 2))
        assertEquals(7, result)
    }

    fun minNumberOperations(target: IntArray): Int {

        if (target.isEmpty()) return 0

        var sum = target[0]

        (1..target.lastIndex).forEach {
            val current = target[it]
            val prev = target[it - 1]

            if (current > prev) {
                sum += current - prev
            }
        }

        return sum
    }
}