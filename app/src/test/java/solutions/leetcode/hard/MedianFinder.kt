package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * This can be solved with
 *  O(log(n)) - sorting at each put
 *  O(n) - insertion sort, keeping a sorted list
 *  O(log(n)) - With two heaps that I'm not sure how to do (will learn though)
 */
class MedianFinder {

    @Test
    fun test3() {
        val medianFinder = MedianFinder()
        medianFinder.addNum(1)
        medianFinder.addNum(2)
        assertEquals(1.5, medianFinder.findMedian())
        medianFinder.addNum(3)
        assertEquals(2, medianFinder.findMedian())
    }

    @Test
    fun test4() {
        val medianFinder = MedianFinder()
        medianFinder.addNum(300)
        medianFinder.addNum(2)
        medianFinder.addNum(5)
        medianFinder.addNum(300)
        assertEquals(152.5, medianFinder.findMedian())
    }

    @Test
    fun test5() {
        val medianFinder = MedianFinder()
        medianFinder.addNum(-1)
        medianFinder.addNum(-2)
        medianFinder.addNum(-3)
        assertEquals(-2.0, medianFinder.findMedian())
    }

    class MedianFinder {

        val list = mutableListOf<Int>()

        fun addNum(num: Int) {
            if (list.isEmpty()) {
                list.add(num)
                return
            }

            var p = 0
            while (p <= list.lastIndex) {
                if (list[p] > num) {
                    list.add(p, num)
                    return
                }
                p++
            }

            list.add(num)
        }

        fun findMedian(): Double {
            val count = list.size
            val index: Int = count / 2
            return if (count.rem(2) == 1) {
                list.elementAt(index).toDouble()
            } else {
                (list.elementAt(index).toDouble() +
                        list.elementAt(index - 1)) / 2
            }
        }
    }
}