package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/4sum/
 */
class FourSum {

    @Test
    fun test() {
        val result = fourSum(intArrayOf(1, 0, -1, 0, -2, 2), 0)
        assertEquals(listOf(listOf(-2, -1, 1, 2), listOf(-2, 0, 0, 2), listOf(-1, 0, 0, 1)), result)
    }

    @Test
    fun test2() {
        val result = fourSum(intArrayOf(), 0)
        assertEquals(listOf<Int>(), result)
    }

    @Test
    fun test3() {
        val result = fourSum(intArrayOf(0), 0)
        assertEquals(listOf<Int>(), result)
    }

    @Test
    fun test4() {
        val result = fourSum(intArrayOf(0, 0, 0, 0), 0)
        assertEquals(listOf<Int>(), result)
    }

    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {

        if (nums.size < 4) return emptyList()
        val quadruplets = mutableSetOf<List<Int>>()

        var p1 = 0
        var p2 = 1
        var p3 = 2
        var p4 = nums.lastIndex

        val numsSorted = nums.sorted()
        // -2, -1, 0, 0, 1, 2
        while (p1 < p2 && p2 < p4) {
            while (p2 < p3 && p3 < p4) {

                while (p3 < p4) {

                    val sum = numsSorted[p1] + numsSorted[p2] + numsSorted[p3] + numsSorted[p4]
                    if (sum == target) {
                        quadruplets.add(
                            listOf(
                                numsSorted[p1],
                                numsSorted[p2],
                                numsSorted[p3],
                                numsSorted[p4]
                            )
                        )
                        p3++
                    } else if (sum > target) {
                        p4--
                    } else {
                        p3++
                    }
                }

                p2++
                p3 = p2 + 1
                p4 = numsSorted.lastIndex
            }

            p1++
            p2 = p1 + 1
            p3 = p2 + 1
            p4 = numsSorted.lastIndex
        }

        return quadruplets.toList()
    }
}