package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Given a word of length n and n six-sided dice with a character in each side.
 * Find out if this word can be constructed by the set of given dice.
 */
class WordDice {

    @Test
    fun test() {
        val dice = arrayOf(
            charArrayOf('a', 'l', 'c', 'd', 'e', 'f'),
            charArrayOf('a', 'b', 'c', 'd', 'e', 'f'),
            charArrayOf('a', 'b', 'c', 'h', 'e', 'f'),
            charArrayOf('a', 'b', 'c', 'd', 'o', 'f'),
            charArrayOf('a', 'b', 'c', 'l', 'e', 'f')
        )

        assertEquals(true, canConstruct("hello", dice))
    }

    @Test
    fun test1() {
        val dice = arrayOf(
            charArrayOf('a', 'b', 'c', 'd', 'e', 'f'),
            charArrayOf('a', 'b', 'c', 'd', 'e', 'f'),
            charArrayOf('a', 'b', 'c', 'd', 'e', 'f'),
            charArrayOf('a', 'b', 'c', 'd', 'e', 'f'),
            charArrayOf('a', 'b', 'c', 'd', 'e', 'f')
        )

        assertEquals(false, canConstruct("hello", dice))
    }

    @Test
    fun test2() {
        val dice = arrayOf(
            charArrayOf('a', 'a', 'a', 'a', 'a', 'a'),
            charArrayOf('b', 'b', 'b', 'b', 'b', 'b'),
            charArrayOf('a', 'b', 'c', 'd', 'e', 'f'),
            charArrayOf('a', 'b', 'c', 'd', 'e', 'f')
        )

        assertEquals(false, canConstruct("aaaa", dice))
    }

    @Test
    fun test3() {
        val dice = arrayOf(
            charArrayOf('h', 'e', 'a'),
            charArrayOf('h', 'k', 'l')
        )

        assertEquals(true, canConstruct("he", dice))
    }

    @Test
    fun test4() {
        val dice = arrayOf(
            charArrayOf('h', 'e', 'a'),
            charArrayOf('h', 'k', 'l')
        )

        assertEquals(false, canConstruct("hs", dice))
    }

    private fun canConstruct(word: String, dice: Array<CharArray>): Boolean {

        val dieUsed = mutableListOf<Int>()
        val letterFound = mutableListOf<Int>()

        val result = canConstructHelper(word, dice, dieUsed, letterFound)
        return result
    }

    private fun canConstructHelper(
        word: String,
        dice: Array<CharArray>,
        dieUsed: MutableList<Int>,
        charFound: MutableList<Int>
    ): Boolean {

        if (charFound.size == word.length)
            return true

        word.forEachIndexed { charIndex, char ->
            if (charFound.contains(charIndex).not()) {
                dice.forEachIndexed { index, it ->
                    if (dieUsed.contains(index).not()) {
                        if (it.contains(char)) {
                            dieUsed.add(index)
                            charFound.add(charIndex)
                            val hasFound = canConstructHelper(word, dice, dieUsed, charFound)
                            if (!hasFound) {    // backtracking!
                                dieUsed.remove(index)
                                charFound.remove(charIndex)
                            } else {
                                return true
                            }
                        }
                    }

                }
            }
        }

        return false
    }

}