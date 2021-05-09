package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/number-of-islands/
 * 1: land, 0: water
 */
class NumberOfIslands {

    @Test
    fun test1() {

        val grid = arrayOf(
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '1', '0', '0'),
            charArrayOf('0', '0', '0', '1', '1')
        )

        val count = numIslands(grid)
        assertEquals(3, count)
    }

    fun numIslands(grid: Array<CharArray>): Int {

        var count = 0
        grid.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, char ->
                if (char == '1') {
                    val islandCount = count(grid, rowIndex, colIndex)
                    count += islandCount
                }
            }
        }

        return count
    }

    private fun count(grid: Array<CharArray>, rowIndex: Int, colIndex: Int): Int {

        val char = grid[rowIndex][colIndex]

        // isVisited
        if (char == 'x') return 0
        if (char == '0') return 0

        grid[rowIndex][colIndex] = 'x'  // mark as visited
        val neighbours = getNeighbours(grid, rowIndex, colIndex)

        neighbours.forEach {
            count(grid, it.first, it.second)
        }

        return 1
    }

    private fun getNeighbours(
        grid: Array<CharArray>,
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