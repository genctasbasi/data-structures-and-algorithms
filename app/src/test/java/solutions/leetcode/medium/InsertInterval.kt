package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/insert-interval/
 */
class InsertInterval {

    @Test
    fun test() {
        val result = insert(
            arrayOf(
                intArrayOf(1, 3),
                intArrayOf(6, 9)
            ), intArrayOf(2, 5)
        )

        val expected = arrayOf(intArrayOf(1, 5), intArrayOf(6, 9))
        assertArrayEquals(expected, result)
    }

    @Test
    fun test1() {
        val result = insert(
            arrayOf(
                intArrayOf(1, 3),
                intArrayOf(6, 9)
            ), intArrayOf(-1, 7)
        )

        val expected = arrayOf(intArrayOf(-1, 9))
        assertArrayEquals(expected, result)
    }

    @Test
    fun test2() {
        val result = insert(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 5),
                intArrayOf(6, 7),
                intArrayOf(8, 10),
                intArrayOf(12, 16)
            ), intArrayOf(4, 8)
        )

        val expected = arrayOf(intArrayOf(1, 2), intArrayOf(3, 10), intArrayOf(12, 16))
        assertArrayEquals(expected, result)
    }

    @Test
    fun test3() {
        val result = insert(
            arrayOf(
                intArrayOf(1, 5)
            ), intArrayOf(5, 7)
        )

        val expected = arrayOf(intArrayOf(1, 7))
        assertArrayEquals(expected, result)
    }

    @Test
    fun test4() {
        val result = insert(
            arrayOf(
                intArrayOf(1, 5)
            ), intArrayOf(0, 0)
        )

        val expected = arrayOf(intArrayOf(0, 0), intArrayOf(1, 5))
        assertArrayEquals(expected, result)
    }

    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {

        if (intervals.isEmpty()) return arrayOf(newInterval)
        if (newInterval.isEmpty()) return intervals

        val output = mutableListOf<IntArray>()

        var newStart = newInterval[0]
        var newEnd = newInterval[1]

        val treeMap = TreeMap<Int, Int>()

        intervals.forEach {
            treeMap[it[0]] = it[1]
        }

        val floorKeyStart = treeMap.floorKey(newStart)
        if (floorKeyStart != null) {
            if (treeMap[floorKeyStart]!! >= newStart) {
                newStart = floorKeyStart
            }
        }

        val floorKeyEnd =
            treeMap.floorKey(newEnd)

        if (floorKeyEnd != null && treeMap[floorKeyEnd]!! > newEnd) {
            newEnd = treeMap[floorKeyEnd]!!
        }

        treeMap.subMap(newStart, true, newEnd, true).clear()
        treeMap[newStart] = newEnd

        treeMap.keys.forEach {
            output.add(intArrayOf(it, treeMap[it]!!))
        }

        return output.toTypedArray()
    }

    fun insert2(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {

        if (intervals.isEmpty()) return arrayOf(newInterval)
        if (newInterval.isEmpty()) return intervals

        val output = intervals.toMutableList()

        val newStart = newInterval[0]
        val newEnd = newInterval[1]

//        // add to beginning
//        if (intervals[0][0] > newEnd) {
//            output.add(0, newInterval)
//            return output.toTypedArray()
//        }
//
//        // add to end
//        if (intervals.last()[1] < newStart) {
//            output.add(newInterval)
//            return output.toTypedArray()
//        }

        var index = 0
        while (index <= intervals.lastIndex) {

            val interval = intervals[index]
            val start = interval[0]
            val end = interval[1]

            if (newStart <= start) {

                if (end >= newEnd) {
                    interval[0] = newStart
                    return output.toTypedArray()
                }



                while (end < newEnd) {


                }
            }


            index++
        }

        return output.toTypedArray()
    }
}