package solutions.leetcode.medium

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 */
class MaxConsecutiveOnesIII {

    @Test
    fun test() {
        assertEquals(
            6,
            longestOnes(intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0), 2)
        )
    }

    @Test
    fun test1() {
        assertEquals(
            10,
            longestOnes(intArrayOf(0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1), 3)
        )
    }

    fun longestOnes(a: IntArray, k: Int): Int {

        if (a.size <= k) return a.size

        if (a.size == 1) return if (a[0] == 1) 1 else Math.min(1, k)

        var max = Int.MIN_VALUE

        var kLeft = k
        var pS = 0
        var pE = 0
        var count = 0   // count of 1s

        while (pE <= a.lastIndex) {
            val numberEnd = a[pE]

            if (numberEnd == 1) {
                count++
                max = Math.max(max, count)
                pE++
            } else {    // it's 0

                if (kLeft > 0) {  // use k
                    kLeft--
                    count++
                    max = Math.max(max, count)
                    pE++
                } else { // we have no k left

                    while (kLeft == 0) {

                        val numberStart = a[pS]

                        if (numberStart == 0) {
                            kLeft++
                        }

                        pS++
                        count--
                    }
                }
            }
        }

        return max
    }
}