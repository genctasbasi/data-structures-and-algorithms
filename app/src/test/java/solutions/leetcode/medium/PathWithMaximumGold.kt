package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/path-with-maximum-gold/
 */
class PathWithMaximumGold {

    @Test
    fun test() {
        val result = getMaximumGold(
            arrayOf(
                intArrayOf(0, 6, 0),
                intArrayOf(5, 8, 7),
                intArrayOf(0, 9, 0)
            )
        )
        assertEquals(24, result)
    }

    @Test
    fun test1() {
        val result = getMaximumGold(
            arrayOf(
                intArrayOf(1, 0, 7, 0, 0, 0),
                intArrayOf(2, 0, 6, 0, 1, 0),
                intArrayOf(3, 5, 6, 7, 4, 2),
                intArrayOf(4, 3, 1, 0, 2, 0),
                intArrayOf(3, 0, 5, 0, 20, 0)
            )
        )
        assertEquals(60, result)
    }

    fun getMaximumGold(grid: Array<IntArray>): Int {

        if (grid.isEmpty()) return 0

        val visited = hashSetOf<String>()
        var max = 0
        grid.forEachIndexed { rowIndex, rows ->
            rows.forEachIndexed { colIndex, col ->
                if (grid[rowIndex][colIndex] != 0) {
                    val sum = getSum(grid, rowIndex, colIndex)
                    max = Math.max(max, sum)
                    visited.clear()
                }
            }
        }

        return max
    }

    private fun getSum(
        grid: Array<IntArray>,
        rowIndex: Int,
        colIndex: Int
    ): Int {

        if (grid[rowIndex][colIndex] == -1) return 0

        val original = grid[rowIndex][colIndex]
        grid[rowIndex][colIndex] = -1
        var maxNeighbour = 0
        val neighbours = getNeighbours(grid, rowIndex, colIndex)

        neighbours.forEach {
            if (grid[it.first][it.second] != 0) {
                val sum = getSum(grid, it.first, it.second)
                maxNeighbour = Math.max(maxNeighbour, sum)
            }
        }

        grid[rowIndex][colIndex] = original
        return maxNeighbour + grid[rowIndex][colIndex]
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