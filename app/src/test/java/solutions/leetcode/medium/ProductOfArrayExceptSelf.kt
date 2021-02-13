package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 */
class ProductOfArrayExceptSelf {

    @Test
    fun test() {
        assertEquals(intArrayOf(24, 12, 8, 6), productExceptSelf(intArrayOf(1, 2, 3, 4)))
    }

    @Test
    fun test2() {
        assertEquals(intArrayOf(0, 0, 8, 0), productExceptSelf(intArrayOf(1, 2, 0, 4)))
    }

    @Test
    fun test3() {
        assertEquals(intArrayOf(0, 0, 0, 0), productExceptSelf(intArrayOf(1, 2, 0, 0)))
    }

    fun productExceptSelf(nums: IntArray): IntArray {

        if (nums.isEmpty()) return intArrayOf()

        // get the product of whole array, skipping a potential zero.
        // if there are two zeros, simply return an array filled with zeros

        var product = 1
        var hasAZero = false
        nums.forEach {
            if (it == 0) {
                if (hasAZero) return IntArray(nums.size) { 0 } else hasAZero = true
            } else {
                product *= it
            }
        }

        val output = IntArray(nums.size) { 0 }

        nums.forEachIndexed { index, it ->
            output[index] = if (it == 0) product else if (hasAZero) 0 else product / it
        }

        return output
    }
}