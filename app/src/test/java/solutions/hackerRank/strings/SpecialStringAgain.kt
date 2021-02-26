package solutions.hackerRank.strings

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.hackerrank.com/challenges/special-palindrome-again/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings
 */
class SpecialStringAgain {

    @Test
    fun test() {
        assertEquals(10, substrCount(4, "aaaa"))
        assertEquals(10, substrCount(7, "abcbaba"))
        assertEquals(7, substrCount(5, "asasd"))
    }

    fun substrCount(n: Int, s: String): Long {

        if (s.length < 2) return s.length.toLong()

        // check if all same char
        var prevChar: Char? = null
        var p = 0
        var isSame = true
        while (p <= s.lastIndex) {
            if (prevChar != null && prevChar != s[p]) {
                isSame = false
                break
            }
            prevChar = s[p]
            p++
        }

        if (isSame) return ((s.length * (s.length + 1)) / 2).toLong()

        var count = s.length
        s.forEachIndexed { index, c ->

            var expandLeft = index - 1
            var expandRight = index + 1

            var charToBe: Char? = null
            while (expandLeft >= 0 && expandRight <= s.lastIndex) {
                if (
                    s[expandLeft] == s[expandRight]
                    && (charToBe == null || charToBe == s[expandLeft])
                ) {
                    count++
                    charToBe = s[expandLeft]
                } else break

                expandLeft--
                expandRight++
            }
        }

        return count.toLong()
    }
}