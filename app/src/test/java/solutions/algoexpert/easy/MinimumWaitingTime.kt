package solutions.algoexpert.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Minimum%20Waiting%20Time
 */
class MinimumWaitingTime {

    @Test
    fun test() {
        Assert.assertEquals(
            17,
            minimumWaitingTime(mutableListOf(3, 2, 1, 2, 6))
        )
    }

    fun minimumWaitingTime(queries: MutableList<Int>): Int {

        val sorted = queries.sorted()

        var totalTime = 0
        var addToNext = 0
        sorted.forEach {
            totalTime += addToNext
            addToNext += it
        }

        return totalTime
    }

}