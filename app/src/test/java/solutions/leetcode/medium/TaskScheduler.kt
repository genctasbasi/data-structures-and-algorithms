package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/task-scheduler/
 */
class TaskScheduler {

    /**
     * TODO WIP: There is that edge case failing the test.
     */
    @Test
    fun test() {
        val result = leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 2)
        assertEquals(8, result)
    }

    @Test
    fun test1() {
        val result = leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 1)
        assertEquals(6, result)
    }

    @Test
    fun test2() {
        val result = leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 0)
        assertEquals(6, result)
    }

    @Test
    fun test3() {
        val result = leastInterval(
            charArrayOf('B', 'C', 'D', 'A', 'A', 'A', 'A', 'A', 'A', 'E', 'F', 'G'),
            // charArrayOf('A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'),
            2
        )
        assertEquals(16, result)
    }

    @Test
    fun test4() {
        val result = leastInterval(
            charArrayOf('A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'),
            // charArrayOf('A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'),
            2
        )
        assertEquals(16, result)
    }

    fun leastInterval(tasks: CharArray, n: Int): Int {

        if (n == 0) return tasks.size

        val counts = linkedMapOf<Char, Int>()
        tasks.forEach {
            counts[it] = (counts[it] ?: 0) + 1
        }

        // we are interested in
        // 1. what is the highest frequency
        // 2. how many do we have of that highest frequency

        var maxFrequency = 0
        var maxFrequentTask: Char? = null
        var maxFrequencyCount = 0

        counts.forEach {
            val key = it.key
            val value = it.value

            if (value > maxFrequency) {
                maxFrequency = value
                maxFrequencyCount = 1
                maxFrequentTask = key
            } else if (value == maxFrequency) {
                maxFrequencyCount++
            }
        }

        val coolTimeSlots = (maxFrequency - 1) * n
        val countOthers = tasks.count { it != maxFrequentTask }
        return coolTimeSlots + maxFrequency + Math.max(0, countOthers - coolTimeSlots)
    }
}