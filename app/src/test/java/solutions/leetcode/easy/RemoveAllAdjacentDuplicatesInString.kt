package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 */
class RemoveAllAdjacentDuplicatesInString {

    @Test
    fun test() {
        assertEquals("ca", removeDuplicates("abcde"))
    }

    @Test
    fun test1() {
        assertEquals("", removeDuplicates("xyzzyx"))
    }

    @Test
    fun test2() {
        assertEquals("ba", removeDuplicates("aababaab"))
    }

    @Test
    fun test3() {
        assertEquals("a", removeDuplicates("aaaaaaaaa"))
    }

    @Test
    fun test4() {
        assertEquals("ababa", removeDuplicates("abbbabaaa"))
    }

    fun removeDuplicates(S: String): String {

        if (S.length < 2) return S

        var pSlow = 0
        var pFast = 1

        val sb = StringBuilder(S)
        while (pFast <= sb.lastIndex) {

            val slowChar = sb[pSlow]
            val fastChar = sb[pFast]

            if (fastChar != slowChar) { // changed

                val prevPosition = pFast - 1
                if (prevPosition > pSlow) {   // means there are stuff to remove
                    val end = if ((pFast - pSlow).rem(2) == 1) pFast - 1 else pFast
                    (end - 1 downTo pSlow).forEach { sb.deleteCharAt(it) }
                    pSlow = 0
                    pFast = 0
                } else {
                    pSlow++
                }
            }
            pFast++

            if (pFast > sb.lastIndex) {
                val prevPosition = pFast - 1
                if (prevPosition > pSlow) {   // means there are stuff to remove
                    val end = if ((pFast - pSlow).rem(2) == 1) pFast - 1 else pFast
                    (end - 1 downTo pSlow).forEach { sb.deleteCharAt(it) }
                    pSlow = 0
                    pFast = 0
                }
            }
        }

        return sb.toString()
    }

}