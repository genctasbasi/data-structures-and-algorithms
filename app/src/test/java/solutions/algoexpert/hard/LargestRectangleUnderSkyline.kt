package solutions.algoexpert.hard

import org.junit.Test
import java.util.*

/**
 * https://www.algoexpert.io/questions/Largest%20Rectangle%20Under%20Skyline
 */
class LargestRectangleUnderSkyline {

    @Test
    fun test() {
        val input = listOf(1, 3, 3, 2, 4, 1, 5, 3, 2)
        val expected = 9
        val output = largestRectangleUnderSkyline(input)
        assert(expected == output)
    }

    @Test
    fun test1() {
        val input = listOf(2, 1, 5, 6, 2, 3)
        val expected = 9
        val output = largestRectangleUnderSkyline(input)
        assert(expected == output)
    }

    fun largestRectangleUnderSkyline(buildings: List<Int>): Int {

        if (buildings.isEmpty()) return 0

        val left = Array(buildings.size) { -1 }
        val right = Array(buildings.size) { -1 }
        val area = Array(buildings.size) { -1 }

        val stack = Stack<Int>()

        var index = 0

        // find left limits
        while (index <= buildings.lastIndex) {

            val currentHeight = buildings[index]

            while (stack.isNotEmpty() && buildings[stack.peek()] >= currentHeight) {
                stack.pop()
            }

            if (stack.isEmpty()) left[index] = -1 else left[index] = stack.peek()

            stack.push(index)
            index++
        }

        // find right limits
        index = buildings.lastIndex
        stack.clear()
        while (index >= 0) {

            val currentHeight = buildings[index]

            while (stack.isNotEmpty() && buildings[stack.peek()] >= currentHeight) {
                stack.pop()
            }

            if (stack.isEmpty()) right[index] = buildings.lastIndex + 1 else right[index] =
                stack.peek()

            stack.push(index)
            index--
        }

        // calculate area:
        (0..buildings.lastIndex).forEach {
            val currentHeight = buildings[it]
            area[it] = (right[it] - left[it] - 1) * currentHeight
        }

        return area.max()!!
    }

}