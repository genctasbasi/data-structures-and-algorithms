package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/next-permutation/
 */
class NextPermutation {

    @Test
    fun test() {
        val arr = intArrayOf(1, 2, 3)
        nextPermutation(arr)
        assertArrayEquals(intArrayOf(1, 3, 2), arr)
    }

    @Test
    fun test1() {
        val arr = intArrayOf(1, 5, 8, 4, 7, 6, 5, 3, 1)
        nextPermutation(arr)
        assertArrayEquals(intArrayOf(1, 5, 8, 5, 1, 3, 4, 6, 7), arr)
    }

    @Test
    fun test2() {
        val arr = intArrayOf(2, 3, 1, 3, 3)
        nextPermutation(arr)
        assertArrayEquals(intArrayOf(2, 3, 3, 1, 3), arr)
    }

    fun nextPermutation(nums: IntArray) {

        var p = nums.lastIndex
        var prev = Int.MIN_VALUE
        while (p >= 0) {
            val num = nums[p]

            if (num < prev) { // first decreasing
                swap(nums, p)
                return
            }

            p--
            prev = num
        }

        nums.sort()
    }

    private fun swap(nums: IntArray, p: Int) {

        var swapIndex = p + 1
        val swappingNum = nums[p]
        var min = Int.MAX_VALUE
        var minIndex = 0
        while (swapIndex <= nums.lastIndex) {
            val num = nums[swapIndex]
            if (num > swappingNum && num <= min) {
                min = num
                minIndex = swapIndex
            }

            swapIndex++
        }

        // now we can swap:
        val temp = nums[p]
        nums[p] = nums[minIndex]
        nums[minIndex] = temp

        // now reverse the 'rest'
        var p1 = p + 1
        var p2 = nums.lastIndex


        while (p1 < p2) {

            val tempReverse = nums[p1]
            nums[p1] = nums[p2]
            nums[p2] = tempReverse

            p1++
            p2--
        }
    }
}