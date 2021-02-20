package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://www.algoexpert.io/questions/Topological%20Sort
 */
class TopologicalSort {

    @Test
    fun test() {
        val sort = topologicalSort(
            listOf(1, 2, 3, 4),
            listOf(listOf(1, 2), listOf(1, 3), listOf(3, 2), listOf(4, 2), listOf(4, 3))
        )

        assertEquals(listOf(1, 4, 3, 2), sort)
    }

    @Test
    fun test2() {
        val sort = topologicalSort(
            listOf(1, 2, 3),
            listOf(listOf(2, 1), listOf(3, 2), listOf(1, 3))
        )

        assertEquals(emptyList<Int>(), sort)
    }

    fun topologicalSort(jobs: List<Int>, deps: List<List<Int>>): List<Int> {

        // build the graph
        val map = mutableMapOf<Int, MutableSet<Int>>()
        deps.forEach { dep ->

            // edge direction
            val from = dep[1]
            val to = dep[0]

            if (map[from] == null) map[from] = mutableSetOf()
            map[from]?.add(to)
        }

        jobs.forEach { job ->
            if (map[job] == null) map[job] = mutableSetOf()
        }

        val output = java.util.LinkedList<Int>()
        val status = hashMapOf<Int, Boolean>()
        map.keys.forEach { key ->
            val isCyclic = dfs(key, map, output, status)
            if (isCyclic) return listOf()
        }

        return output.toList()
    }

    fun dfs(
        key: Int,
        map: MutableMap<Int, MutableSet<Int>>,
        output: LinkedList<Int>,
        status: HashMap<Int, Boolean>
    ): Boolean {

        if (status[key] == true) return true
        if (!output.contains(key)) {

            status[key] = true

            map[key]?.forEach { job ->
                dfs(job, map, output, status)
            }

            status[key] = false
            output.add(key)
        }

        return false
    }

    fun topologicalSort2(jobs: List<Int>, deps: List<List<Int>>): List<Int> {

        val dependencyMap = mutableMapOf<Int, MutableSet<Int>>()

        val jobList = mutableListOf<Int>()

        jobs.forEach {
            dependencyMap[it] = mutableSetOf()
        }

        // create dependency map
        deps.forEach {
            val job = it[1]
            val dependsOn = it[0]
            dependencyMap[job]?.add(dependsOn)
        }

        var hasBeenEdited = true

        while (hasBeenEdited) {

            hasBeenEdited = false
            dependencyMap.forEach {
                if (it.value.isEmpty() && jobList.contains(it.key).not()) {
                    jobList.add(it.key)
                    hasBeenEdited = true
                } else {
                    val valuesToRemove = mutableListOf<Int>()

                    it.value.forEach {
                        if (jobList.contains(it)) {
                            valuesToRemove.add(it)
                        }
                    }

                    valuesToRemove.forEach { remove ->
                        it.value.remove(remove)
                        hasBeenEdited = true
                    }
                }
            }
        }

        return if (jobList.size == jobs.size) jobList else listOf()
    }

}