package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Phone%20Number%20Mnemonics
 *
 */
class PhoneNumberMnemonics {

    @Test
    fun testCase1() {
        val result = phoneNumberMnemonics("23")
        assertEquals(
            listOf(
                "ad", "bd", "cd",
                "ae", "be", "ce",
                "af", "bf", "cf"
            ), result
        )
    }

    @Test
    fun testCase2() {
        val result = phoneNumberMnemonics("1905")
        assertEquals(
            listOf(
                "1w0j",
                "1w0k",
                "1w0l",
                "1x0j",
                "1x0k",
                "1x0l",
                "1y0j",
                "1y0k",
                "1y0l",
                "1z0j",
                "1z0k",
                "1z0l"
            ), result
        )
    }

    fun phoneNumberMnemonics(phoneNumber: String): List<String> {

        var list = mutableListOf<String>()

        phoneNumber.forEach {
            val letters = getLetters(it)
            list = combine(list, letters)
        }

        return list
    }

    private fun combine(list: MutableList<String>, letters: List<Char>): MutableList<String> {

        if (list.isEmpty()) return letters.map { it.toString() }.toMutableList()

        val combined = mutableListOf<String>()
        letters.forEach { letter ->
            list.forEach {
                combined.add("$it$letter")
            }
        }
        return combined
    }


    fun getLetters(digit: Char): List<Char> {
        return when (digit) {
            '0' -> listOf('0')
            '1' -> listOf('1')
            '2' -> "abc".toList()
            '3' -> "def".toList()
            '4' -> "ghi".toList()
            '5' -> "jkl".toList()
            '6' -> "mno".toList()
            '7' -> "pqrs".toList()
            '8' -> "tuv".toList()
            '9' -> "wxyz".toList()
            else -> listOf()
        }
    }
}