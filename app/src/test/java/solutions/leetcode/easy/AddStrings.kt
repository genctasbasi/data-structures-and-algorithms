package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/add-strings/
 */
class AddStrings {

    @Test
    fun test() {
        assertEquals("895", addStrings("546", "349"))
    }

    @Test
    fun test1() {
        assertEquals("10916", addStrings("5458", "5458"))
    }

    @Test
    fun test2() {
        assertEquals("108", addStrings("9", "99"))
    }

    fun addStrings(num1x: String, num2x: String): String {

        if (num1x.isEmpty()) return num2x
        if (num2x.isEmpty()) return num1x

        var num1 = num1x
        var num2 = num2x
        if (num1x.length < num2x.length) num1 = num1x.padStart(num2x.length, '0')
        if (num2x.length < num1x.length) num2 = num2x.padStart(num1x.length, '0')

        val map = mutableMapOf<Char, Int>()
        map['0'] = 0
        map['1'] = 1
        map['2'] = 2
        map['3'] = 3
        map['4'] = 4
        map['5'] = 5
        map['6'] = 6
        map['7'] = 7
        map['8'] = 8
        map['9'] = 9

        var pIndex = Math.max(num1.lastIndex, num2.lastIndex)

        var isCarrying = false
        val sb = StringBuilder()
        while (pIndex >= 0) {

            val digit1 = if (pIndex > num1.lastIndex) 0 else map[num1[pIndex]]!!
            val digit2 = if (pIndex > num2.lastIndex) 0 else map[num2[pIndex]]!!

            var newDigit = digit1 + digit2
            if (isCarrying) {
                newDigit++
                isCarrying = false
            }

            if (newDigit > 10) {
                isCarrying = true
                sb.append(newDigit.rem(10))
            } else
                sb.append(newDigit.toString().reversed())

            pIndex--
        }

        if (isCarrying)
            sb.append('1')

        return sb.toString().reversed()
    }
}