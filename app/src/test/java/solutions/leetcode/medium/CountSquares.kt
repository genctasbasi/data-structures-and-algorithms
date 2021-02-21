package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 *
 */
class CountSquares {

    @Test
    fun test() {
        assertEquals(
            15, countSquares(
                arrayOf(
                    intArrayOf(0, 1, 1, 1),
                    intArrayOf(1, 1, 1, 1),
                    intArrayOf(0, 1, 1, 1)
                )
            )
        )
    }

    fun countSquares(matrix: Array<IntArray>): Int {

        if (matrix.isEmpty()) return 0

        val dp = Array(matrix.size + 1) { IntArray(matrix[0].size + 1) { 0 } }
        var count = 0
        matrix.forEachIndexed { i, rows ->

            rows.forEachIndexed { j, value ->

                val dp_i = i + 1
                val dp_j = j + 1

                if (value == 0) {
                    dp[dp_i][dp_j] = 0
                } else {

                    count++ // just because it's 1

                    // newValueFromNeighbours is min(neighbours) + 1
                    val newValueFromNeighbours = (listOf(
                        dp[dp_i - 1][dp_j - 1],
                        dp[dp_i - 1][dp_j],
                        dp[dp_i][dp_j - 1]
                    ).min() ?: 0) + 1

                    val newValue = Math.max(newValueFromNeighbours, 1)
                    dp[dp_i][dp_j] = newValue
                    if (newValue > 1) count += (newValue - 1)    // count twice if it's bigger than 1 (another square)
                }
            }
        }

        return count
    }
}