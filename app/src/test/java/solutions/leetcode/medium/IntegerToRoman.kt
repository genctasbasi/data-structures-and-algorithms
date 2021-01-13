package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/integer-to-roman/
 */
class IntegerToRoman {

    @Test
    fun test() {
        val result = intToRoman(1994)
        assertEquals("MCMXCIV", result)
    }

    fun intToRoman(num: Int): String {

        val sb = StringBuilder()
        var divisor = 10
        var prev = 0
        var built = 0
        while (built != num) {

            val digit = num.rem(divisor) - prev
            built += digit

            print(digit)
            val roman = digitToRoman(digit)
            sb.insert(0, roman)

            divisor *= 10
            prev = built
        }

        return sb.toString()
    }

    private fun digitToRoman(digit: Int): String {

        val sb = StringBuilder()

        when {
            digit == 4 -> return "IV"
            digit == 5 -> return "V"
            digit == 9 -> return "IX"
            digit == 10 -> return "X"
            digit == 40 -> return "XL"
            digit == 50 -> return "L"
            digit == 90 -> return "XC"
            digit == 100 -> return "C"
            digit == 400 -> return "CD"
            digit == 500 -> return "D"
            digit == 900 -> return "CM"
            digit == 1000 -> return "M"

            digit < 5 -> {
                var i = 1
                while (i <= digit) {
                    sb.append('I')
                    i++
                }
            }

            digit < 10 -> {
                var i = 6
                sb.append('V')
                while (i <= digit) {
                    sb.append('I')
                    i++
                }
            }

            digit < 50 -> {
                var i = 1
                while (i <= (digit / 10)) {
                    sb.append('X')
                    i++
                }
            }

            digit < 100 -> {
                var i = 6
                sb.append('L')
                while (i <= (digit / 10)) {
                    sb.append('X')
                    i++
                }
            }


            digit < 500 -> {
                var i = 1
                while (i <= (digit / 100)) {
                    sb.append('C')
                    i++
                }
            }

            digit < 1000 -> {
                var i = 6
                sb.append('D')
                while (i <= (digit / 100)) {
                    sb.append('C')
                    i++
                }
            }

            digit < 5000 -> {
                var i = 1
                while (i <= (digit / 1000)) {
                    sb.append('M')
                    i++
                }
            }

            else -> ""
        }
        return sb.toString()
    }
}