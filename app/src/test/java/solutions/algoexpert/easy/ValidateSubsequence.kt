package solutions.algoexpert.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Validate%20Subsequence
 */
class ValidateSubsequence {

    @Test
    fun test() {

        Assert.assertEquals(
            true,
            isValidSubsequence2(
                listOf(5, 1, 22, 25, 6, -1, 8, 10),
                listOf(1, 6, -1, 10)
            )
        )

        Assert.assertEquals(
            false,
            isValidSubsequence2(
                listOf(5, 1, 22, 25, 6, -1, 8, 10),
                listOf(1, 6, -1, 99, 10)
            )
        )

        Assert.assertEquals(
            false,
            isValidSubsequence2(
                listOf(5, 1, 22, 25, 6, -1, 8, 10),
                listOf(1, 6, -1, 10, 8)
            )
        )
    }

    // O(n) complexity | O(n) space
    private fun isValidSubsequence(array: List<Int>, sequence: List<Int>): Boolean {
        if (array == sequence || sequence.isEmpty()) return true

        var index = -1
        var subArray = array
        sequence.forEach {
            subArray = subArray.subList(index + 1, subArray.size)
            index = getIndex(subArray, it)

            if (index == -1) return false
        }

        return true
    }

    // listOf(1, 6, -1, 99, 10)
    // listOf(5, 1, 22, 25, 6, -1, 8, 10),
    private fun isValidSubsequence2(array: List<Int>, sequence: List<Int>): Boolean {

        var seqIndex = 0
        array.forEach {
            if (seqIndex == sequence.size) return seqIndex == sequence.size
            if (sequence[seqIndex] == it) seqIndex++
        }

        return seqIndex == sequence.size
    }

    private fun getIndex(array: List<Int>, it: Int): Int {
        return array.indexOf(it)
    }

}