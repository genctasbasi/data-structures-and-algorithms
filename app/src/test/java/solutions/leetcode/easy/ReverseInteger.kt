package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/reverse-integer/
 */
class ReverseInteger {

    @Test
    fun test() {
        val result = reverse(-123)
        Assert.assertEquals(-321, result)
    }

    fun reverse(x: Int): Int {

        if (x < 10 && x > -10) return x

        val isMinus = x < 0
        val number = if (x.rem(10) == 0) x / 10 else x

        val numberString = Math.abs(number).toString()

        val sb = StringBuilder()
        for (i in numberString.length - 1 downTo 0) {
            sb.append(numberString[i])
        }

        return try {
            val numberBack = sb.toString().toInt()
            if (isMinus) numberBack * -1 else numberBack
        } catch (e: Exception) {
            0
        }
    }
}