package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/kth-missing-positive-number/
 */
class KthMissingPositiveNumber {

    @Test
    fun test() {
        assertEquals(9, findKthPositive(intArrayOf(2, 3, 4, 7, 11), 5))
    }

    fun findKthPositive(arr: IntArray, k: Int): Int {

        var prev = 0
        var missedCount = 0
        arr.forEach { num ->
            val diff = num - prev - 1
            if (diff > 0) {

                if (missedCount + diff >= k) {
                    return prev + k - missedCount
                }

                missedCount += diff
            }

            prev = num
        }

        val missing = k - missedCount + arr.last()
        return missing
    }

}