package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/summary-ranges/
 */
class SummaryRanges {

    @Test
    fun test() {
        val result = summaryRanges(intArrayOf(0, 1, 2, 4, 5, 7))
        assertEquals(listOf("0->2", "4->5", "7"), result)
    }

    @Test
    fun test2() {
        val result = summaryRanges(intArrayOf(0, 2, 3, 4, 6, 8, 9))
        assertEquals(listOf("0", "2->4", "6", "8->9"), result)
    }

    fun summaryRanges(nums: IntArray): List<String> {

        if (nums.isEmpty()) return listOf()
        if (nums.size == 1) return nums.map { it.toString() }

        val output = mutableListOf<String>()

        var start: Int? = null
        var prev = Int.MIN_VALUE
        nums.forEachIndexed { index, it ->
            if (start == null) {
                start = it
            } else {

                if (it == prev + 1) {
                    // so we just continue
                } else {

                    if (start == prev) {
                        output.add(start.toString())
                    } else {
                        output.add("$start->$prev")
                    }

                    start = it
                }
            }

            prev = it
        }

        if (start == prev) {
            output.add(start.toString())
        } else {
            output.add("$start->$prev")
        }

        return output
    }
}