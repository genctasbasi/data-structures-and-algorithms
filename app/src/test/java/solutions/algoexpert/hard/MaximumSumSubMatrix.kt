package solutions.algoexpert.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Maximum%20Sum%20Submatrix
 */
class MaximumSumSubMatrix {

    @Test
    fun test() {
        val matrix = listOf(
            listOf(5, 3, -1, 5),
            listOf(-7, 3, 7, 4),
            listOf(12, 8, 0, 0),
            listOf(1, -8, -8, 2)
        )
        val size = 2
        val expected = 18
        val output = maximumSumSubmatrix(matrix, size)
        assertEquals(expected, output)
    }

    @Test
    fun test1() {
        val matrix = listOf(
            listOf(2, 4),
            listOf(5, 6),
            listOf(-3, 2)
        )
        val size = 2
        val expected = 17
        val output = maximumSumSubmatrix(matrix, size)
        assertEquals(expected, output)
    }

    @Test
    fun test2() {
        val matrix = listOf(
            listOf(-1, -2, -3, -4, -5),
            listOf(-5, -4, -3, -2, -1),
            listOf(-1, -2, -3, -4, -5)
        )
        val size = 2
        val expected = -12
        val output = maximumSumSubmatrix(matrix, size)
        assertEquals(expected, output)
    }

    @Test
    fun test3() {
        val matrix = listOf(
            listOf(22, 24, -9, 23),
            listOf(12, 10, -19, 35),
            listOf(45, -20, -20, 99),
            listOf(0, 0, 0, 0),
            listOf(0, 0, 0, 0),
            listOf(-100, 200, -50, 5),
            listOf(5, 6, 7, 8)
        )

        val size = 3
        val expected = 176
        val output = maximumSumSubmatrix(matrix, size)
        assertEquals(expected, output)
    }

    fun maximumSumSubmatrix(matrix: List<List<Int>>, size: Int): Int {

        if (matrix.isEmpty()) return 0
        if (matrix.size < size) return 0
        if (matrix[0].size < size) return 0

        var i = 0
        var j = 0
        var maxSum = Int.MIN_VALUE

        while (i < matrix.size) {
            while (j < matrix[0].size) {

                val cellSum = getSum(matrix, i, j, size)
                maxSum = Math.max(maxSum, cellSum)
                j++
            }

            i++
            j = 0
        }

        return maxSum
    }

    private fun getSum(matrix: List<List<Int>>, i: Int, j: Int, size: Int): Int {

        if (i + size - 1 > matrix.lastIndex) return Int.MIN_VALUE
        if (j + size - 1 > matrix[0].lastIndex) return Int.MIN_VALUE

        var total = 0

        (i until i + size).forEach { row ->

            var rowTotal = 0
            (j until j + size).forEach { col ->
                rowTotal += matrix[row][col]
            }

            total += rowTotal
        }

        return total
    }
}