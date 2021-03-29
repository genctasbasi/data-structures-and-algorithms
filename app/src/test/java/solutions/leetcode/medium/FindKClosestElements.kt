package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/find-k-closest-elements/
 */
class FindKClosestElements {

    @Test
    fun test() {
        assertArrayEquals(
            intArrayOf(1, 2, 3, 4),
            findClosestElements(intArrayOf(1, 2, 3, 4, 5), 4, 3).toIntArray()
        )
    }

    @Test
    fun test1() {
        assertArrayEquals(
            intArrayOf(4, 5, 6, 7),
            findClosestElements(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 4, 6).toIntArray()
        )
    }

    @Test
    fun test2() {
        assertArrayEquals(
            intArrayOf(3, 3, 4),
            findClosestElements(intArrayOf(0, 0, 1, 2, 3, 3, 4, 7, 7, 8), 3, 5).toIntArray()
        )
    }

    @Test
    fun test3() {
        assertArrayEquals(
            intArrayOf(1),
            findClosestElements(intArrayOf(1, 3), 1, 2).toIntArray()
        )
    }

    @Test
    fun test4() {
        assertArrayEquals(
            intArrayOf(
                72,
                74,
                74,
                74,
                75,
                76,
                76,
                77,
                77,
                80,
                80,
                82,
                83,
                85,
                86,
                87,
                87,
                92,
                93,
                94,
                96,
                96,
                97,
                98,
                99
            ),
            findClosestElements(
                intArrayOf(
                    0,
                    1,
                    2,
                    3,
                    4,
                    4,
                    4,
                    5,
                    5,
                    5,
                    6,
                    7,
                    9,
                    9,
                    10,
                    10,
                    11,
                    11,
                    12,
                    13,
                    14,
                    14,
                    15,
                    17,
                    19,
                    19,
                    22,
                    24,
                    24,
                    25,
                    25,
                    27,
                    27,
                    29,
                    30,
                    32,
                    32,
                    33,
                    33,
                    35,
                    36,
                    38,
                    39,
                    41,
                    42,
                    43,
                    44,
                    44,
                    46,
                    47,
                    48,
                    49,
                    52,
                    53,
                    53,
                    54,
                    54,
                    57,
                    57,
                    58,
                    59,
                    59,
                    59,
                    60,
                    60,
                    60,
                    61,
                    61,
                    62,
                    64,
                    66,
                    68,
                    68,
                    70,
                    72,
                    72,
                    74,
                    74,
                    74,
                    75,
                    76,
                    76,
                    77,
                    77,
                    80,
                    80,
                    82,
                    83,
                    85,
                    86,
                    87,
                    87,
                    92,
                    93,
                    94,
                    96,
                    96,
                    97,
                    98,
                    99
                ), 25, 90
            ).toIntArray()
        )
    }

    @Test
    fun test5() {
        assertArrayEquals(
            intArrayOf(10),
            findClosestElements(intArrayOf(1, 1, 1, 10, 10, 10), 1, 9).toIntArray()
        )
    }

    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        if (arr.isEmpty() || k == 0) return emptyList()

        if (x < arr[0]) return arr.take(k)
        if (x > arr[arr.lastIndex]) return arr.takeLast(k)

        val output = mutableListOf<Int>()

        val xIndex = getIndexOfX(arr, x, 0, arr.lastIndex)

        var pL = xIndex - 1
        var pR = xIndex

        while (output.size < k) {

            val leftValue = if (pL >= 0) arr[pL] else null
            val rightValue = if (pR <= arr.lastIndex) arr[pR] else null

            if (leftValue == null && rightValue == null) return output

            when {
                leftValue == null -> {
                    output.add(rightValue!!)
                    pR++
                }
                rightValue == null -> {
                    output.add(leftValue)
                    pL--
                }
                else -> {   // has both left & right
                    val leftDistance = Math.abs(x - leftValue)
                    val rightDistance = Math.abs(x - rightValue)

                    when {
                        leftDistance <= rightDistance -> {
                            output.add(leftValue)
                            pL--
                        }
                        leftDistance > rightDistance -> {
                            output.add(rightValue)
                            pR++
                        }
                    }
                }
            }
        }

        return output.sorted()
    }

    private fun getIndexOfX(arr: IntArray, x: Int, start: Int, end: Int): Int {

        if (start >= end) return start

        val mid = (start + end) / 2

        val number = arr[mid]
        if (number == x) return mid

        return if (x > number) {
            getIndexOfX(arr, x, mid + 1, end)
        } else {
            getIndexOfX(arr, x, start, mid)
        }
    }
}