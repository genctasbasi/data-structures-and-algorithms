package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/merge-intervals/
 *
 */
class MergeIntervals {

    @Test
    fun test() {
        val result = merge(arrayOf(intArrayOf(1, 4), intArrayOf(4, 5)))
        assertEquals(arrayOf(intArrayOf(1, 5)), result)
    }

    @Test
    fun test2() {
        val result = merge(
            arrayOf(
                intArrayOf(1, 3),
                intArrayOf(2, 6),
                intArrayOf(8, 10),
                intArrayOf(15, 18)
            )
        )
        assertEquals(1, result[0][0])
        assertEquals(6, result[0][1])
        assertEquals(8, result[1][0])
        assertEquals(10, result[1][1])
        assertEquals(15, result[2][0])
        assertEquals(18, result[2][1])
    }

    @Test
    fun test3() {
        merge(
            arrayOf(
                intArrayOf(1, 4),
                intArrayOf(0, 4)
            )
        )
    }

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val output = mutableListOf<IntArray>()

        val map = TreeMap<Int, Int>()

        val sorted = intervals.sortedWith(compareBy { it[0] })

        sorted.forEach {
            val addingFrom = it[0]
            val addingTo = it[1]

            if (map.isEmpty()) {
                map[addingFrom] = addingTo
            } else {

                val floorFrom = map.floorKey(addingFrom)
                val floorTo = if (floorFrom != null) map[floorFrom] else null

                // CASE 0 - same key - (2,3) (2,4)
                if (floorFrom != null && floorTo != null && floorFrom == addingFrom) {
                    if (addingTo > floorTo) {
                        map[floorFrom] = addingTo
                    }   // else - ignore
                }

                // CASE 02 - same key (1, 4) (4, 5)
                else if (floorFrom != null && map[floorFrom] == addingFrom) {
                    map[floorFrom] = addingTo
                }

                // CASE 03 - same key (1, 4) (4, 5)
                else if (floorFrom == null && map.floorKey(addingTo) == addingTo) {
                    val newTo = map[addingTo]!!
                    map.remove(addingTo)
                    map[addingFrom] = newTo
                }

                // CASE 04 - same key (0, 4) (1, 4)
                else if (floorFrom != null && floorFrom < addingFrom && map[floorFrom] == addingTo) {
                    // ignore
                }

                // CASE 1
                else if (floorFrom != null && floorTo != null && floorFrom < addingFrom
                    && floorTo > addingFrom && floorTo < addingTo
                ) {
                    map[floorFrom] = addingTo
                }

                // CASE 2
                else if (floorFrom == null) {
                    val floorFromNew = map.floorKey(addingTo)
                    val ceilingKey = map.ceilingKey(addingFrom)

                    if (floorFromNew != null && floorFromNew < addingTo && map[floorFromNew]!! > addingTo) {
                        val newTo = map[floorFromNew]!!
                        map.remove(floorFromNew)
                        map[addingFrom] = newTo
                    }

                    // CASE 4
                    else if (ceilingKey != null && map[ceilingKey]!! < addingTo) {
                        map.remove(ceilingKey)
                        map[addingFrom] = addingTo
                    }
                }

                // CASE 3
                else if (floorFrom < addingFrom && floorTo != null && floorTo > addingTo) {
                    // don't do anything, simple ignore the new guy
                } else {
                    map[addingFrom] = addingTo
                }

            }
        }

        map.keys.forEach {
            output.add(intArrayOf(it, map[it]!!))
        }

        return output.toTypedArray()
    }
}