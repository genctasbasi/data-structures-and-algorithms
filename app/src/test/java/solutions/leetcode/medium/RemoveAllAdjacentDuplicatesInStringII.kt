package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 * TODO Can be WIP, check.
 */
class RemoveAllAdjacentDuplicatesInStringII {

    @Test
    fun test() {
        assertEquals("abcd", removeDuplicates("abcd", 2))
    }

    @Test
    fun test1() {
        assertEquals("aa", removeDuplicates("deeedbbcccbdaa", 3))
    }

    @Test
    fun test2() {
        assertEquals("ps", removeDuplicates("pbbcggttciiippooaais", 2))
    }

    @Test
    fun test3() {
        assertEquals("deeedbbcccbdaa", removeDuplicates("deeedbbcccbdaa", 10))
    }

    fun removeDuplicates(S: String, k: Int): String {

        if (S.length < 2) return S

        var pSlow = 0
        var pFast = 1

        var repeat = 0
        val sb = StringBuilder(S)
        while (pFast <= sb.lastIndex) {

            val slowChar = sb[pSlow]
            val fastChar = sb[pFast]

            if (fastChar != slowChar) { // changed

                val prevPosition = pFast - 1
                if (prevPosition > pSlow && repeat >= k) {   // means there are stuff to remove
                    val end = if ((pFast - pSlow).rem(2) == 1) pFast - 1 else pFast
                    (end - 1 downTo pSlow).forEach { sb.deleteCharAt(it) }
                    pSlow = 0
                    pFast = 0
                } else {
                    pSlow++
                }
                repeat = 0
            }else repeat++

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