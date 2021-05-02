package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/number-of-closed-islands/
 */
class ClosedIslands {

    @Test
    fun test() {
        val input =
            arrayOf(
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 0),
                intArrayOf(1, 0, 0, 0, 0, 1, 1, 0),
                intArrayOf(1, 0, 1, 0, 1, 1, 1, 0),
                intArrayOf(1, 0, 0, 0, 0, 1, 0, 1),
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 0)
            )

        assertEquals(2, closedIsland(input))
    }

    fun closedIsland(grid: Array<IntArray>): Int {
        var islands = 0
        grid.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, _ ->
                if (
                    grid[rowIndex][colIndex] == 0 &&
                    isClosed(grid, rowIndex, colIndex) == true
                )
                    islands++
            }
        }

        return islands
    }

    private fun isClosed(
        grid: Array<IntArray>,
        rowIndex: Int,
        colIndex: Int
    ): Boolean? {

        if (grid[rowIndex][colIndex] == 1) return true
        if (grid[rowIndex][colIndex] == -1) return false
        if (grid[rowIndex][colIndex] == -2) return null // in progress

        if (grid[rowIndex][colIndex] == 0 && isOnBoundary(grid, rowIndex, colIndex)) {
            grid[rowIndex][colIndex] =
                -1   // -1 is basically 'unusable', meaning we aren't interested in this at all
            return false
        }

        // now we're here, meaning we found a land piece (a '0' in the grid)
        // we're interested if it's 'leaking' if it isn't leaking, meaning that all of its neighbours are either
        // water or another land that is not leaking. let's find this.
        grid[rowIndex][colIndex] = -2   // 'visited'

        val neighbours = getNeighbours(grid, rowIndex, colIndex)
        var isClosed = true
        neighbours.forEach { neighbour ->
            val isCellClosed = isClosed(grid, neighbour.first, neighbour.second)
            if (isCellClosed == false)
                isClosed = false
        }

        return isClosed
    }

    private fun isOnBoundary(grid: Array<IntArray>, rowIndex: Int, colIndex: Int): Boolean {
        return rowIndex == 0 || rowIndex == grid.lastIndex
                || colIndex == 0 || colIndex == grid[0].lastIndex
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