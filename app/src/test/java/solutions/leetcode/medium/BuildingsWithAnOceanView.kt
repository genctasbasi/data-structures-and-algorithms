package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/buildings-with-an-ocean-view/
 */
class BuildingsWithAnOceanView {

    @Test
    fun test() {
        assertArrayEquals(intArrayOf(0, 2, 3), findBuildings(intArrayOf(4, 2, 3, 1)))
    }

    @Test
    fun test2() {
        assertArrayEquals(intArrayOf(4, 3, 2, 1), findBuildings(intArrayOf(4, 2, 3, 1)))
    }

    fun findBuildings(heights: IntArray): IntArray {

        var highestInFront = Int.MIN_VALUE
        val output = mutableListOf<Int>()
        var p = heights.lastIndex
        while (p >= 0) {
            val height = heights[p]

            if (height > highestInFront) {
                output.add(p)
                highestInFront = height
            }

            p--
        }

        return output.reversed().toIntArray()
    }
}