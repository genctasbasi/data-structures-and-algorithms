package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/count-and-say/
 */
class CountAndSay {

    @Test
    fun test() {
        val result1 = countAndSay(4)
        assertEquals("1211", result1)

        val result2 = countAndSay(1)
        assertEquals("1", result2)
    }

    val sb = StringBuilder()
    fun countAndSay(n: Int): String {

        if (n == 1) return "1"

        val say = countAndSay(n - 1)

        val list = mutableListOf<Pair<Char, Int>>()
        var currentNumber: Char? = null
        var prevNumber: Char? = null
        var currentCount = 0
        say.toCharArray().forEach {

            if (currentNumber == null) {
                currentNumber = it
                currentCount = 1
            } else {
                if (currentNumber == it)
                    currentCount++
                else {  // changed
                    list.add(Pair(prevNumber!!, currentCount))
                    currentNumber = it
                    currentCount = 1
                }
            }

            prevNumber = currentNumber
        }

        currentNumber?.let {
            list.add(Pair(prevNumber!!, currentCount))
        }

        sb.clear()
        list.forEach {
            sb.append(it.second)
            sb.append(it.first)
        }

        return sb.toString()
    }
}