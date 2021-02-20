package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Task%20Assignment
 *
 */
class TaskAssignment {

    @Test
    fun testCase1() {
        val result = taskAssignment(3, listOf(1, 3, 5, 3, 1, 4))
        assertEquals(
            listOf(
                listOf(0, 2),
                listOf(4, 5),
                listOf(1, 3)
            ), result
        )
    }

    fun taskAssignment(k: Int, tasks: List<Int>): List<List<Int>> {

        val map = mutableMapOf<Int, Int>()
        tasks.forEachIndexed { index, it ->
            map[index] = it
        }

        val list = mutableListOf<List<Int>>()
        val sorted = tasks.sorted()

        var p0 = 0
        var p1 = sorted.lastIndex

        while (p0 < p1) {

            val p0Index = map.filter { it.value == sorted[p0] }.keys.first()
            map.remove(p0Index)

            val p1Index = map.filter { it.value == sorted[p1] }.keys.first()
            map.remove(p1Index)

            list.add(listOf(p0Index, p1Index))
            
            p0++
            p1--
        }

        return list
    }
}