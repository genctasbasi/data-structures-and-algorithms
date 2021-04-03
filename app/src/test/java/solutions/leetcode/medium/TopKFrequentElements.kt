package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
class TopKFrequentElements {

    @Test
    fun test() {
        val result =
            topKFrequent(intArrayOf(1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3), 2).toTypedArray()
        assertArrayEquals(arrayOf(2, 3), result)
    }

    @Test
    fun test1() {
        val result =
            topKFrequent(intArrayOf(3, 0, 1, 0), 1).toTypedArray()
        assertArrayEquals(arrayOf(0), result)
    }

    fun topKFrequent(nums: IntArray, k: Int): IntArray {

        val map = hashMapOf<Int, Int>()

        nums.forEach {
            map[it] = (map[it] ?: 0) + 1
        }

        val heap = PriorityQueue<Int> { c1, c2 -> map[c1]!! - map[c2]!! }

        map.keys.forEach {
            heap.offer(it)
            if (heap.size > k) heap.poll()
        }

        val output = mutableListOf<Int>()

        while (heap.isNotEmpty()) {
            output.add(heap.poll())
        }

        return output.toIntArray()
    }
}