package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/as-far-from-land-as-possible/
 */
class AsFarFromLandAsPossible {

    @Test
    fun test() {

        // 0: water, 1: land
        val grid = arrayOf(
            intArrayOf(1, 0, 1),
            intArrayOf(0, 0, 0),
            intArrayOf(1, 0, 1)
        )

        assertEquals(2, maxDistance(grid))
    }

    @Test
    fun test1() {

        val grid = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 1)
        )

        assertEquals(4, maxDistance(grid))
    }

    @Test
    fun test2() {

        val grid = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 1)
        )

        assertEquals(0, maxDistance(grid))
    }

    fun maxDistance(grid: Array<IntArray>): Int {

        var i = 0
        val queue = LinkedList<Pair<Int, Int>>()

        val visited = hashSetOf<String>()
        while (i <= grid.lastIndex) {

            var j = 0
            while (j <= grid[0].lastIndex) {
                if (grid[i][j] == 1) {
                    queue.add(Pair(i, j))
                    visited.add("$i-$j")
                    grid[i][j] = 0  // because we'll start counting based on this cell value
                }
                j++
            }
            i++
        }

        // check if all land:
        if (queue.size == grid.size) return -1   // no water

        var max = 0
        while (queue.isNotEmpty()) {

            val popped = queue.pop()

            val neighbours = getNeighbours(grid, popped.first, popped.second)
            neighbours.forEach {

                val key = "${it.first}-${it.second}"
                if (visited.contains(key).not()) {    // not visited yet
                    queue.add(Pair(it.first, it.second))
                    visited.add(key)
                    val newDistance = grid[popped.first][popped.second] + 1
                    grid[it.first][it.second] = newDistance
                    max = Math.max(max, newDistance)
                }
            }
        }

        return if (max == 0) -1 else max
    }

    /**
     * This failed in 1 test case (out of 35) not sure why.
     */
    fun `maxDistance DFS`(grid: Array<IntArray>): Int {

        var i = 0
        var j = 0

        var max = Int.MIN_VALUE

        val mem = hashMapOf<String, Int>()
        while (i <= grid.lastIndex) {
            while (j <= grid[0].lastIndex) {
                val distance = visit(grid, i, j, mem)
                max = Math.max(max, distance)
                j++
            }
            i++
            j = 0
        }
        if (max == 0) return -1 // doesn't exist
        return if (max == Int.MAX_VALUE) -1 else max
    }

    private fun visit(grid: Array<IntArray>, i: Int, j: Int, mem: HashMap<String, Int>): Int {

        val key = "$i-$j"
        if (mem[key] != null) return mem[key]!!

        if (grid[i][j] == 1) {
            mem[key] = 0
            return 0
        }

        if (grid[i][j] == -1) return -1  // this is visited

        val originalValue = grid[i][j]
        grid[i][j] = -1

        val neighbours = getNeighbours(grid, i, j)

        var minNeighbour = Int.MAX_VALUE

        // do any neighbour is a land
        val hasLandNeighbour =
            neighbours.any { grid[it.first][it.second] == 1 }

        if (hasLandNeighbour) {
            grid[i][j] = originalValue
            mem[key] = 1
            return 1
        }

        neighbours.forEach {
            val keyNeighbour = "${it.first}-${it.second}"

            val distance = visit(grid, it.first, it.second, mem)

            if (distance != -1) {
                mem[keyNeighbour] = distance
                minNeighbour = Math.min(minNeighbour, distance)
            }
        }

        grid[i][j] = originalValue
        val min = if (minNeighbour == Int.MAX_VALUE) minNeighbour else minNeighbour + 1
        mem[key] = min
        return min
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