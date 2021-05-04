package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

class HeapPractice {

    @Test
    fun testMin() {

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
    fun testMax() {
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
}