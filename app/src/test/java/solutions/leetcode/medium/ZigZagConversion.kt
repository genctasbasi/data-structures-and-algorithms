package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-palindromic-substring/submissions/
 */
class ZigZagConversion {

    @Test
    fun test() {
        val result = convert("PAYPALISHIRING", 4)
        assertEquals("PAHNAPLSIIGYIR", result)
    }

    enum class Direction { DOWN, UP }

    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s

        var direction = Direction.DOWN
        var currentRow = 0
        val rows = mutableMapOf<Int, MutableList<Char>>()

        s.forEachIndexed { index, char ->

            if (rows[currentRow] == null) rows[currentRow] = mutableListOf()
            rows[currentRow]!!.add(char)

            when (direction) {
                Direction.DOWN -> {
                    currentRow++
                    if (currentRow == numRows) {
                        direction = Direction.UP
                        currentRow -= 2
                    }
                }
                Direction.UP -> {
                    currentRow--

                    if (currentRow == -1) {
                        direction = Direction.DOWN
                        currentRow += 2
                    }
                }
            }
        }

        // combine
        val sb = StringBuilder()
        rows.values.forEach {
            it.forEach {
                sb.append(it)
            }
        }

        return sb.toString()
    }
}