package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/my-calendar-i/
 */
class MyCalendar {

    @Test
    fun test() {
        val calendar = MyCalendar()
        assertEquals(true, calendar.book(10, 20))
        assertEquals(false, calendar.book(15, 25))
        assertEquals(true, calendar.book(20, 30))
    }

    @Test
    fun test1() {
        val calendar = MyCalendar()
        assertEquals(true, calendar.book(37, 50))
        assertEquals(false, calendar.book(35, 48))
    }

    @Test
    fun test2() {
        val calendar = MyCalendar()

        assertEquals(true, calendar.book(47, 50))
        assertEquals(true, calendar.book(33, 41))
        assertEquals(false, calendar.book(39, 45))
        assertEquals(false, calendar.book(33, 42))
        assertEquals(true, calendar.book(25, 32))
        assertEquals(false, calendar.book(26, 35))
        assertEquals(true, calendar.book(19, 25))
        assertEquals(true, calendar.book(3, 8))
        assertEquals(true, calendar.book(8, 13))
        assertEquals(false, calendar.book(18, 27))
    }

    class MyCalendar {

        private val treeMap = TreeMap<Int, Int>()

        fun book(start: Int, end: Int): Boolean {

            val prevStart = treeMap.floorKey(start)
            val nextStart = treeMap.ceilingKey(start)

            if ((prevStart == null || treeMap[prevStart]!! <= start) &&
                (nextStart == null || nextStart >= end)
            ) {
                treeMap[start] = end
                return true
            }

            return false
        }
    }

    /**
     * O(n)
     */
    class MyCalendar2 {

        val bookings = hashSetOf<Pair<Int, Int>>()
        fun book(start: Int, end: Int): Boolean {

            if (isConflicting(bookings, start, end)) return false

            bookings.add(Pair(start, end))
            return true
        }

        private fun isConflicting(
            bookings: HashSet<Pair<Int, Int>>,
            start: Int,
            end: Int
        ): Boolean {

            bookings.forEach {
                if (start >= it.first && start < it.second) return true
                if (end > it.first && start < it.first) return true
            }

            return false
        }
    }
}