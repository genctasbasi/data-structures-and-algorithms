package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 */
class AllPathsFromSourceToTarget {

    @Test
    fun test() {

        val graph = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3),
            intArrayOf(3),
            intArrayOf()
        )

        val result = allPathsSourceTarget(graph).toTypedArray()
        val expected = arrayOf(intArrayOf(0, 1, 3), intArrayOf(0, 2, 3))
        assertArrayEquals(expected, result)
    }

    val output = mutableListOf<MutableList<Int>>()
    var last = 0

    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        if (graph.isEmpty()) return listOf()

        val map = mutableMapOf<Int, IntArray>()

        last = graph.lastIndex

        graph.forEachIndexed { index, ints ->
            map[index] = ints
        }

        findPath(map, 0, mutableListOf())

        return output
    }

    private fun findPath(
        map: MutableMap<Int, IntArray>,
        value: Int,
        path: MutableList<Int>
    ) {

        if (value == last) {
            path.add(value)
            output.add(path)
            return
        }

        map[value]?.forEach {
            findPath(map, it, (path + mutableListOf(value)).toMutableList())
        }
    }
}