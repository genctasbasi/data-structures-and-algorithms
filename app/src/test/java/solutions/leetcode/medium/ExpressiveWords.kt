package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/expressive-words/
 *
 * Two pointers complexity: O(nms)
 * n: Number of words
 * s: length of S
 * m: length of longest word
 */
class ExpressiveWords {

    @Test
    fun test() {
        val result = expressiveWords("heeellooo", arrayOf("hello", "hi", "helo"))
        assertEquals(1, result)
    }

    @Test
    fun test1() {
        val result = expressiveWords("heeellooo", arrayOf("hello"))
        assertEquals(1, result)
    }

    @Test

    fun test2() {
        val result = expressiveWords("heeelllooo", arrayOf("hellllo"))
        assertEquals(0, result)
    }

    @Test
    fun test3() {
        val result = expressiveWords("aaa", arrayOf("aaaa"))
        assertEquals(0, result)
    }

    fun expressiveWords(S: String, words: Array<String>): Int {

        if (S.isEmpty()) return 0
        if (words.isEmpty()) return 0

        var p1 = 0
        var p2 = 0
        var count = 0
        words.forEach { word ->

            if (word.length < S.length) {
                var isValid = true
                while (p1 <= word.lastIndex && p2 <= S.lastIndex) {

                    val wordLetter = word[p1]
                    val sLetter = S[p2]

                    if (wordLetter != sLetter) {
                        isValid = false
                        break // TODO double check
                    }

                    // fast forward p1
                    var forwardCountWord = 0
                    while (p1 <= word.lastIndex && word[p1] == wordLetter) {
                        p1++
                        forwardCountWord++
                    }

                    // fast forward p2
                    // fast forward p1
                    var forwardCountS = 0
                    while (p2 <= S.lastIndex && S[p2] == sLetter) {
                        p2++
                        forwardCountS++
                    }

                    if (forwardCountWord > forwardCountS) {
                        isValid = false
                        break
                    }

                    if (forwardCountS != forwardCountWord && forwardCountS < 3) {
                        isValid = false
                        break
                    }
                }

                if (isValid && p1 > word.lastIndex && p2 > S.lastIndex)
                    count++

                // reset
                p1 = 0
                p2 = 0
            }
        }

        return count
    }
}