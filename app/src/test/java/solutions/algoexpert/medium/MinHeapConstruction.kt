package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Min%20Heap%20Construction
 */
class MinHeapConstruction {

    @Test
    fun testCase1() {

        val buildList = mutableListOf(48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41)
        val minHeap = MinHeap(buildList)

        minHeap.insert(76)
        assertEquals(-5, minHeap.peek())

        assertEquals(-5, minHeap.remove())
        assertEquals(2, minHeap.peek())
        assertEquals(2, minHeap.remove())
        assertEquals(6, minHeap.peek())

        minHeap.insert(87)
    }

    @Test
    fun testCase2() {

        val buildList = mutableListOf(
            -823,
            164,
            48,
            -987,
            323,
            399,
            -293,
            183,
            -908,
            -376,
            14,
            980,
            965,
            842,
            422,
            829,
            59,
            724,
            -415,
            -733,
            356,
            -855,
            -155,
            52,
            328,
            -544,
            -371,
            -160,
            -942,
            -51,
            700,
            -363,
            -353,
            -359,
            238,
            892,
            -730,
            -575,
            892,
            490,
            490,
            995,
            572,
            888,
            -935,
            919,
            -191,
            646,
            -120,
            125,
            -817,
            341,
            -575,
            372,
            -874,
            243,
            610,
            -36,
            -685,
            -337,
            -13,
            295,
            800,
            -950,
            -949,
            -257,
            631,
            -542,
            201,
            -796,
            157,
            950,
            540,
            -846,
            -265,
            746,
            355,
            -578,
            -441,
            -254,
            -941,
            -738,
            -469,
            -167,
            -420,
            -126,
            -410,
            59
        )
        val minHeap = MinHeap(buildList)

        minHeap.insert(2)
        assertEquals(-5, minHeap.peek())

        assertEquals(-5, minHeap.remove())
        assertEquals(2, minHeap.peek())
        assertEquals(2, minHeap.remove())
        assertEquals(6, minHeap.peek())

        minHeap.insert(87)
    }

    open class MinHeap(array: MutableList<Int>) {
        val heap = this.buildHeap(array)

        fun buildHeap(array: MutableList<Int>): MutableList<Int> {

            val smallestParentIndex: Int = (array.size - 1) / 2

            for (index in smallestParentIndex downTo 0) {
                siftDown(index, array.size - 1, array)
            }

            return array
        }

        fun siftDown(currentIdx: Int, endIdx: Int, heap: MutableList<Int>) {

            var newCurrentIdx = currentIdx

            while (newCurrentIdx < endIdx) {

                val child1Index = newCurrentIdx * 2 + 1
                val child2Index = newCurrentIdx * 2 + 2

                if (child1Index > heap.size - 1)
                    return

                val child1 = heap[child1Index]
                val child2 = if (child2Index < heap.size) heap[child2Index] else Int.MAX_VALUE

                val minChildIndex = if (child1 < child2) child1Index else child2Index

                if (heap[newCurrentIdx] > heap[minChildIndex]) {
                    val temp = heap[newCurrentIdx]
                    heap[newCurrentIdx] = heap[minChildIndex]
                    heap[minChildIndex] = temp
                } else {
                    return
                }

                newCurrentIdx = minChildIndex
            }
        }

        fun siftUp(currentIdx: Int, heap: MutableList<Int>) {

            var newCurrentIdx = currentIdx

            while (newCurrentIdx > 0) {
                val value = heap[newCurrentIdx]
                val parentIndex: Int = (newCurrentIdx - 1) / 2
                if (parentIndex < 0) return
                val parent = heap[parentIndex]

                // swap
                if (value < parent) {
                    val temp = heap[newCurrentIdx]
                    heap[newCurrentIdx] = heap[parentIndex]
                    heap[parentIndex] = temp
                } else {
                    return
                }

                newCurrentIdx = parentIndex
            }
        }

        fun peek(): Int? {
            return if (heap.isEmpty()) null else heap[0]
        }

        fun remove(): Int? {
            if (heap.isEmpty()) return null

            val minHeap = heap[0]
            heap[0] = heap[heap.size - 1]
            heap.removeAt(heap.size - 1)

            siftDown(0, heap.size - 1, heap)
            return minHeap
        }

        fun insert(value: Int) {
            // add value to the end of the array and sift it up
            heap.add(heap.size, value)
            siftUp(heap.size - 1, heap)
            val a = 2
        }
    }
}