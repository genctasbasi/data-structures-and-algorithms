package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Monotonic%20Array
 */
class MonotonicArray {

    @Test
    fun test() {
        val list1 = mutableListOf(-1, -5, -10, -1100, -1100, -1101, -1102, -9001)
        Assert.assertEquals(true, isMonotonic(list1))

        val list2 = mutableListOf(-1, -5, -10, -1100, -1100, -1101, -1100, -1102, -9001)
        Assert.assertEquals(false, isMonotonic(list2))
    }

    enum class Direction {
        UP,
        DOWN
    }

    fun isMonotonic(array: List<Int>): Boolean {

        if (array.size < 3) return true
        var direction: Direction? = null

        for (i in 1 until array.size) {

            val previousValue = array[i - 1]
            val currentValue = array[i]

            if (previousValue == currentValue) continue
            if (direction == Direction.UP && currentValue < previousValue) return false
            if (direction == Direction.DOWN && currentValue > previousValue) return false

            direction = if (currentValue > previousValue) Direction.UP else Direction.DOWN
        }

        return true
    }
}