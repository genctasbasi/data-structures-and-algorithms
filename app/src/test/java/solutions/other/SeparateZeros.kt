package solutions.other

import junit.framework.TestCase
import org.junit.Test

/**
 * https://www.geeksforgeeks.org/count-ways-to-split-a-binary-string-into-three-substrings-having-equal-count-of-zeros/
 *
 * Given binary string str, the task is to count the total number of ways to split
 * the given string into three non-overlapping substrings having the same number of 0s.
 *
 * Input: str = “01010”
 * Output: 4
 * Explanation:
 * The possible splits are: [0, 10, 10], [01, 01, 0], [01, 0, 10], [0, 101, 0]
 */
class SeparateZeros {

    @Test
    fun test1() {
        val result = separate("01010")
        TestCase.assertEquals(4, result)
    }

    private fun separate(s: String): Int {
        val zeroCount = s.count { it == '0' }

        if (zeroCount.rem(3) != 0) return 0

        val zeroInEachGroup = zeroCount / 3

        var waysOfFirstCut = 0
        var waysOfSecondCut = 0

        var count = 0
        s.forEach {

            if (it == '0') count++

            if (count == zeroInEachGroup)
                waysOfFirstCut++

            // Incrementing the ways for the
            // 2nd cut if count is equal to
            // 2*(zeros required in each substring)
            else if (count == 2 * zeroInEachGroup)
                waysOfSecondCut++

        }

        // Total number of ways to split is
        // multiplication of ways for the 1st
        // and 2nd cut
        return waysOfFirstCut * waysOfSecondCut
    }
}