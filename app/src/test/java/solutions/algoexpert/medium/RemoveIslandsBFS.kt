package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://www.algoexpert.io/questions/Remove%20Islands
 *
 */
class RemoveIslandsBFS {

    @Test
    fun testCase1() {
        val input = listOf(
            mutableListOf(1, 0, 0, 0, 0, 0),
            mutableListOf(0, 1, 0, 1, 1, 1),
            mutableListOf(0, 0, 1, 0, 1, 0),
            mutableListOf(1, 1, 0, 0, 1, 0),
            mutableListOf(1, 0, 1, 1, 0, 0),
            mutableListOf(1, 0, 0, 0, 0, 1)
        )
        val result = removeIslands(input)

        assertEquals(
            listOf(
                listOf(1, 0, 0, 0, 0, 0),
                listOf(0, 0, 0, 1, 1, 1),
                listOf(0, 0, 0, 0, 1, 0),
                listOf(1, 1, 0, 0, 1, 0),
                listOf(1, 0, 0, 0, 0, 0),
                listOf(1, 0, 0, 0, 0, 1)
            ), result
        )
    }

    private fun removeIslands(matrix: List<MutableList<Int>>): List<MutableList<Int>> {
        return explore(matrix, mutableListOf())
    }

    private fun explore(
        matrix: List<MutableList<Int>>,
        visited: MutableList<String>
    ): List<MutableList<Int>> {

        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.add(Pair(0, 0))

        while (queue.isNotEmpty()) {

            val pair = queue.remove()

            val key = "${pair.first}-${pair.second}"

            if (visited.contains(key)) continue
            visited.add(key)
            print("$key, ")

            if (matrix[pair.first][pair.second] == 1
                && !isConnected(matrix, pair.first, pair.second)
            ) {
                matrix[pair.first][pair.second] = 0
            }

            val neighbours = getNeighbours(matrix, pair.first, pair.second)

            neighbours.forEach {
                queue.add(it)
            }
        }

        return matrix
    }

    private fun isConnected(
        matrix: List<MutableList<Int>>, i: Int, j: Int,
        visited: MutableList<String> = mutableListOf()
    ): Boolean {

        if (matrix[i][j] == 0) return false

        val key = "$i-$j"
        if (visited.contains(key)) return false

        if (i == 0 && matrix[i][j] == 1) return true
        if (i == matrix.lastIndex && matrix[i][j] == 1) return true
        if (j == 0 && matrix[i][j] == 1) return true
        if (j == matrix[0].lastIndex
            && matrix[i][j] == 1
        ) return true

        visited.add("$i-$j")
        val neighbours = getNeighbours(matrix, i, j)

        neighbours.forEach {
            if (matrix[it.first][it.second] == 1
                && isConnected(matrix, it.first, it.second, visited)
            ) return true
        }

        return false
    }

    private fun getNeighbours(
        matrix: List<MutableList<Int>>,
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