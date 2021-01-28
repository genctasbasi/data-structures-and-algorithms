package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/is-subsequence/
 */
class IsSubsequence {

    @Test
    fun test() {
        val result = isSubsequence("abd", "ahbgdc")
        Assert.assertEquals(true, result)
    }

    @Test
    fun test2() {
        val result = isSubsequence("axc", "ahbgdc")
        Assert.assertEquals(false, result)
    }

    fun isSubsequence(s: String, t: String): Boolean {

        var p1 = 0
        var p2 = 0

        while (p2 <= t.lastIndex) {

            if (t[p2] == s[p1]) {
                p1++

                if (p1 > s.lastIndex) return true
            }

            p2++
        }

        return false
    }

}