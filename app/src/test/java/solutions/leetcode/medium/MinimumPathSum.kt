package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/minimum-path-sum/
 */
class MinimumPathSum {

    @Test
    fun test() {
        val result = minPathSum(
            arrayOf(
                intArrayOf(1, 3, 1),
                intArrayOf(1, 5, 1),
                intArrayOf(4, 2, 1),
                intArrayOf(4, 2, 1),
                intArrayOf(4, 2, 1)
            )
        )

        assertEquals(7, result)
    }

    fun minPathSum(grid: Array<IntArray>): Int {
        return helper(grid, 0, 0, hashMapOf())
    }

    private val MAX = 1000
    fun helper(grid: Array<IntArray>, i: Int, j: Int, mem: HashMap<String, Int>): Int {

        if (i > grid.lastIndex) return MAX
        if (j > grid[0].lastIndex) return MAX

        if (i == grid.lastIndex && j == grid[0].lastIndex) return grid[i][j]

        val key = "$i-$j"
        if (mem[key] != null) {
            return mem[key]!!
        }

        val option1 = helper(grid, i + 1, j, mem)
        mem["${i + 1}-$j"] = option1
        val option2 = helper(grid, i, j + 1, mem)
        mem["$i-${j + 1}"] = option2

        return Math.min(option1, option2) + grid[i][j]
    }
}