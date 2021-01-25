package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/relative-ranks/
 */
class RelativeRanks {

    @Test
    fun test() {
        val result = findRelativeRanks(intArrayOf(5, 4, 3, 2, 1, 6))
        assertEquals(arrayOf("Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"), result)
    }

    @Test
    fun test2() {
        val result = findRelativeRanks(intArrayOf(5, 4, 3, 2, 11, 0, 9))
        assertEquals(
            arrayOf("Bronze Medal", "4", "5", "6", "Gold Medal", "7", "Silver Medal"),
            result
        )
    }

    @Test
    fun test3() {
        val result = findRelativeRanks(intArrayOf(5, 4, 3, 2, 11, 0, 9, 12, 1))
        assertEquals(
            arrayOf("4", "5", "6", "7", "Silver Medal", "9", "Bronze Medal", "Gold Medal", "8"),
            result
        )
    }

    fun findRelativeRanks(nums: IntArray): Array<String> {

        val map = mutableMapOf<Int, Int>()
        val output: Array<String> = Array(nums.size) { "" }
        nums.forEachIndexed { index, it ->
            map[it] = index
        }

        val numsSorted = nums.sorted().reversed()
        // 12, 11, 9, 5, 4, 3, 2, 1, 0
        numsSorted.forEachIndexed { index, it ->
            val numIndex = map[it]!!
            output[numIndex] = getRank(index)
        }

        return output
    }

    private fun getRank(index: Int) = when (index) {
        0 -> "Gold Medal"
        1 -> "Silver Medal"
        2 -> "Bronze Medal"
        else -> (index + 1).toString()
    }

    fun findRelativeRanksNoSorting(nums: IntArray): Array<String> {

        if (nums.isEmpty()) return arrayOf()
        val ranks: Array<Int> = Array(nums.size) { 0 }

        var rank = Int.MIN_VALUE
        var prev = Int.MIN_VALUE
        var smallestSoFar = Int.MAX_VALUE
        nums.forEachIndexed { index, it ->

            if (rank == Int.MIN_VALUE) {
                rank = 1
                ranks[index] = rank
            } else {

                if (it < smallestSoFar) {
                    rank += 1
                    ranks[index] = rank
                } else {
                    // now time to backtrack
                    rank = backtrack(ranks, nums, index - 1, it)
                }
            }

            prev = it
            if (it < smallestSoFar)
                smallestSoFar = it
        }

        return ranks.map {
            when (it) {
                1 -> "Gold Medal"
                2 -> "Silver Medal"
                3 -> "Bronze Medal"
                else -> it.toString()
            }
        }.toTypedArray()

    }

    /**
     * return new rank for the new number
     */
    private fun backtrack(ranks: Array<Int>, nums: IntArray, indexStart: Int, newNumber: Int): Int {

        var rankOfNewNumber = Int.MAX_VALUE
        var newMaxRank = Int.MIN_VALUE
        (indexStart downTo 0).forEach { index ->

            if (nums[index] > newNumber) {
                // no need to do anything
            } else {
                if (ranks[index] < rankOfNewNumber) {
                    rankOfNewNumber = ranks[index]
                }

                ranks[index] = ranks[index] + 1   // one 'less' rank

                if (ranks[index] > newMaxRank)
                    newMaxRank = ranks[index]
            }
        }

        // set rank of new number - TODO: This is not pure!
        ranks[indexStart + 1] = rankOfNewNumber
        return newMaxRank
    }
}