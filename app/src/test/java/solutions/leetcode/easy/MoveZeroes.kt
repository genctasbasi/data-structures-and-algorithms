package solutions.leetcode.easy

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/move-zeroes/
 */
class MoveZeroes {

    @Test
    fun test() {
        val arr = intArrayOf(0, 1, 0, 3, 12)
        moveZeroes(arr)
        assertArrayEquals(arrayOf(1, 3, 12, 0, 0), arr.toTypedArray())
    }

    @Test
    fun test1() {
        val arr = intArrayOf(0, 1, 0, 0, 0, 0, 3, 12)
        moveZeroes(arr)
        assertArrayEquals(arrayOf(1, 3, 12, 0, 0, 0, 0, 0), arr.toTypedArray())
    }

    @Test
    fun test2() {
        val arr = intArrayOf(4, 2, 4, 0, 0, 3, 0, 5, 1, 0)
        moveZeroes(arr)
        assertArrayEquals(arrayOf(4, 2, 4, 3, 5, 1, 0, 0, 0, 0), arr.toTypedArray())
    }

    @Test
    fun test3() {   // does one swap only
        val arr = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1)
        moveZeroes(arr)
        assertArrayEquals(arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0), arr.toTypedArray())
    }

    // 1, 2, 3, 0, 0, 5, 6, 0, 9
    // 0, 1, 0, 0, 0, 3, 12
    // 1, 0, 0, 3, 12
    // 1, 3, 0, 0, 12
    // 1, 3, 12, 0, 0
    fun moveZeroes(nums: IntArray) {

        if (nums.size < 2) return

        var p = 0
        var zeroIndex: Int? = null
        while (p <= nums.lastIndex) {

            val num = nums[p]

            when (num) {
                0 -> {
                    if (zeroIndex == null)
                        zeroIndex = p

                    p++
                }
                else -> {
                    if (zeroIndex != null) { // time to swap
                        val temp = nums[zeroIndex]
                        nums[zeroIndex] = num
                        nums[p] = temp
                        p = zeroIndex + 1
                        zeroIndex = null
                    } else p++
                }
            }
        }
    }
}