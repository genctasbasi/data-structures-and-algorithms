package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/surrounded-regions/
 */
class SurroundedRegions {

    @Test
    fun test() {

        val board = arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'O', 'O', 'X'),
            charArrayOf('X', 'X', 'O', 'X'),
            charArrayOf('X', 'O', 'X', 'X')
        )

        val expected = arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'O', 'X', 'X')
        )

        solve(board)
        assertArrayEquals(expected, board)
    }

    fun solve(board: Array<CharArray>) {

        val rowEnd = board.lastIndex - 1
        val colEnd = board[0].lastIndex - 1

        (1..rowEnd).forEach { rowIndex ->
            (1..colEnd).forEach { colIndex ->
                val cell = board[rowIndex][colIndex]
                if (cell == 'O') {
                    val isTouching = isTouchingSides(board, rowIndex, colIndex)
                    if (!isTouching) board[rowIndex][colIndex] = 'X'
                }
            }
        }
    }

    private fun isTouchingSides(
        board: Array<CharArray>,
        rowIndex: Int,
        colIndex: Int
    ): Boolean {

        val cell = board[rowIndex][colIndex]
        if (cell == 'V') return false // being visited
        if (cell == 'X') return false

        // is on the edge
        if (rowIndex == 0 || rowIndex == board.lastIndex || colIndex == 0 || colIndex == board[0].lastIndex) return true

        val original = board[rowIndex][colIndex]
        board[rowIndex][colIndex] = 'V'

        val neighbours = getNeighbours(board, rowIndex, colIndex)

        neighbours.forEach {
            val isTouchingSides = isTouchingSides(board, it.first, it.second)
            if (isTouchingSides) {
                board[rowIndex][colIndex] = original
                return true
            }
        }

        board[rowIndex][colIndex] = original
        return false
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