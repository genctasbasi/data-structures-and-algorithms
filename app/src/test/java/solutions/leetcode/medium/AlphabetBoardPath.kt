package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/alphabet-board-path/
 */
class AlphabetBoardPath {

    @Test
    fun test() {
        assertEquals("RR!DDRR!UUL!R!", alphabetBoardPath("code"))
    }

    @Test
    fun test2() {
        assertEquals("DDDDD!UUUUURRR!DDDDLLLD!", alphabetBoardPath("zdz"))
    }

    private val board = listOf(
        listOf('a', 'b', 'c', 'd', 'e'),
        listOf('f', 'g', 'h', 'i', 'j'),
        listOf('k', 'l', 'm', 'n', 'o'),
        listOf('p', 'q', 'r', 's', 't'),
        listOf('u', 'v', 'w', 'x', 'y'),
        listOf('z', '|', '|', '|', '|')
    )

    /**
     * assumption:
     *  - all the letters I require to form the target exists on the board
     *  - start position is 0,0
     */
    fun alphabetBoardPath(target: String): String {

        if (target.isEmpty()) return ""

        val colLastIndex = board[0].lastIndex
        val moves = StringBuilder()

        var row = 0
        var col = 0

        var targetPointer = 0

        while (targetPointer < target.length) {

            val targetLetter = target[targetPointer]

            if (targetLetter == 'z') {    // go to first column
                while (col > 0) {
                    col--
                    moves.append('L')
                }
            }

            // first check if we're on the correct row
            when {
                targetLetter > board[row][colLastIndex] -> {
                    moves.append('D')
                    row++
                }
                targetLetter < board[row][0] -> {
                    moves.append('U')
                    row--
                }
                targetLetter > board[row][col] -> {
                    moves.append('R')
                    col++
                }
                targetLetter < board[row][col] -> {
                    moves.append('L')
                    col--
                }
                else -> {
                    moves.append('!')
                    targetPointer++
                }
            }
        }

        return moves.toString()
    }
}