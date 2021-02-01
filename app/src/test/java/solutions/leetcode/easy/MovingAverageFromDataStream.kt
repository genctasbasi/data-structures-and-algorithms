package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/moving-average-from-data-stream/
 */
class MovingAverageFromDataStream {

    @Test
    fun test() {
        val movingAverage = MovingAverage(3)
        assertEquals(1.0, movingAverage.next(1))
        assertEquals(5.5, movingAverage.next(10))
        assertEquals(4.666666666666667, movingAverage.next(3))
        assertEquals(6.0, movingAverage.next(5))
    }

    class MovingAverage(val size: Int) {

        private val queue = LinkedList<Int>()
        private var sum = 0.0

        fun next(`val`: Int): Double {

            if (queue.size == size) {
                val minus = queue.remove()
                sum -= minus
            }

            queue.add(`val`)
            sum += `val`

            return sum / queue.size
        }
    }
}