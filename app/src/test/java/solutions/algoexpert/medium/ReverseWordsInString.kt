package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Reverse%20Words%20In%20String
 */
class ReverseWordsInString {

    @Test
    fun testCase1() {
        assertEquals("best! the is AlgoExpert", reverseWordsInString("AlgoExpert is the best!"))
    }

    fun reverseWordsInString(string: String): String {

        val list = mutableListOf<String>()
        val sb = StringBuilder()
        string.forEachIndexed { index, it ->

            sb.append(it)
            if (it == ' ' || index == string.lastIndex) {
                if (index == string.lastIndex) sb.append(' ')
                if (list.size == 0) list.add(sb.dropLast(1).toString())
                else list.add(sb.toString())
                sb.clear()
            }
        }

        sb.clear()
        list.reversed().forEach {
            sb.append(it)
            /// sb.append(" ")
        }

        return sb.toString()
    }
}