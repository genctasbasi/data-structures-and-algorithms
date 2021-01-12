package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Min%20Max%20Stack%20Construction
 */

class MinMaxStackConstruction {

    @Test
    fun test() {
        val stack = MinMaxStack()
        stack.push(5)
        Assert.assertEquals(5, stack.getMin())
        Assert.assertEquals(5, stack.getMax())
        Assert.assertEquals(5, stack.peek())

        stack.push(7)
        Assert.assertEquals(5, stack.getMin())
        Assert.assertEquals(7, stack.getMax())
        Assert.assertEquals(7, stack.peek())

        stack.push(2)
        Assert.assertEquals(2, stack.getMin())
        Assert.assertEquals(7, stack.getMax())
        Assert.assertEquals(2, stack.peek())
        Assert.assertEquals(2, stack.pop())
        Assert.assertEquals(7, stack.pop())

        Assert.assertEquals(5, stack.getMin())
        Assert.assertEquals(5, stack.getMax())
        Assert.assertEquals(5, stack.peek())
    }

    open class MinMaxStack() {

        val array = mutableListOf<Int>()
        private val arrayMinimums = mutableListOf<Int>()
        private val arrayMaximums = mutableListOf<Int>()

        fun peek(): Int? {
            return if (array.isEmpty()) -1 else array[array.size - 1]
        }

        fun pop(): Int? {
            if (array.isEmpty()) return -1
            val last = array.last()
            array.removeAt(array.size - 1)
            arrayMinimums.removeAt(arrayMinimums.size - 1)
            arrayMaximums.removeAt(arrayMaximums.size - 1)
            return last
        }

        fun push(number: Int) {
            array.add(number)
            arrayMinimums.add(Math.min(number, getMin()))
            arrayMaximums.add(Math.max(number, getMax()))
        }

        fun getMin(): Int {
            return if (arrayMinimums.isEmpty()) Int.MAX_VALUE else arrayMinimums.last()
        }

        fun getMax(): Int {
            return if (arrayMaximums.isEmpty()) Int.MIN_VALUE else arrayMaximums.last()
        }
    }
}