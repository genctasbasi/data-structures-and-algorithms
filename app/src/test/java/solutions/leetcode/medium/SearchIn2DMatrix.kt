package solutions.leetcode.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 */
class SearchIn2DMatrix {

    @Test
    fun test() {
        val matrix =
            arrayOf(
                intArrayOf(1, 3, 5, 7),
                intArrayOf(10, 11, 16, 20),
                intArrayOf(23, 30, 34, 60),
                intArrayOf(63, 70, 74, 80),
                intArrayOf(83, 88, 86, 90)
            )

        val searchMatrix = searchMatrix(matrix, 8)
        assertEquals(true, searchMatrix)
    }

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {

        if (matrix.isEmpty()) return false

        val firstValue = matrix[0][0]
        val lastValue = matrix[matrix.lastIndex][matrix[0].lastIndex]

        if (target < firstValue || target > lastValue) return false

        val rowToLook = findRow(matrix, 0, matrix.lastIndex, target)
        val listToLook = matrix[rowToLook]

        val isFound = findValue(listToLook, 0, listToLook.lastIndex, target)
        return isFound
    }

    private fun findValue(row: IntArray, start: Int, end: Int, target: Int): Boolean {

        if (start > end) return false
        if (start == end) return row[start] == target
        if (end - start == 1) return row[start] == target || row[end] == target

        val mid = (start + end) / 2
        val value = row[mid]

        return when {
            target < value -> {
                findValue(row, start, mid, target)
            }
            target > value -> {
                findValue(row, mid, end, target)
            }
            else -> true
        }
    }

    private fun findRow(matrix: Array<IntArray>, startRow: Int, endRow: Int, target: Int): Int {

        if (startRow >= endRow) return startRow

        if (endRow - startRow == 1) {
            return if (matrix[endRow][0] > target) startRow else endRow
        }

        val mid = (startRow + endRow) / 2
        val rowValue = matrix[mid][0]

        return when {
            target < rowValue -> {
                findRow(matrix, startRow, mid, target)
            }
            target > rowValue -> {
                findRow(matrix, mid, endRow, target)
            }
            else -> mid
        }

    }
}