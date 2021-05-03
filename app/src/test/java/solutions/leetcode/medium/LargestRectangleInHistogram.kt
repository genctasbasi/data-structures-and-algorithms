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
        val result = largestRectangleArea(intArrayOf(2, 1, 5, 6, 2, 3))
        assertEquals(10, result)
    }

    @Test
    fun `test O(n2)`() {
        val result = `largestRectangleArea O(n2)`(intArrayOf(2, 1, 5, 6, 2, 3))
        assertEquals(10, result)
    }

    /**
     * O(n)
     */
    fun largestRectangleArea(heights: IntArray): Int {

        if (heights.isEmpty()) return 0

        val left = Array(heights.size) { -1 }
        val right = Array(heights.size) { -1 }
        val area = Array(heights.size) { -1 }

        val stack = Stack<Int>()

        var index = 0

        // find left limits
        while (index <= heights.lastIndex) {

            val currentHeight = heights[index]

            while (stack.isNotEmpty() && heights[stack.peek()] >= currentHeight) {
                stack.pop()
            }

            if (stack.isEmpty()) left[index] = -1 else left[index] = stack.peek()

            stack.push(index)
            index++
        }

        // find right limits
        index = heights.lastIndex
        stack.clear()
        while (index >= 0) {

            val currentHeight = heights[index]

            while (stack.isNotEmpty() && heights[stack.peek()] >= currentHeight) {
                stack.pop()
            }

            if (stack.isEmpty()) right[index] = heights.lastIndex + 1 else right[index] =
                stack.peek()

            stack.push(index)
            index--
        }

        // calculate area:
        (0..heights.lastIndex).forEach {
            val currentHeight = heights[it]
            area[it] = (right[it] - left[it] - 1) * currentHeight
        }

        return area.max()!!
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

        // return the area
        return (leftWidth + rightWidth + 1) * barHeight
    }
}