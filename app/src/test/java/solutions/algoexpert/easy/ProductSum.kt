package solutions.algoexpert.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Product%20Sum
 *
 * Complexity:
 *  Time complexity: O(n), where n is the number of all the element (including the inner arrays)
 *  Space complexity: O(d), d is the depth of the array (level of the most inner array)
 */
class ProductSum {

    @Test
    fun test() {
        // [5, 2, [7, -1], 3, [6, [-13, 8], 4]]
        val sampleInput = listOf(
            5,
            2,
            listOf(7, -1),
            3,
            listOf(6, listOf(-13, 8), 4)
        )
        assertEquals(12, productSum(sampleInput))
    }

    fun productSum(sampleInput: List<*>): Int {
        return productSum(sampleInput, 1)
    }

    private fun productSum(sampleInput: List<*>, level: Int): Int {

        var sum = 0

        sampleInput.forEach {
            when (it) {
                is Int -> sum += it
                else -> {
                    val newLevel = level + 1
                    sum += newLevel * productSum(it as List<*>, newLevel)
                }
            }
        }

        return sum
    }
}