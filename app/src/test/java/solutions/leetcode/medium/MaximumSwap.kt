package solutions.leetcode.medium

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/maximum-swap/
 */
class MaximumSwap {

    @Test
    fun test() {
        assertEquals(7236, maximumSwap(2736))
    }

    @Test
    fun test1() {
        assertEquals(9973, maximumSwap(9973))   // no swap
    }

    fun maximumSwap(num: Int): Int {
        if (num < 10) return num

        val lastIndexes = Array(10) { -1 }

        val arr = num.toString().toCharArray()
        var pS = 0

        while (pS <= arr.lastIndex) {
            val digit = arr[pS]
            lastIndexes[digit - '0'] = pS
            pS++
        }

        pS = 0  // reset

        while (pS <= arr.lastIndex) {

            val digitStart = arr[pS]

            // check if we have any number in our array that's bigger than this digitStart
            (9 downTo 0).forEach {

                val lastIndex = lastIndexes[it]

                if (lastIndex > pS) {  // found it
                    val swapValue = arr[lastIndex]
                    if (swapValue > digitStart) {
                        arr[pS] = swapValue
                        arr[lastIndex] = digitStart
                        return arr.joinToString("").toInt()
                    }
                }
            }

            pS++
        }

        return num
    }

    fun `maximumSwapO(n2)`(num: Int): Int {
        if (num < 10) return num

        val numStr = num.toString()

        var pS = 0
        var pE = numStr.lastIndex
        var maxSwap = Char.MIN_VALUE
        var swapIndex: Int? = null

        while (pS <= numStr.lastIndex) {

            val digitStart = numStr[pS]

            while (pE > pS) {

                val digitEnd = numStr[pE]

                if (digitEnd > maxSwap) {
                    maxSwap = digitEnd
                    swapIndex = pE
                }

                if (digitEnd > maxSwap)
                    maxSwap = digitEnd

                pE--

                if (pE == pS && maxSwap > digitStart && swapIndex != null) {   // time to swap
                    val arr = numStr.toCharArray()
                    arr[pS] = maxSwap
                    arr[swapIndex] = digitStart
                    return arr.joinToString("").toInt()
                }
            }

            maxSwap = Char.MIN_VALUE
            pS++
            pE = numStr.lastIndex
        }

        return num
    }
}