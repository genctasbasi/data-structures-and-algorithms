package solutions.leetcode.medium

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/time-needed-to-inform-all-employees/
 */
class TimeNeededToInformAllEmployees {

    @Test
    fun test() {
        val result = numOfMinutes(6, 2, intArrayOf(2, 2, -1, 2, 2, 2), intArrayOf(0, 0, 1, 0, 0, 0))
        assertEquals(1, result)
    }

    @Test
    fun test2() {
        val result = numOfMinutes(1, 0, intArrayOf(-1), intArrayOf(0))
        assertEquals(0, result)
    }

    @Test
    fun test3() {
        val result = numOfMinutes(
            15,
            0,
            intArrayOf(-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6),
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0)
        )
        assertEquals(3, result)
    }

    @Test
    fun test4() {
        val result =
            numOfMinutes(
                8,
                6,
                intArrayOf(1, 2, 3, 4, 5, 6, -1, 5),
                intArrayOf(0, 6, 5, 4, 3, 2, 1, 10)
            )
        assertEquals(21, result)
    }

    fun numOfMinutes(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int {

        if (manager.isEmpty() || informTime.isEmpty()) return 0

        val map = hashMapOf<Int, MutableList<Int>>() // dependency graph

        (0 until n).forEach {
            val managerIndex = manager[it]
            if (managerIndex != -1) {
                if (map[managerIndex] == null) map[managerIndex] = mutableListOf()
                map[managerIndex]?.add(it)
            }
        }

        val mem = hashMapOf<Int, Int>()
        var max = 0
        map.keys.forEachIndexed { _, employee ->
            val childMax = dfs(map, employee, informTime, mem)
            max = Math.max(max, childMax)
        }

        return max
    }

    private fun dfs(
        map: HashMap<Int, MutableList<Int>>,
        employeeIndex: Int,
        informTime: IntArray,
        mem: HashMap<Int, Int>
    ): Int {

        if (mem[employeeIndex] != null) return mem[employeeIndex]!!

        var childMax = 0
        map[employeeIndex]?.forEach {
            val child = dfs(map, it, informTime, mem)
            childMax = Math.max(childMax, child)
        }

        val sum = childMax + informTime[employeeIndex]
        mem[employeeIndex] = sum
        return sum
    }
}