package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/3sum/
 */
class ThreeSum {

    @Test
    fun test() {
        val result = threeSum(intArrayOf(-1, 0, 1, 2, -1, -4))
        assertEquals(listOf(listOf(-1, -1, 2), listOf(-1, 0, 1)), result)
    }

    @Test
    fun test2() {
        val result = threeSum(intArrayOf(0, 0, 0, 0))
        assertEquals(listOf(listOf(0, 0, 0)), result)
    }

    @Test
    fun test3() {
        val result = threeSum(intArrayOf(-2, 0, 1, 1, 2))
        assertEquals(listOf(listOf(-2, 0, 2), listOf(-2, 1, 1)), result)
    }

    /**
     * O(n2)
     */
    fun threeSum(nums: IntArray): List<List<Int>> {

        if (nums.size < 3) return emptyList()

        var p1 = 0
        var p2 = 1
        var p3 = nums.lastIndex

        val numsSorted = nums.sorted()
        val list = mutableSetOf<List<Int>>()
        while (p1 < p2 && p2 < p3) {

            while (p2 < p3) {

                val sum = numsSorted[p1] + numsSorted[p2] + numsSorted[p3]
                if (sum == 0) {
                    list.add(listOf(numsSorted[p1], numsSorted[p2], numsSorted[p3]))
                    p2++
                } else if (sum < 0) {
                    p2++
                } else {
                    p3--
                }
            }

            p1++
            p2 = p1 + 1
            p3 = numsSorted.lastIndex
        }

        return list.toList()
    }

}