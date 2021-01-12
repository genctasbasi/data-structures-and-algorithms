package solutions.hackerRank.arrays

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Array Challenges from
 * https://www.hackerrank.com/challenges/2d-array/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 *
 */
class TwoDArrayDS {

    @Test
    fun `two d array ds`() {  // hourglassSum
        val arr = arrayOf(
            arrayOf(1, 1, 1, 0, 0, 0),
            arrayOf(0, 1, 0, 0, 0, 0),
            arrayOf(1, 1, 1, 0, 0, 0),
            arrayOf(0, 0, 2, 4, 4, 0),
            arrayOf(0, 0, 0, 2, 0, 0),
            arrayOf(0, 0, 1, 2, 4, 0)
        )

        // flatten the array of arrays
        val flatten = arr.flatten()
        var currentMax = Int.MIN_VALUE

        flatten.forEachIndexed { index, _ ->

            if (canFormHourGlass(index)) {
                val sum = getSum(index, flatten)
                if (sum > currentMax) currentMax = sum
            }
        }

        assertEquals(currentMax, 19)
    }

    private fun getSum(it: Int, arr: List<Int>): Int {
        return arr[it] + arr[it + 1] + arr[it + 2] + arr[it + 7] + arr[it + 12] + arr[it + 13] + arr[it + 14]
    }

    private fun canFormHourGlass(index: Int): Boolean {
        return index.rem(6) < 4 && index + 14 < 36
    }
}
