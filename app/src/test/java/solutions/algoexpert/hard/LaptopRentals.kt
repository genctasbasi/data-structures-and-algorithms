package solutions.algoexpert.hard

import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Laptop%20Rentals
 * Similar to MeetingRooms2: https://leetcode.com/problems/meeting-rooms-ii/
 * Can be (and should be!) solved in O(nlog(n))
 *
 */
class LaptopRentals {

    @Test
    fun test() {
        val input = listOf(
            listOf(0, 2),
            listOf(1, 4),
            listOf(4, 6),
            listOf(0, 4),
            listOf(7, 8),
            listOf(9, 11),
            listOf(3, 10)
        )
        val expected = 3
        val output = laptopRentals(input)
        assert(expected == output)
    }

    fun laptopRentals(times: List<List<Int>>): Int {

        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE

        times.forEach {
            min = Math.min(min, it[0])
            max = Math.max(max, it[1])
        }

        var maxCut = 0
        var cutCurrent = 0
        (min..max).forEach { cutter ->

            times.forEach {
                if (cutter >= it[0] && cutter < it[1]) {
                    cutCurrent++
                }
            }

            maxCut = Math.max(maxCut, cutCurrent)
            cutCurrent = 0
        }

        return maxCut
    }
}