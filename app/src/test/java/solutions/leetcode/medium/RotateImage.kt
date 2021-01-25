package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/rotate-image/
 */
class RotateImage {

    @Test
    fun test() {
        val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )
        val result = rotate(matrix)
        val expected =
            arrayOf(
                intArrayOf(7, 4, 1),
                intArrayOf(8, 5, 2),
                intArrayOf(9, 6, 3)
            )
        assertEquals(expected, result)
    }

    @Test
    fun test2() {
        val matrix = arrayOf(
            intArrayOf(5, 1, 9, 11),
            intArrayOf(2, 4, 8, 10),
            intArrayOf(13, 3, 6, 7),
            intArrayOf(15, 14, 12, 16)
        )
        rotate(matrix)
        val expected =
            arrayOf(
                intArrayOf(15, 13, 2, 5),
                intArrayOf(14, 3, 4, 1),
                intArrayOf(12, 6, 8, 9),
                intArrayOf(16, 7, 10, 11)
            )

        (matrix.indices).forEach { row ->
            (matrix.indices).forEach { col ->
                assertEquals(expected[row][col], matrix[row][col])
            }
        }
    }

    fun rotate(matrix: Array<IntArray>): Unit {
        val levelCount = (matrix.size + 1) / 2

        val backup1 = mutableMapOf<Int, Int>()
        val backup2 = mutableMapOf<Int, Int>()
        val backup3 = mutableMapOf<Int, Int>()
        val backup4 = mutableMapOf<Int, Int>()

        (0 until levelCount).forEach { level ->

            val size = matrix.size - (level * 2)

            backup1.clear()
            backup2.clear()
            backup3.clear()
            backup4.clear()

            (0 until size).forEach { index ->
                backup1[index] = matrix[level + index][size + level - 1]     // right col
                backup2[index] = matrix[level + size - 1][level + index]     // last row
                backup3[index] = matrix[level + index][level]     // first col
                backup4[index] = matrix[level][level + index]     // first row?
            }

            // place last col
            (size - 1 downTo 0).forEach { index ->
                matrix[index + level][size + level - 1] = backup4[index] ?: 0
            }

            // place last row
            (size - 1 downTo 0).forEach { index ->
                matrix[size + level - 1][size + level - 1 - index] = backup1[index] ?: 0
            }

            // place first col
            (0 until size).forEach { index ->
                matrix[index + level][level] = backup2[index] ?: 0
            }

            // place first row
            (size - 1 downTo 0).forEach { index ->
                matrix[level][size + level - 1 - index] = backup3[index] ?: 0
            }
        }
    }
}