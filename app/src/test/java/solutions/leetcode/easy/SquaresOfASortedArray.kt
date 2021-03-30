package solutions.leetcode.easy

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 */
class SquaresOfASortedArray {

    @Test
    fun test() {
        assertArrayEquals(intArrayOf(0, 1, 9, 16, 100), sortedSquares(intArrayOf(-4, -1, 0, 3, 10)))
    }

    @Test
    fun test1() {
        assertArrayEquals(intArrayOf(0, 9, 100), sortedSquares(intArrayOf(0, 3, 10)))
    }

    @Test
    fun test2() {
        assertArrayEquals(intArrayOf(4, 9, 25), sortedSquares(intArrayOf(-5, -3, -2)))
    }

    @Test
    fun test3() {
        assertArrayEquals(
            intArrayOf(4, 9, 25),
            sortedSquares(intArrayOf(-10000, -9999, -7, -5, 0, 0, 10000))
        )
    }

    fun sortedSquares(nums: IntArray): IntArray {

        if (nums.isEmpty()) return nums

        if (nums.last() < 0) return allNegativeItems(nums)
        if (nums[0] >= 0) return allPositiveItems(nums)

        // executing this line means we have both negative and positive values
        val output = mutableListOf<Int>()
        var pPositive = 0
        while (nums[pPositive] < 0) pPositive++

        // pPositive is on the very first positive value
        var pNegative = pPositive - 1

        while (pPositive <= nums.lastIndex || pNegative >= 0) {

            val positiveSquare =
                if (pPositive <= nums.lastIndex) nums[pPositive] * nums[pPositive] else null
            val negativeSquare = if (pNegative >= 0) nums[pNegative] * nums[pNegative] else null

            if (positiveSquare == null && negativeSquare == null) break

            when {
                positiveSquare == null -> {
                    output.add(negativeSquare!!)
                    pNegative--
                }
                negativeSquare == null -> {
                    output.add(positiveSquare)
                    pPositive++
                }
                positiveSquare <= negativeSquare -> {
                    output.add(positiveSquare)
                    pPositive++
                }
                else -> {
                    output.add(negativeSquare)
                    pNegative--
                }
            }
        }

        return output.toIntArray()
    }

    private fun allNegativeItems(nums: IntArray): IntArray {
        var p = nums.lastIndex
        val output = mutableListOf<Int>()
        while (p >= 0) {
            output.add(nums[p] * nums[p])
            p--
        }

        return output.toIntArray()
    }

    private fun allPositiveItems(nums: IntArray): IntArray {
        var p = 0
        val output = mutableListOf<Int>()
        while (p <= nums.lastIndex) {
            output.add(nums[p] * nums[p])
            p++
        }

        return output.toIntArray()
    }
}