package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * TODO: WIP
 * https://leetcode.com/problems/regular-expression-matching/
 */
class RegularExpressionMatching {

    @Test
    fun test() {
        val result = solution("aaaaaba", "a*")
        assertEquals(false, result)
    }

    @Test
    fun test1() {
        val result = solution("aaaaaba", "a*b.")
        assertEquals(true, result)
    }

    @Test
    fun test2() {
        val result = solution("aa", "a")
        assertEquals(false, result)
    }

    @Test
    fun test3() {
        val result = solution("aa", "a*")
        assertEquals(true, result)
    }

    @Test
    fun test4() {
        val result = solution("ab", ".*")
        assertEquals(true, result)
    }

    @Test
    fun test5() {
        val result = solution("aab", "c*a*b")
        assertEquals(true, result)
    }

    @Test
    fun test6() {
        val result = solution("mississippi", "mis*is*p*.")
        assertEquals(false, result)
    }

    @Test
    fun test7() {
        val result = solution("ab", ".*c")
        assertEquals(false, result)
    }

    @Test
    fun test8() {
        val result = solution("aaa", "aaaa")
        assertEquals(false, result)
    }

    @Test
    fun test9() {
        val result = solution("aaa", "aaaa*")
        assertEquals(true, result)
    }

    @Test
    fun test10() {
        val result = solution("aaa", "a*a*")
        assertEquals(true, result)
    }

    @Test
    fun test11() {
        val result = solution("aaca", "ab*a*c*a")
        assertEquals(true, result)
    }

    fun solution(s: String, p: String): Boolean {

        var pChar = 0
        var pPattern = 0
        // var prevChar: Char? = null
        // var prevPattern: Char? = null

        var char: Char? = s[pChar]
        var pattern: Char? = p[pPattern]

        while (char != null) {

            if (pattern == null) return false

            when (pattern) {
                char,
                '.'
                -> {
                    // all good
                    pPattern++
                    pChar++
                }

                // "aaaaaba", "a*b."
                '*' -> {
                    if (p[pPattern-1] != char && p[pPattern-1] != '.') return false

                    while (char == s[pChar -1]) {
                        pChar++
                        char = if (pChar > s.lastIndex) null else s[pChar]
                        // if (char == null) return false
                    }

                    if (char == null) return true
                    if (pPattern == p.lastIndex && pChar == s.lastIndex) return true
                    pPattern++
                }

                else -> {   // pattern has a char and it doesn't match
                    // we need a following * not to fail
                    if (pPattern == p.lastIndex) return false
                    if (p[pPattern + 1] != '*') return false

                    // skip all *s
                    pPattern++
                    while (p[pPattern] == '*') {
                        pPattern++
                        if (pPattern > p.lastIndex) return false
                    }

                    // now we should either be on a matching char or .
                    if (p[pPattern] != char && p[pPattern] != '.') return false

                    // otherwise just continue
                }
            }

            // prevChar = s[pChar]
            // prevPattern = p[pPattern]

            char = if (pChar > s.lastIndex) null else s[pChar]
            pattern = if (pPattern > p.lastIndex) null else p[pPattern]
        }

        return pPattern == p.lastIndex + 1 || p.endsWith('*')
    }
}