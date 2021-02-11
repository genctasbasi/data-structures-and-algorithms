package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * O(n) is possible with a stack
 * https://www.youtube.com/watch?v=vcv3REtIvEo&t=981s&ab_channel=TECHDOSE
 */
class LargestRectangleInHistogram {

    @Test
    fun test() {
        val result = `largestRectangleArea O(n2)`(intArrayOf(2, 1, 5, 6, 2, 3))
        assertEquals(10, result)
    }

    /**
     * O(n2)
     */
    fun `largestRectangleArea O(n2)`(heights: IntArray): Int {

        var max = Int.MIN_VALUE

        heights.forEachIndexed { index, it ->
            val height = getArea(heights, index)
            max = Math.max(max, height)
        }

        return max
    }

    fun getArea(heights: IntArray, index: Int): Int {

        var left = index - 1
        var leftWidth = 0
        var right = index + 1
        var rightWidth = 0
        val barHeight = heights[index]

        // get left
        while (left >= 0) {
            if (heights[left] < barHeight) break
            leftWidth++
            left--
        }

        // get right
        while (right <= heights.lastIndex) {
            if (heights[right] < barHeight) break
            rightWidth++
            right++
        }

        val area = (leftWidth + rightWidth + 1) * barHeight
        // print("index: " + index + " l: " + leftWidth + " r: " + rightWidth + " area: " + area + ", ")
        return area
    }
}