package solutions.leetcode.medium

import junit.framework.TestCase
import org.junit.Test

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
class Atoi {

    @Test
    fun test() {
        val result = myAtoi("42")
        TestCase.assertEquals(42, result)
    }

    @Test
    fun test2() {
        val result = myAtoi("-42")
        TestCase.assertEquals(-42, result)
    }

    @Test
    fun test6() {
        val result = myAtoi("  dsfd df  42")
        TestCase.assertEquals(0, result)
    }

    @Test
    fun test3() {
        val result = myAtoi("428 ash")
        TestCase.assertEquals(428, result)
    }

    @Test
    fun test4() {
        val result = myAtoi("-91283472332")
        TestCase.assertEquals(-2147483648, result)
    }

    @Test
    fun test5() {
        val result = myAtoi("91283472332")
        TestCase.assertEquals(-2147483648, result)
    }

    @Test
    fun test7() {
        val result = myAtoi("-+12")
        TestCase.assertEquals(0, result)
    }

    @Test
    fun test8() {
        val result = myAtoi("-")
        TestCase.assertEquals(0, result)
    }

    @Test
    fun test9() {
        val result = myAtoi("+-12")
        TestCase.assertEquals(0, result)
    }

    @Test
    fun test10() {
        val result = myAtoi("21474836460")
        TestCase.assertEquals(2147483647, result)
    }

    fun myAtoi(s: String): Int {

        if (s.isEmpty()) return 0

        var numbersStarted = false
        var isNegative = false
        val sb = StringBuilder()
        val numbers = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        s.forEach {

            when {
                it == ' ' -> {
                    if (numbersStarted) {
                        return parseAndReturn(sb.toString(), isNegative)
                    }
                }
                it == '+' -> {
                    if (!numbersStarted) {
                        numbersStarted = true
                    } else {
                        return parseAndReturn(sb.toString(), isNegative)
                    }
                }
                it == '-' -> {
                    if (!numbersStarted) {
                        numbersStarted = true
                        isNegative = true
                    } else {
                        return parseAndReturn(sb.toString(), isNegative)
                    }
                }

                numbers.contains(it) -> {
                    numbersStarted = true
                    sb.append(it)
                }

                else -> {
                    // not a number and minus
                    return if (numbersStarted && sb.isNotEmpty())
                        parseAndReturn(sb.toString(), isNegative)
                    else
                        0
                }
            }
        }

        return parseAndReturn(sb.toString(), isNegative)
    }

    private fun parseAndReturn(numberAsString: String, isNegative: Boolean): Int {
        return try {
            when {
                numberAsString.isEmpty() -> 0
                isNegative -> "-$numberAsString".toInt()
                else -> numberAsString.toInt()
            }
        } catch (e: Exception) {
            if (isNegative) Int.MIN_VALUE else Int.MAX_VALUE
        }
    }

}