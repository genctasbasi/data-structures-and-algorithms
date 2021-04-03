package solutions.leetcode.easy

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/strobogrammatic-number/
 */
class StrobogrammaticNumber {

    @Test
    fun test() {
        assertEquals(false, isStrobogrammatic("962"))
    }

    @Test
    fun test1() {
        assertEquals(true, isStrobogrammatic("980181086"))
    }

    fun isStrobogrammatic(num: String): Boolean {

        var pStart = 0
        var pEnd = num.lastIndex


        while (pStart <= pEnd) {

            val charStart = num[pStart]
            val charEnd = num[pEnd]

            val option1 = charStart == '9' && charEnd == '6'
            val option2 = charStart == '6' && charEnd == '9'
            val option3 = charStart == '8' && charEnd == '8'
            val option4 = charStart == '1' && charEnd == '1'
            val option5 = charStart == '0' && charEnd == '0'

            if (!option1 && !option2 && !option3 && !option4 && !option5) return false

            pStart++
            pEnd--
        }

        return true
    }
}