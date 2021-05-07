package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * Practice tests
 */
class Sandbox {

    @Test
    fun `min heap`() {

        val minHeap: Queue<Int> = PriorityQueue<Int>()
        minHeap.offer(10)
        minHeap.offer(5)
        assertEquals(5, minHeap.peek())

        minHeap.offer(2)
        assertEquals(2, minHeap.peek())

        val head = minHeap.poll()
        assertEquals(2, head)
        assertEquals(5, minHeap.peek())
        assertEquals(2, minHeap.size)
    }

    @Test
    fun `max heap`() {
        val maxHeap: Queue<Int> = PriorityQueue<Int>(Collections.reverseOrder())
        maxHeap.offer(5)
        maxHeap.offer(10)

        assertEquals(10, maxHeap.peek())

        maxHeap.offer(20)
        assertEquals(20, maxHeap.peek())

        val head = maxHeap.poll()
        assertEquals(20, head)
        assertEquals(10, maxHeap.peek())
        assertEquals(2, maxHeap.size)
    }

    @Test
    fun `tree map`() {

        val map = TreeMap<Int, Int>()   // It's a red-black tree implementation
        map[10] = 20
        map[15] = 25

        map.subMap(10, false, 20, true).clear()
        assertEquals(10, map.floorKey(15))
    }
}