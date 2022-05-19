package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/course-schedule/
 */
class CourseSchedule2 {

    @Test
    fun test() {
        val order = findOrder(2, arrayOf(intArrayOf(1, 0))).toTypedArray()
        assertArrayEquals(arrayOf(0, 1), order)
    }

    @Test
    fun test1() {
        val order = findOrder(
            5,
            arrayOf(
                intArrayOf(1, 0),
                intArrayOf(2, 0),
                intArrayOf(3, 1),
                intArrayOf(3, 2),
                intArrayOf(0, 9)
            )
        ).toTypedArray()
        assertArrayEquals(arrayOf(0, 2, 1, 3), order)
    }

    private fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {

        val map = mutableMapOf<Int, MutableList<Int>>()

        prerequisites.forEach {
            val from = it[0]
            val to = it[1]

            if (map[from] == null) map[from] = mutableListOf()
            map[from]?.add(to)
        }

        val visiting = hashSetOf<Int>()
        map.keys.forEach {
            if (isCyclic(map, it, visiting)) return intArrayOf()
        }

        (0..numCourses).forEach { list.add(it) }

        return list.toIntArray()
    }

    val list = LinkedHashSet<Int>()

    private fun isCyclic(
        map: MutableMap<Int, MutableList<Int>>,
        value: Int,
        state: HashSet<Int>
    ): Boolean {

        if (state.contains(value)) return true   // re-visiting an element so it's cyclic

        state.add(value)

        map[value]?.forEach {
            if (isCyclic(map, it, state)) return true
        }

        list.add(value)

        state.remove(value)
        return false
    }
}