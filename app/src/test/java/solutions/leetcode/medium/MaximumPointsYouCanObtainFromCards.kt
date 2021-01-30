package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 */
class MaximumPointsYouCanObtainFromCards {

    @Test
    fun test() {
        val result = maxScore(intArrayOf(1, 2, 3, 4, 5, 6, 1), 3)
        assertEquals(12, result)
    }

    /**
     * Sliding window - that's the optimal solution
     */
    fun maxScore(cardPoints: IntArray, k: Int): Int {

        if (cardPoints.isEmpty()) return 0
        if (k >= cardPoints.size) return cardPoints.sum()

        var max = 0
        var sum = 0

        // get first k element sum
        for (i in 0 until k) {
            max += cardPoints[i]
        }

        sum = max

        for (j in 0 until k) { // 0, 1, 2

            // drop last of beginning
            sum -= cardPoints[k - j - 1]

            // add from end
            sum += cardPoints[cardPoints.lastIndex - j]

            if (sum > max)
                max = sum
        }

        return max
    }

    /**
     * This is a solution with recursion and memoization.
     * Time complexity is k over 2 (no memoization)
     *
     */
    fun maxScore2(cardPoints: IntArray, k: Int): Int {

        if (cardPoints.isEmpty()) return 0
        if (k >= cardPoints.size) return cardPoints.sum()

        return maxScoreHelper(cardPoints, k, 0, cardPoints.lastIndex, mutableMapOf())
    }

    fun maxScoreHelper(
        cardPoints: IntArray,
        k: Int,
        start: Int,
        end: Int,
        mem: MutableMap<String, Int>
    ): Int {

        if (k == 1) return Math.max(cardPoints[start], cardPoints[end])

        val key1 = "$(k-1}-${start + 1}-${end}"
        val key2 = "$(k-1}-$start-${end - 1}"

        val sum1 = if (mem[key1] != null) mem[key1]!! else {
            val memSum = maxScoreHelper(cardPoints, k - 1, start + 1, end, mem)
            mem[key1] = memSum
            memSum
        }

        val sum2 = if (mem[key2] != null) mem[key2]!! else {
            val memSum = maxScoreHelper(cardPoints, k - 1, start, end - 1, mem)
            mem[key2] = memSum
            memSum
        }

        val option1 = cardPoints[start] + sum1
        val option2 = cardPoints[end] + sum2

        return Math.max(option1, option2)
    }

    /**
     * Solution 3.
     * This naive approach didn't work, obviously.
     * It was just picking the bigger integer from either end.
     */
    fun maxScore3(cardPoints: IntArray, k: Int): Int {

        if (cardPoints.isEmpty()) return 0
        if (k >= cardPoints.size) return cardPoints.sum()

        var sum = 0
        var p0 = 0
        var p1 = cardPoints.lastIndex

        (1..k).forEach {

            if (cardPoints[p0] > cardPoints[p1]) {
                sum += cardPoints[p0]
                p0++
            } else {
                sum += cardPoints[p1]
                p1--
            }
        }

        return sum

    }
}