package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/snapshot-array/
 */
class SnapshotArray {

    @Test
    fun test() {
        val arr = SnapshotArray(3)
        arr.set(0, 5)
        arr.snap()
        arr.set(0, 6)
        assertEquals(5, arr.get(0, 0))
    }

    class SnapshotArray(val length: Int) {

        var snapId = -1
        private val currentMap = hashMapOf<Int, Int>()
        val map = mutableMapOf<Int, MutableMap<Int, Int>>()

        fun set(index: Int, `val`: Int) {
            currentMap[index] = `val`
        }

        fun snap(): Int {

            snapId++

            currentMap.keys.forEachIndexed { _, it ->
                if (map[it] == null)
                    map[it] = mutableMapOf()

                map[it]!![snapId] = currentMap[it]!!
            }

            return snapId
        }

        fun get(index: Int, snap_id: Int): Int {
            return map[index]?.get(snap_id) ?: 0
        }
    }
}