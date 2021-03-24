package solutions.leetcode.easy

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/merge-sorted-array/
 */
class MergeSortedArray {

    @Test
    fun test() {
        val param = intArrayOf(1, 2, 3, 0, 0, 0)
        merge(param, 3, intArrayOf(2, 5, 6), 3)
        assertArrayEquals(intArrayOf(1, 2, 2, 3, 5, 6), param)
    }

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {

        var p1 = m - 1
        var p2 = n - 1
        var p1End = m + n - 1

        while (p1End >= 0) {

            val num1 = if (p1 >= 0) nums1[p1] else Int.MIN_VALUE
            val num2 = if (p2 >= 0) nums2[p2] else Int.MIN_VALUE

            if (num1 > num2) {
                nums1[p1End] = num1
                p1--
            } else {
                nums1[p1End] = num2
                p2--
            }

            p1End--
        }
    }

}