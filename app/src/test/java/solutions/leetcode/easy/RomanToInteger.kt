package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/roman-to-integer/
 */
class RomanToInteger {

    @Test
    fun test() {
        val result = romanToInt("LVIII")
        assertEquals(58, result)
    }

    @Test
    fun test2() {
        val result = romanToInt("III")
        assertEquals(3, result)
    }

    @Test
    fun test3() {
        val result = romanToInt("MCMXCIV")
        assertEquals(1994, result)
    }

    @Test
    fun test4() {
        val result = romanToInt("IV")
        assertEquals(4, result)
    }

    @Test
    fun test5() {
        val result = romanToInt("IX")
        assertEquals(9, result)
    }

    fun romanToInt(s: String): Int {

        var p = 0
        var number = 0
        while (p <= s.lastIndex) {

            val read2Char: String =
                if (p + 1 > s.lastIndex) s[p].toString() else ("${s[p]}${s[p + 1]}")

            val readNumber = getAsNumber1(read2Char)
            if (readNumber != null) {
                number += readNumber
                p += 2
            } else {
                number += getAsNumber2(read2Char[0])
                p++
            }
        }

        return number
    }

    /**
     * returns a number if provided string is a 'minus group' like IX, IV,
     * -1 if not
     */
    private fun getAsNumber1(s: String): Int? {

        return when (s) {
            "IV" -> 4
            "IX" -> 9
            "XL" -> 40
            "XC" -> 90
            "CD" -> 400
            "CM" -> 900
            else -> null
        }
    }

    private fun getAsNumber2(s: Char): Int {
        return when (s) {
            'I' -> 1
            'V' -> 5
            'X' -> 10
            'L' -> 50
            'C' -> 100
            'D' -> 500
            'M' -> 1000
            else -> -1
        }
    }
}