package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
 */
class LongestLineOfConsecutiveOneInMatrix {

    @Test
    fun test() {

        val grid = arrayOf(
            intArrayOf(0, 1, 1, 0),
            intArrayOf(0, 1, 1, 0),
            intArrayOf(0, 0, 0, 1)
        )

        assertEquals(3, longestLine(grid))
    }

    @Test
    fun test1() {

        val grid = arrayOf(
            intArrayOf(1, 1, 1, 1),
            intArrayOf(0, 1, 1, 0),
            intArrayOf(0, 0, 0, 1)
        )

        assertEquals(4, longestLine(grid))
    }

    fun longestLine(mat: Array<IntArray>): Int {
        if (mat.isEmpty()) return 0

        var ones = 0
        val dp = Array(mat[0].size) { IntArray(4) }

        for (i in mat.indices) {
            var old = 0
            for (j in mat[0].indices) {
                if (mat[i][j] == 1) {
                    dp[j][0] = if (j > 0) dp[j - 1][0] + 1 else 1
                    dp[j][1] = if (i > 0) dp[j][1] + 1 else 1
                    val prev = dp[j][2]
                    dp[j][2] = if (i > 0 && j > 0) old + 1 else 1
                    old = prev
                    dp[j][3] =
                        if (i > 0 && j < mat[0].size - 1) dp[j + 1][3] + 1 else 1
                    ones = Math.max(
                        ones,
                        Math.max(
                            Math.max(
                                dp[j][0],
                                dp[j][1]
                            ), Math.max(dp[j][2], dp[j][3])
                        )
                    )
                } else {
                    old = dp[j][2]
                    dp[j][3] = 0
                    dp[j][2] = dp[j][3]
                    dp[j][1] = dp[j][2]
                    dp[j][0] = dp[j][1]
                }
            }
        }
        return ones
    }

    val dp = hashMapOf<String, Int>()
    fun longestLine4(mat: Array<IntArray>): Int {

        if (mat.isEmpty()) return 0

        var max = 0
        mat.forEachIndexed { rowIndex, rows ->
            rows.forEachIndexed { colIndex, col ->

                if (mat[rowIndex][colIndex] == 1) {

                    (0..7).forEach { direction ->
                        val length = getLength(mat, rowIndex, colIndex, direction)
                        max = Math.max(length, max)
                    }
                }
            }
        }

        return max
    }

    private fun getLength(mat: Array<IntArray>, rowIndex: Int, colIndex: Int, direction: Int): Int {

        val key = "$rowIndex-$colIndex-$direction"
        if (dp[key] != null) return dp[key]!!


        var sum = 0
        var moveIndex = 0
        when (direction) {
            0 -> {
                while (colIndex + moveIndex <= 0 && mat[rowIndex][colIndex + moveIndex] == 1) {
                    sum++
                    moveIndex--
                }
            }

            1 -> {
                while (colIndex + moveIndex <= 0 && mat[rowIndex][colIndex + moveIndex] == 1) {
                    sum++
                    moveIndex--
                }
            }
        }

        return sum
    }

    private fun getNeighbours(
        grid: Array<IntArray>,
        i: Int,
        j: Int
    ): List<Pair<Int, Int>> {

        val neighbours = mutableListOf<Pair<Int, Int>>()

        // top
        if (i != 0) neighbours.add(Pair(i - 1, j))

        // bottom
        if (i != grid.lastIndex) neighbours.add(Pair(i + 1, j))

        // left
        if (j != 0) neighbours.add(Pair(i, j - 1))

        // right
        if (j != grid[0].lastIndex) neighbours.add(Pair(i, j + 1))

        return neighbours
    }
}