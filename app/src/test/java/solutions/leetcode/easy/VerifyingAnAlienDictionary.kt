package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 */
class VerifyingAnAlienDictionary {

    @Test
    fun test() {
        val result = isAlienSorted(arrayOf("hello", "leetcode"), "hlabcdefgijkmnopqrstuvwxyz")
        Assert.assertEquals(true, result)
    }

    @Test
    fun test1() {
        val result = isAlienSorted(arrayOf("word", "world", "row"), "worldabcefghijkmnpqstuvxyz")
        Assert.assertEquals(false, result)
    }

    @Test
    fun test2() {
        val result = isAlienSorted(arrayOf("app", "apple"), "worldabcefghijkmnpqstuvxyz")
        Assert.assertEquals(true, result)
    }

    @Test
    fun test3() {
        val result = isAlienSorted(arrayOf("apple", "app"), "worldabcefghijkmnpqstuvxyz")
        Assert.assertEquals(false, result)
    }

    @Test
    fun test4() {
        val result = isAlienSorted(arrayOf("kuvp", "q"), "ngxlkthsjuoqcpavbfdermiywz")
        Assert.assertEquals(true, result)
    }

    fun isAlienSorted(words: Array<String>, order: String): Boolean {

        if (words.size < 2) return true

        val orderMap = mutableMapOf<Char, Int>()
        order.forEachIndexed { index, c ->
            orderMap[c] = index
        }

        var wordIndex = 0
        c@ while (wordIndex + 1 <= words.lastIndex) {

            val word1 = words[wordIndex]
            val word2 = words[wordIndex + 1]

            var charIndex = 0
            while (charIndex <= word1.lastIndex && charIndex <= word2.lastIndex) {

                val char1Position = orderMap[word1[charIndex]]!!
                val char2Position = orderMap[word2[charIndex]]!!

                if (char1Position > char2Position) return false
                if (char1Position < char2Position){
                    wordIndex++
                    continue@c
                }
                charIndex++
            }

            // check if the case is like apple & app
            if (word1.length > word2.length) return false

            wordIndex++
        }

        return true
    }
}