package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 */
class MaximumProductSubarray {

    @Test
    fun test() {
        assertEquals(6, maxProduct(intArrayOf(2, 3, -2, 4)))
    }

    @Test
    fun test1() {
        assertEquals(0, maxProduct(intArrayOf(-2, 0, -1)))
    }

    @Test
    fun test2() {
        assertEquals(24, maxProduct(intArrayOf(-2, 3, -4)))
    }

    @Test
    fun test3() {
        assertEquals(2, maxProduct(intArrayOf(0, 2)))
    }

    @Test
    fun test4() {
        assertEquals(24, maxProduct(intArrayOf(2, -5, -2, -4, 3)))
    }

    /**
     * It was a bit difficult to come up with an O(n) solution but the trick is to keep both
     * 'max so far' and 'min so far' values because of the negativity, min so far can be the next max.
     */
    fun maxProduct(nums: IntArray): Int {

        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        var max = nums[0]
        var maxSoFar = nums[0]
        var minSoFar = nums[0]

        // 2, 3, -2, 4
        (1 until nums.size).forEach {
            val num = nums[it]
            val productWithMax = maxSoFar * num
            val productWithMin = minSoFar * num

            minSoFar = listOf(productWithMax, productWithMin, num).min()!!
            maxSoFar = listOf(productWithMax, productWithMin, num).max()!!

            max = Math.max(max, maxSoFar)
        }

        return max
    }

    /**
     * BF option - O(n2)
     */
    fun `maxProduct O(n2)`(nums: IntArray): Int {

        var max = Int.MIN_VALUE

        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]
        var i = 0
        var j = i + 1

        while (i <= nums.lastIndex && j <= nums.lastIndex) {

            val num = nums[i]
            var product = num

            if (product > max)
                max = product

            while (i <= nums.lastIndex && j <= nums.lastIndex) {
                val numJ = nums[j]
                if (numJ > max)
                    max = numJ

                product *= numJ

                if (product > max)
                    max = product

                j++
            }

            i++
            j = i + 1
        }

        return max
    }

    fun maxProduct2(nums: IntArray): Int {

        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        var max = Int.MIN_VALUE

        val dp = Array(nums.size) { Int.MIN_VALUE }
        dp[0] = nums[0]
        (1 until nums.size).forEach {
            val num = nums[it]

            val option1 = num
            val option2 = num * nums[it - 1]
            val option3 = num * dp[it - 1]

            val maxOption = listOf(option1, option2, option3).max()!!
            dp[it] = maxOption

            max = Math.max(max, maxOption)
        }

        return max
    }
}