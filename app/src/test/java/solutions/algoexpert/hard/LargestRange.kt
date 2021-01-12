package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Largest%20Range
 */
class LargestRange {

    @Test
    fun test() {
        val list = largestRange(mutableListOf(1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6))
        assertEquals(listOf(0, 7), list)
    }

    @Test
    fun test2() {
        val list = largestRange(mutableListOf(1, 11, 3, 0, 15, 5, 2, 10, 7, 12, 6, 9, 13))
        assertEquals(listOf(9, 13), list)
    }

    class Range(var min: Int, var max: Int)

    fun largestRange(array: List<Int>): Pair<Int, Int> {

        val hash = HashMap<Int, Boolean>()
        array.forEach { arrayItem ->
            hash[arrayItem] = false
        }

        var maxRange = 0
        var maxRangeStart = 0
        var maxRangeEnd = 0

        hash.keys.forEach {

            var currentRangeCount = 0
            var currentRangeStart = 0
            var currentRangeEnd = 0

            if (hash[it] == false) {

                currentRangeCount++

                var check = it - 1
                currentRangeStart = it
                while (hash[check] == false) {
                    currentRangeCount++
                    currentRangeStart = check
                    hash[check] = true
                    check--
                }

                check = it + 1
                currentRangeEnd = it
                while (hash[check] == false) {
                    currentRangeCount++
                    currentRangeEnd = check
                    hash[check] = true
                    check++
                }

                if (currentRangeCount > maxRange) {
                    maxRange = currentRangeCount
                    maxRangeStart = currentRangeStart
                    maxRangeEnd = currentRangeEnd
                }
            }

            hash[it] = true
        }

        return Pair(maxRangeStart, maxRangeEnd)
    }

    fun largestRange2(array: List<Int>): Pair<Int, Int> {

        val rangeList = mutableListOf<Range>()

        array.forEach { arrayItem ->
            var isAdded = false
            rangeList.forEach {
                when {
                    arrayItem == it.min - 1 -> {
                        it.min = arrayItem
                        isAdded = true
                    }

                    arrayItem == it.max + 1 -> {
                        it.max = arrayItem
                        isAdded = true
                    }
                }

                if (!isAdded) {
                    rangeList.add(Range(arrayItem, arrayItem))
                }
            }
        }


        return Pair(-1, -1)
    }

}