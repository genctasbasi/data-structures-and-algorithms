package solutions.leetcode.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/max-area-of-island/
 */
class MaxAreaOfIsland {

    @Test
    fun test() {
        val input = arrayOf(
            intArrayOf(0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0),
            intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0)
        )

        val result = maxAreaOfIsland(input)
        Assert.assertEquals(6, result)
    }

    fun maxAreaOfIsland(grid: Array<IntArray>): Int {

        var max = Int.MIN_VALUE

        grid.forEachIndexed { rowIndex, rows ->
            rows.forEachIndexed { colIndex, it ->
                val count = explore(grid, rowIndex, colIndex, 0)
                if (count > max)
                    max = count
            }
        }

        return max
    }

    fun explore(grid: Array<IntArray>, row: Int, col: Int, landCount: Int): Int {

        // it's either zero or visited
        if (grid[row][col] == 0 || grid[row][col] == -1) return 0

        // val count +   // since now we know it's a 1
        grid[row][col] = -1 // make it 'visited'
        val neighbours = getNeighbours(grid, row, col)

        var count = landCount + 1
        neighbours.forEach {
            if (grid[it.first][it.second] == 1) {
                count += explore(grid, it.first, it.second, landCount)
            }
        }

        return count
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