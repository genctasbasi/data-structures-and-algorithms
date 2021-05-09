package solutions.algoexpert.hard

import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Solve%20Sudoku
 */
class SolveSudoku {

    @Test
    fun testCase() {
        val input = mutableListOf(
            mutableListOf(7, 8, 0, 4, 0, 0, 1, 2, 0),
            mutableListOf(6, 0, 0, 0, 7, 5, 0, 0, 9),
            mutableListOf(0, 0, 0, 6, 0, 1, 0, 7, 8),
            mutableListOf(0, 0, 7, 0, 4, 0, 2, 6, 0),
            mutableListOf(0, 0, 1, 0, 5, 0, 9, 3, 0),
            mutableListOf(9, 0, 4, 0, 6, 0, 0, 0, 5),
            mutableListOf(0, 7, 0, 3, 0, 0, 0, 1, 2),
            mutableListOf(1, 2, 0, 0, 0, 7, 4, 0, 0),
            mutableListOf(0, 4, 9, 2, 0, 6, 0, 0, 7)
        )

        val expected = mutableListOf(
            mutableListOf(7, 8, 5, 4, 3, 9, 1, 2, 6),
            mutableListOf(6, 1, 2, 8, 7, 5, 3, 4, 9),
            mutableListOf(4, 9, 3, 6, 2, 1, 5, 7, 8),
            mutableListOf(8, 5, 7, 9, 4, 3, 2, 6, 1),
            mutableListOf(2, 6, 1, 7, 5, 8, 9, 3, 4),
            mutableListOf(9, 3, 4, 1, 6, 2, 7, 8, 5),
            mutableListOf(5, 7, 8, 3, 9, 4, 6, 1, 2),
            mutableListOf(1, 2, 6, 5, 8, 7, 4, 9, 3),
            mutableListOf(3, 4, 9, 2, 1, 6, 8, 5, 7)
        )

        val output = solveSudoku(input)
        assert(expected == output)
    }

    fun solveSudoku(board: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        return solveRec(board, 0, 0) ?: board
    }

    fun solveRec(
        board: MutableList<MutableList<Int>>,
        row: Int,
        col: Int
    ): MutableList<MutableList<Int>>? {

        var currentRow = row
        var currentCol = col

        if (currentCol > board[0].lastIndex) {
            if (currentRow == board.lastIndex) return board // board completed
            currentRow++
            currentCol = 0
        }

        if (board[currentRow][currentCol] != 0) {
            return solveRec(board, currentRow, currentCol + 1)
        }

        (1..9).forEach {
            if (isValidAtPosition(it, currentRow, currentCol, board)) {
                board[currentRow][currentCol] = it
                val isSolved = solveRec(board, currentRow, currentCol + 1)
                if (isSolved != null) return isSolved
            }
        }

        board[currentRow][currentCol] = 0
        return null
    }

    fun isValidAtPosition(
        value: Int,
        row: Int,
        col: Int,
        board: MutableList<MutableList<Int>>
    ): Boolean {

        val rowIsValid = !board[row].contains(value)
        val columnIsValid = !board.map() { r -> r[col] }.contains(value)

        if (!rowIsValid || !columnIsValid) return false

        // Check subgrid constraint.
        val subgridRowStart = (row / 3) * 3
        val subgridColStart = (col / 3) * 3

        for (rowIdx in 0 until 3) {
            for (colIdx in 0 until 3) {

                val rowToCheck = subgridRowStart + rowIdx
                val colToCheck = subgridColStart + colIdx
                val existingValue = board[rowToCheck][colToCheck]

                if (existingValue == value) return false
            }
        }

        return true
    }
}