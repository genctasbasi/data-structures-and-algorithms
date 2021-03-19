package solutions.algoexpert.easy

import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Sorted%20Squared%20Array
 */
class SortedSquaredArray {
    @Test
    fun TestCase1() {
        val input = listOf(1, 2, 3, 5, 6, 8, 9)
        val expected = listOf(1, 4, 9, 25, 36, 64, 81)
        val output = sortedSquaredArray(input)
        assert(expected == output)
    }

    fun sortedSquaredArray(array: List<Int>) = array.map { it * it }.sorted()

}