package solutions.other

import org.junit.Test

/**
 *
 * https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
 *
 * You are given a connected undirected graph.
 * Perform a Depth First Traversal of the graph.
 *
 * Note: Use the recursive approach to find the DFS traversal of the graph starting
 * from the 0th vertex from left to right according to the graph.
 *
 */
class BuildGraph {

    @Test
    fun buildGraph() {

        val edges = listOf(
            listOf(1, 3),
            listOf(2, 4),
            listOf(0),
            listOf(),
            listOf(2, 5),
            listOf()
        )

        val graphMap = build(edges)

        dfs(graphMap, 0)

        visited.clear()
        val isCyclic = isCyclic(graphMap, 0)

        println("isCyclic: $isCyclic")
    }

    val visited = mutableSetOf<Int>()

    /**
     * Just DFS and print
     */
    private fun dfs(graphMap: Map<Int, Set<Int>>, item: Int) {

        if (visited.contains(item)) return

        visited.add(item)
        println(item)

        graphMap[item]?.forEach {
            dfs(graphMap, it)
        }
    }

    private fun isCyclic(graphMap: Map<Int, Set<Int>>, item: Int): Boolean {

        if (visited.contains(item)) return true

        visited.add(item)
        println(item)

        graphMap[item]?.forEach {
            val isCyclic = isCyclic(graphMap, it)

            if (isCyclic) return true
        }

        return false
    }

    private fun build(edges: List<List<Int>>): Map<Int, Set<Int>> {

        val graph = mutableMapOf<Int, MutableSet<Int>>()

        edges.forEachIndexed { index, list ->

            if (graph[index] == null) {
                graph[index] = edges[index].toMutableSet()
            } else {
                graph[index]!!.addAll(list)
            }
        }

        return graph
    }
}


