package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/course-schedule/
 */
class CourseSchedule {

    @Test
    fun test() {
        assertEquals(true, canFinish(2, arrayOf(intArrayOf(1, 0))))
    }

    @Test
    fun test1() {
        assertEquals(
            false,
            canFinish(
                2,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(1, 4),
                    intArrayOf(4, 5),
                    intArrayOf(5, 0)
                )
            )
        )
    }

    @Test
    fun test2() {
        assertEquals(
            true, canFinish(
                2,
                arrayOf(
                    intArrayOf(0, 2),
                    intArrayOf(2, 1),
                    intArrayOf(4, 0),
                    intArrayOf(3, 4)
                )
            )
        )
    }

    @Test
    fun test3() {
        assertEquals(
            true, canFinish(
                2,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 9),
                    intArrayOf(4, 5),
                    intArrayOf(8, 9),
                    intArrayOf(9, 0)
                )
            )
        )
    }

    @Test
    fun test4() {
        assertEquals(true, canFinish(2, arrayOf(intArrayOf(1, 0))))
    }

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {

        val graph = hashMapOf<Int, MutableList<Int>>()
        prerequisites.forEachIndexed { index, pair ->

            val course = pair[0]
            val dependsOn = pair[1]

            if (graph[course] == null) graph[course] = mutableListOf()
            graph[course]!!.add(dependsOn)
        }

        val visitStatus = hashMapOf<Int, Int>() // 0: not visited, 1: visited, 2: visiting
        graph.keys.forEach {
            val ic = isCyclic(it, graph, visitStatus)
            if (ic) return false
        }

        return true
    }

    fun isCyclic(
        key: Int,
        graph: MutableMap<Int, MutableList<Int>>,
        visitStatus: HashMap<Int, Int>
    ): Boolean {

        if (visitStatus[key] == 1) return false  // visited
        if (visitStatus[key] == 2) return true  // visiting

        visitStatus[key] = 2   // visiting

        graph[key]?.forEach {   // values it's depending on
            if (isCyclic(it, graph, visitStatus)) return true
        }

        visitStatus[key] = 1    // visited
        return false
    }
}