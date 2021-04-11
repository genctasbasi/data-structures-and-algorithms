package solutions.algoexpert.medium

import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Merge%20Overlapping%20Intervals
 */

class MergeOverlappingIntervals {

    @Test
    fun test() {
        val intervals =
            listOf(listOf(1, 2), listOf(3, 5), listOf(4, 7), listOf(6, 8), listOf(9, 10))
        val expected = listOf(listOf(1, 2), listOf(3, 8), listOf(9, 10))
        val output = mergeOverlappingIntervals(intervals)
        assert(expected == output)
    }

    fun mergeOverlappingIntervals(intervals: List<List<Int>>): List<List<Int>> {

        if (intervals.size < 2) return intervals

        val sorted = intervals.sortedWith(compareBy { it[0] }).toMutableList()
        var p = 1

        while (p <= sorted.lastIndex) {

            val first = sorted[p - 1]
            val second = sorted[p]

            if (first[1] >= second[0] && second[1] >= first[0]) { // that is overlapping
                sorted[p - 1] = listOf(Math.min(first[0], second[0]), Math.max(first[1], second[1]))
                sorted.removeAt(p)
            } else {
                p++
            }
        }

        return sorted
    }
}