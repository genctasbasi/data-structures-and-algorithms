package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
class LetterCombinationsOfAPhoneNumber {

    @Test
    fun test() {
        val result = letterCombinations("23")
        assertEquals(listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"), result)
    }

    @Test
    fun test2() {
        val result = letterCombinations("2")
        assertEquals(listOf("a", "b", "c"), result)
    }

    @Test
    fun test3() {
        val result = letterCombinations("")
        assertEquals(listOf<String>(), result)
    }

    @Test
    fun test4() {
        val result = letterCombinations("234")
        assertEquals(
            listOf(
                "adg", "bdg", "cdg", "aeg", "beg", "ceg", "afg", "bfg", "cfg",
                "adh", "bdh", "cdh", "aeh", "beh", "ceh", "afh", "bfh", "cfh",
                "adi", "bdi", "cdi", "aei", "bei", "cei", "afi", "bfi", "cfi"

            ), result
        )
    }

    fun letterCombinations(digits: String): List<String> {

        if (digits.isEmpty()) return emptyList()

        var combined = mutableListOf<String>()
        val newCombination = mutableListOf<String>()
        digits.forEach {
            val letters = getLetters(it)
            if (combined.isEmpty()) {
                combined.addAll(letters.map { letters -> letters.toString() })
            } else {
                letters.forEach { newLetter ->
                    combined.forEach { combinedItem ->
                        newCombination.add(combinedItem + newLetter)
                    }
                }

                combined = newCombination.toMutableList()
                newCombination.clear()
            }
        }

        return combined
    }

    fun getLetters(digit: Char): String {

        return when (digit) {
            '2' -> "abc"
            '3' -> "def"
            '4' -> "ghi"
            '5' -> "jkl"
            '6' -> "mno"
            '7' -> "pqrs"
            '8' -> "tuv"
            '9' -> "wxyz"
            else -> ""
        }
    }
}