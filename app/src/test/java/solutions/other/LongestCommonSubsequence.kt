package solutions.other

import org.junit.Assert
import org.junit.Test

/**
 *
 * https://www.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1
 *
 * Given two strings, find the length of longest subsequence present in both of them.
 * Both the strings are in uppercase latin alphabets.
 */
class LongestCommonSubsequence {

    @Test
    fun `find the longest common subsequence`() {
        val str1 = "ABCDGHR"
        val str2 = "AEDFHR"

        val commonLength = lcs(str1, str2)

        Assert.assertEquals(4, commonLength)
    }

    fun lcs(s1: String, s2: String): Int {

        if (s1.isEmpty() || s2.isEmpty()) return 0

        // the max length of LCS could be the size of the shortest string
        val min = minOf(s1.length, s2.length)

        val agg = Array(min) { -1 }
        var prevAgg = Array(min) { -1 }


        s1.forEachIndexed { _, char1 ->
            s2.forEachIndexed { index2, char2 ->

                if (char1 == char2) {
                    // check how it was before this match
                    // that is the left diagonal
                    if (index2 == 0) {
                        agg[index2] = 1
                    } else {
                        val diagonal = prevAgg[index2 - 1]
                        agg[index2] = diagonal + 1
                    }
                } else {
                    // get the max of prev & before
                    if (index2 == 0) {
                        agg[index2] = prevAgg[index2]   // just get the top one
                    } else {
                        agg[index2] = maxOf(prevAgg[index2], agg[index2 - 1])
                    }
                }
            }

            prevAgg = agg
        }

        return agg.max()
    }
}