package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/sentence-screen-fitting/
 */
class SentenceScreenFitting {

    @Test
    fun test() {
        assertEquals(1, wordsTyping(arrayOf("hello", "world"), 2, 8))
    }

    @Test
    fun test1() {
        assertEquals(2, wordsTyping(arrayOf("a", "bcd", "e"), 3, 6))
    }

    @Test
    fun test2() {
        assertEquals(1, wordsTyping(arrayOf("i", "had", "apple", "pie"), 4, 5))
    }

    @Test
    fun test3() {
        assertEquals(10, wordsTyping(arrayOf("f", "p", "a"), 8, 7))
    }

    fun wordsTyping(sentence: Array<String>, rows: Int, cols: Int): Int {

        if (sentence.isEmpty() || rows == 0 || cols == 0) return 0

        var wordIndex = 0
        var count = 0
        (0 until rows).forEach { rowIndex ->

            var colIndex = 0
            while (colIndex < cols) {

                val colsLeft = cols - colIndex

                val wordLength = sentence[wordIndex].length
                if (colsLeft >= wordLength) {
                    wordIndex++ // placed the word
                    colIndex += wordLength

                    colIndex++
                } else {
                    break
                }

                if (wordIndex > sentence.lastIndex) {
                    wordIndex = 0
                    count++
                }
            }
        }

        return count
    }
}