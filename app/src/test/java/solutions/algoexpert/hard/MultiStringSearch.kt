package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Pattern%20Matcher
 */
class MultiStringSearch {

    @Test
    fun test() {
        val result =
            multiStringSearch(
                "this is a big string",
                listOf("this", "yo", "is", "a", "bigger", "string", "kappa")
            )
        assertEquals(listOf(true, false, true, true, false, true, false), result)
    }

    @Test
    fun test2() {
        val result =
            multiStringSearch(
                "abcdefghijklmnopqrstuvwxyz",
                listOf("abc", "mnopqr", "wyz", "no", "e", "tuuv")
            )
        assertEquals(listOf(true, true, false, true, true, false), result)
    }

    @Test
    fun testMine() {
        val result =
            multiStringSearch(
                "nonsense",
                listOf("ons", "xyz", "sense")
            )
        assertEquals(listOf(true, true, false, true, true, false), result)
    }

    @Test
    fun test12() {
        val result =
            multiStringSearch(
                "bbbabb",
                listOf("bbabb")
            )
        assertEquals(listOf(true), result)
    }

    class TrieNode(val children: MutableMap<Char, TrieNode?>)

    fun multiStringSearch(bigString: String, smallStrings: List<String>): List<Boolean> {

        // it will be SUFFIX-tree!
        val root: TrieNode = buildSuffixTrie(bigString)
        val booleanList = mutableListOf<Boolean>()
        var activeNode = root

        // now search for words
        smallStrings.forEach { word ->

            var found = true
            for (i in word.indices) {
                val char = word[i]
                if (activeNode.children[char] == null) {
                    found = false
                    booleanList.add(false)
                    break
                } else {
                    activeNode = activeNode.children[char]!!
                }
            }

            if (found)
                booleanList.add(true)

            activeNode = root
        }

        return booleanList
    }

    private fun buildSuffixTrie(bigString: String): TrieNode {

        val root = TrieNode(mutableMapOf())

        for (i in bigString.lastIndex downTo 0) {
            val word = bigString.substring(i, bigString.lastIndex + 1)
            addToTrie(root, word)
        }

        return root
    }

    private fun addToTrie(root: TrieNode, word: String) {

        var activeNode: TrieNode = root
        word.forEach { char ->
            if (activeNode.children[char] == null) {
                activeNode.children[char] = TrieNode(mutableMapOf())
            }

            activeNode = activeNode.children[char]!!
        }
    }

    fun multiStringSearchWithPointers(
        bigString: String,
        smallStrings: List<String>
    ): List<Boolean> {

        val subStringPointers = IntArray(smallStrings.size) { 0 }
        val subStringHasStartedTracking = BooleanArray(smallStrings.size) { false }
        val resultBoolean = Array<Boolean?>(smallStrings.size) { null }

        bigString.forEachIndexed { pMain, char ->

            subStringPointers.forEachIndexed { pWord, i ->

                if (resultBoolean[pWord] == null) {

                    val substringCurrentWord = smallStrings[pWord]
                    val charIndex = subStringPointers[pWord]

                    if (charIndex < substringCurrentWord.length) {
                        val substringCurrentLetter = substringCurrentWord[charIndex]

                        if (substringCurrentLetter == char) {
                            subStringHasStartedTracking[pWord] = true
                            subStringPointers[pWord] = charIndex + 1
                        } else {
                            if (subStringHasStartedTracking[pWord]) { // we were already tracking and it failed
                                subStringHasStartedTracking[pWord] = false  // reset
                                subStringPointers[pWord] = 0
                            }
                        }
                    }

                    if (subStringHasStartedTracking[pWord] && i == substringCurrentWord.lastIndex) {
                        resultBoolean[pWord] = true // it exists
                    }
                }
            }
        }

        return resultBoolean.map { it ?: false }.toList()
    }
}
