package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/path-with-minimum-effort/
 * TODO: Works but with terrible time complexity - Memoisation not working.
 */
class PathWithMinimumEffort {

    @Test
    fun test() {
        val result = minimumEffortPath(
            arrayOf(
                intArrayOf(1, 2, 2),
                intArrayOf(3, 8, 2),
                intArrayOf(5, 3, 5)
            )
        )

        assertEquals(2, result)
    }

    @Test
    fun test1() {
        val result = minimumEffortPath(
            arrayOf(
                intArrayOf(1, 2, 1, 1, 1),
                intArrayOf(1, 2, 1, 2, 1),
                intArrayOf(1, 2, 1, 2, 1),
                intArrayOf(1, 2, 1, 2, 1),
                intArrayOf(1, 1, 1, 2, 1)
            )
        )

        assertEquals(0, result)
    }

    fun minimumEffortPath(heights: Array<IntArray>): Int {
        val mem = mutableMapOf<String, Int>()
        return getEffort(heights, 0, 0, mem)
    }

    private fun getEffort(
        heights: Array<IntArray>,
        row: Int,
        col: Int,
        mem: MutableMap<String, Int>
    ): Int {

        if (row < 0) return Int.MAX_VALUE
        if (col < 0) return Int.MAX_VALUE
        if (row > heights.lastIndex) return Int.MAX_VALUE
        if (col > heights[0].lastIndex) return Int.MAX_VALUE

        val cellEffort = heights[row][col]

        if (cellEffort == -1) return Int.MAX_VALUE
        if (row == heights.lastIndex && col == heights[0].lastIndex) {
            mem["$row-$col"] = 0
            return 0
        }

        // TODO: Mem is not working!
        // if (mem["$row-$col"] != null) return mem["$row-$col"]!!

        heights[row][col] = -1

        val path1 = getEffort(heights, row, col - 1, mem)
        val path2 = getEffort(heights, row - 1, col, mem)
        val path3 = getEffort(heights, row, col + 1, mem)
        val path4 = getEffort(heights, row + 1, col, mem)

        val option1 =
            if (path1 == Int.MAX_VALUE) Int.MAX_VALUE
            else Math.max(path1, Math.abs(cellEffort - heights[row][col - 1]))

        val option2 =
            if (path2 == Int.MAX_VALUE) Int.MAX_VALUE
            else Math.max(path2, Math.abs(cellEffort - heights[row - 1][col]))

        val option3 =
            if (path3 == Int.MAX_VALUE) Int.MAX_VALUE
            else Math.max(path3, Math.abs(cellEffort - heights[row][col + 1]))

        val option4 =
            if (path4 == Int.MAX_VALUE) Int.MAX_VALUE
            else Math.max(path4, Math.abs(cellEffort - heights[row + 1][col]))

        heights[row][col] = cellEffort

        val min = listOf(option1, option2, option3, option4).min()!!

        mem["${row}-${col}"] = min
        return min
    }
}