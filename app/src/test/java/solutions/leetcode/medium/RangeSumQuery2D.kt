package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 */
class RangeSumQuery2D {

    @Test
    fun test() {
        val matrix = NumMatrix(
            arrayOf(
                intArrayOf(3, 0, 1, 4, 2),
                intArrayOf(5, 6, 3, 2, 1),
                intArrayOf(1, 2, 0, 1, 5),
                intArrayOf(4, 1, 0, 1, 7),
                intArrayOf(1, 0, 3, 0, 5)
            )
        )

        assertEquals(8, matrix.sumRegion(2, 1, 4, 3))
        assertEquals(11, matrix.sumRegion(1, 1, 2, 2))
        assertEquals(12, matrix.sumRegion(1, 2, 2, 4))
    }

    class NumMatrix(val matrix: Array<IntArray>) {

        private var dp: Array<IntArray> = arrayOf()

        init {
            if (matrix.isNotEmpty() && matrix[0].isNotEmpty()) {
                dp = Array(matrix.size) { IntArray(matrix[0].size + 1) }
                for (r in matrix.indices) {
                    for (c in matrix[0].indices) {
                        dp[r][c + 1] = dp[r][c] + matrix[r][c]
                    }
                }
            }
        }

        fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
            var sum = 0
            for (row in row1..row2) {
                sum += dp[row][col2 + 1] - dp[row][col1]
            }
            return sum
        }
    }

}