package solutions.leetcode.hard

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/minimum-window-subsequence/
 */
class MinimumWindowSubsequence {

    @Test
    fun test() {
        val result = minWindow("abcdebdde", "bde")
        Assert.assertEquals("bcde", result)
    }

    @Test
    fun test2() {
        val result = minWindow("abcdebddebdxxxxxe", "bde")
        Assert.assertEquals("bde", result)
    }

    @Test
    fun test3() {
        val result = minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "u")
        Assert.assertEquals("", result)
    }

    @Test
    fun test4() {
        val result = minWindow("wcbsuiyzacfgrqsqsnodwmxzkz", "xwqe")
        Assert.assertEquals("", result)
    }

    fun minWindow(S: String, T: String): String {

        var list = mutableListOf<Int>()
        var pickStart = 0
        var pickEnd = Int.MAX_VALUE
        var minDiff = Int.MAX_VALUE
        S.forEachIndexed { index, it ->
            list.clear()
            if (it == T[0]) {
                list = minWindowHelper(S, T, index, 0)
                if (list.size == T.length)
                    if (list[0] - list[list.lastIndex] < minDiff) {
                        minDiff = list[0] - list[list.lastIndex]
                        pickStart = list[list.lastIndex]
                        pickEnd = list[0]
                    }
            }
        }

        return if (minDiff == Int.MAX_VALUE) "" else S.substring(pickStart, pickEnd + 1)
    }

    fun minWindowHelper(S: String, T: String, startS: Int, startT: Int): MutableList<Int> {

        if (startT > T.lastIndex) return mutableListOf()

        val lookLetter = T[startT]

        (startS..S.lastIndex).forEachIndexed { index, it ->

            if (S[startS + index] == lookLetter) {
                val list = minWindowHelper(S, T, startS + index + 1, startT + 1)
                list.add(startS + index)
                return list
            }
        }

        return mutableListOf()
    }
}