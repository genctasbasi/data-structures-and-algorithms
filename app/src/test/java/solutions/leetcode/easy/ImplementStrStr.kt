package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/implement-strstr/
 */
class ImplementStrStr {

    @Test
    fun test() {
        val result = strStr("hello", "ll")
        Assert.assertEquals(2, result)
    }

    fun strStr(haystack: String, needle: String): Int {

        if (needle.isEmpty()) return 0
        if (haystack.isEmpty()) return -1

        var p1 = 0
        var p2 = 0
        var startIndex = -1
        while (p1 <= haystack.lastIndex) {
            if (p2 <= needle.lastIndex && haystack[p1] == needle[p2]) {
                if (startIndex == -1)
                    startIndex = p1

                p2++
            } else {

                if (p2 > needle.lastIndex) {
                    return startIndex
                } else {
                    p2 = 0
                    if (startIndex != -1)
                        p1 = startIndex
                    startIndex = -1
                }
            }

            p1++
        }

        return if (p2 > needle.lastIndex) startIndex else -1
    }
}