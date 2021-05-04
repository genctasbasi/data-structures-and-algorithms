package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 */
class MeetingRooms2 {

    @Test
    fun test() {
        val result = minMeetingRooms(
            arrayOf(
                intArrayOf(0, 30),
                intArrayOf(5, 10),
                intArrayOf(15, 20)
            )
        )

        assertEquals(2, result)
    }

    @Test
    fun test1() {
        val result = minMeetingRooms(
            arrayOf(
                intArrayOf(7, 10),
                intArrayOf(2, 4)
            )
        )

        assertEquals(1, result)
    }

    @Test
    fun test2() {
        val result = minMeetingRooms(
            arrayOf(
                intArrayOf(0, 30),
                intArrayOf(5, 10),
                intArrayOf(15, 20),
                intArrayOf(8, 25)
            )
        )

        assertEquals(3, result)
    }

    fun minMeetingRooms(intervals: Array<IntArray>): Int {

        if (intervals.isEmpty()) return 0

        // sort it by the start, end time (just start time is fine too)
        val sorted = intervals.sortedWith(compareBy({ it[0] }, { it[1] }))

        val minHeap: Queue<Int> = PriorityQueue<Int>()

        var max = 0
        sorted.forEach {
            val start = it[0]
            val end = it[1]
            val peeked = minHeap.peek()
            if (peeked == null) {
                minHeap.offer(end)
            } else {

                if (peeked <= start) {
                    minHeap.poll()
                }

                minHeap.offer(end)
            }

        }

        return minHeap.size
    }
}