package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/add-binary/
 */
class AddBinary {

    @Test
    fun test1() {
        Assert.assertEquals("10101", addBinary("1010", "1011"))
    }

    @Test
    fun test2() {
        Assert.assertEquals("100", addBinary("11", "1"))
    }

    fun addBinary(ax: String, bx: String): String {

        var a = ax
        var b = bx

        if (a.isEmpty() && b.isEmpty()) return ""
        if (b.isEmpty()) return a
        if (a.isEmpty()) return b

        if (a.length < b.length) a = a.padStart(b.length, '0')
        if (b.length < a.length) b = b.padStart(a.length, '0')

        val output = StringBuilder()
        var isCarrying = false
        var pointer = a.lastIndex

        while (pointer >= 0) {

            val fromA = a[pointer]
            val fromB = b[pointer]

            when {
                fromA == '0' && fromB == '0' -> {
                    if (isCarrying) {
                        isCarrying = false
                        output.append('1')
                    } else {
                        output.append('0')
                    }
                }

                fromA == '1' && fromB == '1' -> {
                    if (isCarrying) {
                        output.append('1')
                    } else {
                        isCarrying = true
                        output.append('0')
                    }
                }

                else -> {
                    if (isCarrying) {
                        output.append('0')
                    } else {
                        output.append('1')
                        isCarrying = false
                    }
                }
            }

            if (pointer == 0 && isCarrying)
                output.append('1')

            pointer--
        }

        return output.toString().reversed()
    }

}