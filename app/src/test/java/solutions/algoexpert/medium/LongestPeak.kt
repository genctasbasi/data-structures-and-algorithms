package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Longest%20Peak
 */
class LongestPeak {

    @Test
    fun test() {
        val list = mutableListOf(1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3)
        Assert.assertEquals(6, longestPeak(list))

        val list2 = mutableListOf(1, 3, 2)
        Assert.assertEquals(3, longestPeak(list2))

        val list3 = mutableListOf(5, 4, 3, 2, 1, 2, 1)
        Assert.assertEquals(3, longestPeak(list3))

        val list4 = mutableListOf(5, 4, 3, 2, 1, 2, 10, 12)
        Assert.assertEquals(0, longestPeak(list4))
    }

    enum class Direction {
        UP,
        DOWN,
        SAME
    }

    fun longestPeak(array: List<Int>): Int {

        if (array.size < 3) return 0

        var direction: Direction? = null

        var peakCount = 0
        var maxPeakCount = 0
        var previousValue = array[0]

        array.forEach {

            when {
                it == previousValue -> {
                    peakCount = 1
                    direction = Direction.SAME
                }

                it > previousValue -> {
                    when (direction) {
                        Direction.DOWN -> {
                            peakCount = 2
                        }
                        Direction.SAME -> {
                            peakCount = 2
                        }
                        else -> {
                            peakCount++
                        }
                    }

                    direction = Direction.UP
                }

                it < previousValue -> {

                    when (direction) {
                        Direction.UP -> {
                            peakCount++
                            direction = Direction.DOWN
                        }
                        Direction.DOWN -> {
                            peakCount++
                        }
                        Direction.SAME -> {
                            peakCount = 1
                        }
                    }
                }
            }

            previousValue = it
            if (direction == Direction.DOWN && peakCount > maxPeakCount) maxPeakCount = peakCount
        }

        return if (maxPeakCount >= 3) maxPeakCount else 0
    }
}