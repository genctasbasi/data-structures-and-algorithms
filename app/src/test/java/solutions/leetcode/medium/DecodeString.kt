package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/decode-string/
 */
class DecodeString {

    @Test
    fun test() {
        assertEquals("aaabcbc", decodeString("3[a]2[bc]"))
    }

    @Test
    fun test1() {
        assertEquals("accaccacc", decodeString("3[a2[c]]"))
    }

    @Test
    fun test2() {
        assertEquals("abcabccdcdcdef", decodeString("2[abc]3[cd]ef"))
    }

    @Test
    fun test3() {
        assertEquals("abccdcdcdxyz", decodeString("abc3[cd]xyz"))
    }

    fun decodeString(s: String): String {

        val stackRepeat = Stack<Int>()
        val stackChars = Stack<String>()

        var index = 0
        var builtSoFar = ""
        while (index <= s.lastIndex) {
            val char = s[index]

            // 13[a2[c]]
            when {

                Character.isDigit(char) -> {

                    var number = ""
                    // get whole number (ie n digits)
                    while (Character.isDigit(s[index])) {
                        number += s[index]
                        index++
                    }

                    stackRepeat.push(number.toInt())
                }

                char == '[' -> {
                    stackChars.push(builtSoFar)
                    builtSoFar = ""
                    index++
                }

                char == ']' -> {

                    val count = stackRepeat.pop()
                    val sb = StringBuilder(stackChars.pop())
                    (0 until count).forEach {
                        sb.append(builtSoFar)
                    }
                    index++
                    builtSoFar = sb.toString()
                }

                else -> {   // alphabet character
                    builtSoFar += char
                    index++
                }
            }

        }

        return builtSoFar
    }
}