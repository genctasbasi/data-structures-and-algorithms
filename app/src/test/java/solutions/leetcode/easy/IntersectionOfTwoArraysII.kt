package solutions.leetcode.easy

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
class IntersectionOfTwoArraysII {

    @Test
    fun test1() {
        assertArrayEquals(intArrayOf(2, 2), intersect(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2)))
    }

    @Test
    fun test2() {
        assertArrayEquals(
            intArrayOf(9, 4),
            intersect(intArrayOf(4, 9, 5), intArrayOf(9, 4, 9, 8, 4))
        )
    }

    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val map = hashMapOf<Int, Int>()
        val output = mutableListOf<Int>()
        nums1.forEach { map[it] = (map[it] ?: 0) + 1 }
        nums2.forEach {
            if ((map[it] ?: 0) != 0) {
                output.add(it)
                map[it] = map[it]!! - 1
            }
        }
        return output.toIntArray()
    }

}