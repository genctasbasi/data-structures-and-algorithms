package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Search%20In%20Sorted%20Matrix
 */

val NO_RESULT = Pair(-1, -1)

class SearchInSortedMatrix {

    @Test
    fun test() {
        val matrix = listOf(
            listOf(1, 4, 7, 12, 15, 1000),
            listOf(2, 5, 19, 31, 32, 1001),
            listOf(3, 8, 24, 33, 35, 1002),
            listOf(40, 41, 42, 44, 45, 1003),
            listOf(99, 100, 103, 106, 128, 1004)
        )

        val result = searchInSortedMatrix(matrix, 1)
        Assert.assertEquals(Pair(3, 3), result)
    }

    fun searchInSortedMatrix(matrix: List<List<Int>>, target: Int): Pair<Int, Int> {

        if (matrix.isEmpty()) return NO_RESULT

        var colIndex = matrix[0].size - 1
        var rowIndex = 0

        while (colIndex >= 0 && rowIndex < matrix.size) {

            val currentNumber = matrix[rowIndex][colIndex]

            when {
                currentNumber == target -> return Pair(rowIndex, colIndex)
                currentNumber > target -> colIndex--
                else -> rowIndex++
            }
        }

        return NO_RESULT
    }

}