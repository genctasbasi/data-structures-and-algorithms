package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/word-search/
 */
class WordSearch {

    @Test
    fun test() {
        val result = exist(
            arrayOf(
                charArrayOf('A', 'B', 'C', 'E'),
                charArrayOf('S', 'F', 'C', 'S'),
                charArrayOf('A', 'D', 'E', 'E')
            ), "ABCCED"
        )
        assertEquals(true, result)
    }

    @Test
    fun test2() {
        val result = exist(
            arrayOf(
                charArrayOf('A', 'B', 'C', 'E'),
                charArrayOf('S', 'F', 'C', 'S'),
                charArrayOf('A', 'D', 'K', 'E')
            ), "SEK"
        )
        assertEquals(true, result)
    }

    @Test
    fun test3() {
        val result = exist(
            arrayOf(
                charArrayOf('A', 'B', 'C', 'E'),
                charArrayOf('S', 'F', 'C', 'S'),
                charArrayOf('A', 'D', 'E', 'E')
            ), "ABCX"
        )
        assertEquals(false, result)
    }

    @Test
    fun test4() {
        val result = exist(
            arrayOf(
                charArrayOf('A', 'B', 'C'),
                charArrayOf('D', 'E', 'F'),
                charArrayOf('G', 'H', 'I')
            ), "X"
        )
        assertEquals(false, result)
    }

    @Test
    fun test5() {
        val result = exist(
            arrayOf(
                charArrayOf('A', 'B', 'C'),
                charArrayOf('D', 'E', 'F'),
                charArrayOf('G', 'H', 'I')
            ), "BEDGH"
        )
        assertEquals(true, result)
    }

    fun exist(board: Array<CharArray>, word: String): Boolean {
        board.forEachIndexed { row, chars ->
            chars.forEachIndexed { col, _ ->
                val hasWord = explore(board, word, mutableListOf(), 0, row, col)
                if (hasWord) return true
            }
        }

        return false
    }

    private fun explore(
        board: Array<CharArray>,
        word: String,
        visited: MutableList<String>,
        index: Int,
        i: Int,
        j: Int
    ): Boolean {

        if (board[i][j] != word[index]) return false
        if (index >= word.lastIndex) return true

        val key = "$i-$j"
        visited.add(key)

        val wordIndex = index + 1

        val neighbours = getNeighbours(board, i, j)

        neighbours.forEach {

            if (!visited.contains("${it.first}-${it.second}"))
                if (board[it.first][it.second] == word[wordIndex]) {
                    val found = explore(board, word, visited, wordIndex, it.first, it.second)
                    if (found) return true
                }
        }

        visited.remove(key)
        return false
    }

    private fun getNeighbours(
        board: Array<CharArray>,
        index: Int,
        charsIndex: Int
    ): List<Pair<Int, Int>> {

        val list = mutableListOf<Pair<Int, Int>>()

        // top
        if (index != 0) list.add(Pair(index - 1, charsIndex))

        // bottom
        if (index != board.lastIndex) list.add(Pair(index + 1, charsIndex))

        // right
        if (charsIndex != board[0].lastIndex) list.add(Pair(index, charsIndex + 1))

        // left
        if (charsIndex != 0) list.add(Pair(index, charsIndex - 1))

        return list
    }
}