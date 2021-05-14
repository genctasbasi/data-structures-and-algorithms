package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/number-of-matching-subsequences/
 */
class NumberOfMatchingSubsequences {

    @Test
    fun test() {
        val result = numMatchingSubseq("abcde", arrayOf("a", "bb", "acd", "ace"))
        assertEquals(3, result)
    }

    @Test
    fun test1() {
        val result =
            numMatchingSubseq("dsahjpjauf", arrayOf("ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"))
        assertEquals(2, result)
    }

    @Test
    fun test2() {
        val result =
            numMatchingSubseq(
                "ricogwqznwxxcpueelcobbbkuvxxrvgyehsudccpsnuxpcqobtvwkuvsubiidjtccoqvuahijyefbpqhbejuisksutsowhufsygtwteiqyligsnbqglqblhpdzzeurtdohdcbjvzgjwylmmoiundjscnlhbrhookmioxqighkxfugpeekgtdofwzemelpyjsdeeppapjoliqlhbrbghqjezzaxuwyrbczodtrhsvnaxhcjiyiphbglyolnswlvtlbmkrsurrcsgdzutwgjofowhryrubnxkahocqjzwwagqidjhwbunvlchojtbvnzdzqpvrazfcxtvhkruvuturdicnucvndigovkzrqiyastqpmfmuouycodvsyjajekhvyjyrydhxkdhffyytldcdlxqbaszbuxsacqwqnhrewhagldzhryzdmmrwnxhaqfezeeabuacyswollycgiowuuudrgzmwnxaezuqlsfvchjfloczlwbefksxsbanrektvibbwxnokzkhndmdhweyeycamjeplecewpnpbshhidnzwopdjuwbecarkgapyjfgmanuavzrxricbgagblomyseyvoeurekqjyljosvbneofjzxtaizjypbcxnbfeibrfjwyjqrisuybfxpvqywqjdlyznmojdhbeomyjqptltpugzceyzenflfnhrptuugyfsghluythksqhmxlmggtcbdddeoincygycdpehteiugqbptyqbvokpwovbnplshnzafunqglnpjvwddvdlmjjyzmwwxzjckmaptilrbfpjxiarmwalhbdjiwbaknvcqovwcqiekzfskpbhgxpyomekqvzpqyirelpadooxjhsyxjkfqavbaoqqvvknqryhotjritrkvdveyapjfsfzenfpuazdrfdofhudqbfnzxnvpluwicurrtshyvevkriudayyysepzqfgqwhgobwyhxltligahroyshfndydvffd",
                arrayOf("ydlddogzvzttizzzjohfsenatvbpngarutztgdqczkzoenbxzv")
            )
        assertEquals(0, result)
    }

    fun numMatchingSubseq(s: String, words: Array<String>): Int {

        if (s.isEmpty() || words.isEmpty()) return 0

        var count = 0
        words.forEach {
            if (exists(s, it, 0, 0)) count++
        }

        return count
    }

    fun exists(
        s: String,
        word: String,
        sStart: Int,
        wordStart: Int
    ): Boolean {

        if (sStart > s.lastIndex) return false
        if (wordStart > word.lastIndex) return false

        var index = sStart
        while (index <= s.lastIndex) {

            if (s[index] == word[wordStart]) {
                if (wordStart == word.lastIndex) return true

                val searchInRest = exists(s, word, index + 1, wordStart + 1)

                return searchInRest
            }

            index++
        }

        return false
    }
}