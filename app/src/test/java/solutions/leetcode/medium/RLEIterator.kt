package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/rle-iterator/
 */
class RLEIterator {

    @Test
    fun test() {
        val iterator = RLEIterator(intArrayOf(3, 8, 0, 9, 2, 5))

        assertEquals(8, iterator.next(2))
        assertEquals(8, iterator.next(1))
        assertEquals(5, iterator.next(1))
        assertEquals(-1, iterator.next(2))
    }

    class RLEIterator(encoding: IntArray) {

        private val count = mutableListOf<Int>()
        private val number = mutableListOf<Int>()

        init {
            var index = 0
            while (index < encoding.lastIndex) {
                val item = encoding[index]
                if (item > 0) {
                    count.add(item)
                    number.add(encoding[index + 1])
                }
                index += 2
            }
        }

        fun next(n: Int): Int {
            var start = n
            while (start >= 0 && number.isNotEmpty()) {
                val firstItemCount = count[0]
                if (start <= firstItemCount) {
                    count[0] = count[0] - start
                    val returning = number[0]
                    if(count[0] == 0){
                        count.removeAt(0)
                        number.removeAt(0)
                    }
                    return returning
                } else {
                    start -= firstItemCount
                    count.removeAt(0)
                    number.removeAt(0)
                }
            }

            return -1
        }
    }
}