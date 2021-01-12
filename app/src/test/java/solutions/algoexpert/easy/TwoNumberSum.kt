package solutions.algoexpert.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Two%20Number%20Sum
 */
class TwoNumberSum {

    @Test
    fun test() {

        Assert.assertEquals(
            listOf(-1, 11),
            twoNumberSum(mutableListOf(3, 5, -4, 8, 11, 1, -1, 6), 10)
        )

        Assert.assertEquals(
            listOf(-1, 11),
            twoNumberSum2(mutableListOf(3, 5, -4, 8, 11, 1, -1, 6), 10)
        )
    }

    // O(n) time and O(1) space
    private fun twoNumberSum(array: MutableList<Int>, targetSum: Int): List<Int> {

        array.forEach {
            val needed = targetSum - it
            if (array.contains(needed) && needed != it) {
                return listOf(needed, it)
            }
        }

        return emptyList()
    }

    // O(n^2) time and O(1) space
    private fun twoNumberSum2(array: MutableList<Int>, targetSum: Int): List<Int> {

        array.forEachIndexed { index, i ->
            for (j in index + 1 until array.size) {
                if (i + array[j] == targetSum) return listOf(array[j], i)
            }
        }

        return emptyList()
    }
}