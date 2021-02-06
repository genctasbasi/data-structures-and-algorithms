package solutions.leetcode.medium

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/container-with-most-water/
 */
class ContainerWithMostWater {

    @Test
    fun test() {
        val result = maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7))
        assertEquals(49, result)
    }

    // O(n)
    fun maxArea(height: IntArray): Int {

        var maxWater = 0

        var p1 = 0
        var p2 = height.lastIndex

        while (p1 < p2) {

            val distance = p2 - p1
            val lowestHeight = Math.min(height[p1], height[p2])

            val water = distance * lowestHeight
            if (water > maxWater)
                maxWater = water

            if (height[p1] < height[p2])
                p1++
            else
                p2--
        }

        return maxWater
    }

    // O(n2) - brute force
    fun maxArea2(height: IntArray): Int {

        var maxWater = 0

        for (i in 0 until height.lastIndex) {
            for (j in (i + 1)..height.lastIndex) {
                val distance = j - i
                val lowestHeight = Math.min(height[i], height[j])

                val water = distance * lowestHeight
                if (water > maxWater)
                    maxWater = water
            }

        }

        return maxWater
    }
}