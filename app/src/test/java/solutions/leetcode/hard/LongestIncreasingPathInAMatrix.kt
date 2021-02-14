package solutions.leetcode.hard

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
class LongestIncreasingPathInAMatrix {

    @Test
    fun test() {
        val result = longestIncreasingPath(
            arrayOf(
                intArrayOf(9, 9, 4),
                intArrayOf(6, 6, 8),
                intArrayOf(2, 1, 1)
            )
        )
        Assert.assertEquals(4, result)
    }

    @Test
    fun test1() {
        val result = longestIncreasingPath(
            arrayOf(
                intArrayOf(3, 4, 5),
                intArrayOf(3, 2, 6),
                intArrayOf(2, 1, 1)
            )
        )
        Assert.assertEquals(5, result)
    }

    @Test
    fun test2() {
        val result = longestIncreasingPath(
            arrayOf(
                intArrayOf(3, 4, 5),
                intArrayOf(3, 2, 6),
                intArrayOf(2, 2, 1)
            )
        )
        Assert.assertEquals(4, result)
    }

    fun longestIncreasingPath(matrix: Array<IntArray>): Int {

        if (matrix.isEmpty()) return 0

        var max = Int.MIN_VALUE

        for (i in 0..matrix.lastIndex) {
            for (j in 0..matrix[0].lastIndex) {
                val count = helper(matrix, i, j, hashMapOf())
                max = Math.max(max, count)
            }
        }

        return max
    }

    fun helper(matrix: Array<IntArray>, i: Int, j: Int, mem: MutableMap<String, Int>): Int {

        if (matrix[i][j] == -1) return -1

        if (mem["$i-$j"] != null) return mem["$i-$j"]!!

        // visiting, mark
        val originalValue = matrix[i][j]
        matrix[i][j] = -1

        val neighbours = getNeighbours(matrix, i, j)
        var maxNeighbour = 0
        neighbours.forEach {
            if (matrix[it.first][it.second] > originalValue) {
                val neighbourLength = helper(matrix, it.first, it.second, mem)
                mem["${it.first}-${it.second}"] = neighbourLength
                maxNeighbour = Math.max(maxNeighbour, neighbourLength)
            }
        }

        matrix[i][j] = originalValue

        return 1 + maxNeighbour
    }

    private fun getNeighbours(
        matrix: Array<IntArray>,
        i: Int,
        j: Int
    ): List<Pair<Int, Int>> {

        val neighbours = mutableListOf<Pair<Int, Int>>()

        // top
        if (i != 0) neighbours.add(Pair(i - 1, j))

        // bottom
        if (i != matrix.lastIndex) neighbours.add(Pair(i + 1, j))

        // left
        if (j != 0) neighbours.add(Pair(i, j - 1))

        // right
        if (j != matrix[0].lastIndex) neighbours.add(Pair(i, j + 1))

        return neighbours
    }
}