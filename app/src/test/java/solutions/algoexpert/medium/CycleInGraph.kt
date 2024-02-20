package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Cycle%20In%20Graph
 */
class CycleInGraph {

    @Test
    fun testCase() {

        val edges = listOf(
            listOf(1, 2),
            listOf(2),
            listOf()
        )

        assertEquals(false, cycleInGraph(edges))
    }

    @Test
    fun testCase1() {

        val edges = listOf(
            listOf(1, 2),
            listOf(2),
            listOf(0)
        )

        assertEquals(true, cycleInGraph(edges))
    }

    @Test
    fun testCase2() {

        val edges = listOf(
            listOf(1, 2),
            listOf(3, 4),
            listOf(4),
            listOf(),
            listOf(5),
            listOf()
        )

        assertEquals(false, cycleInGraph(edges))
    }

    @Test
    fun testCase3() {

        val edges = listOf(
            listOf(1, 3),
            listOf(2, 3, 4),
            listOf(0),
            listOf(),
            listOf(2, 5),
            listOf()
        )

        assertEquals(true, cycleInGraph(edges))
    }

    private fun cycleInGraph(edges: List<List<Int>>): Boolean {

        val status = hashMapOf<Int, MutableSet<Int>>()
        val visited = Array(edges.size) { 0 }
        buildGraph(edges, status)

        edges.forEachIndexed { index, list ->
            if (isCyclic(index, status, visited)) return true
        }

        return false
    }

    // 1: visited, 2: visiting
    private fun isCyclic(
        index: Int,
        map: HashMap<Int, MutableSet<Int>>,
        visited: Array<Int>
    ): Boolean {

        if (visited[index] == 2) return true
        visited[index] = 2
        map[index]!!.forEachIndexed { _, it ->
            val isCyclic = isCyclic(it, map, visited)
            if (isCyclic) return true
        }

        visited[index] = 1
        return false
    }

    private fun buildGraph(edges: List<List<Int>>, map: HashMap<Int, MutableSet<Int>>) {

        edges.forEachIndexed { index, list ->
            if (map[index] == null)
                map[index] = mutableSetOf()

            list.forEach {
                map[index]!!.add(it)
            }
        }
    }
}