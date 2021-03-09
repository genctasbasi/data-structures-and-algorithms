package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 */
class DesignAddAndSearchWordsDataStructure {

    @Test
    fun test() {
        val dict = WordDictionary()

        dict.addWord("bad")
        dict.addWord("dad")
        dict.addWord("mad")

        assertEquals(false, dict.search("pad"))
        assertEquals(true, dict.search("bad"))
        assertEquals(false, dict.search("bak"))
        assertEquals(false, dict.search("bads"))

        assertEquals(true, dict.search(".ad"))
        assertEquals(true, dict.search("b.."))
        assertEquals(true, dict.search("b.d"))
        assertEquals(false, dict.search("mad."))
        assertEquals(false, dict.search(".mad"))
    }

    class TrieNode {
        val children: MutableMap<Char, TrieNode> = mutableMapOf()
    }

    class WordDictionary {

        val root = TrieNode()

        fun addWord(word: String) {

            var node = root

            word.forEach { char ->
                if (node.children[char] == null) {  // this is new
                    node.children[char] = TrieNode()
                }
                node = node.children[char]!!
            }

            node.children['*'] = TrieNode()
        }

        fun search(word: String): Boolean {
            return searchInNode(word, root)
        }

        fun searchInNode(word: String, searchNode: TrieNode): Boolean {

            var node = searchNode

            word.forEachIndexed { index, char ->

                if (node.children[char] == null) {
                    if (char == '.') {    // joker
                        node.children.forEach { child ->
                            val hasChar = searchInNode(word.substring(index + 1), child.value)
                            if (hasChar) return true
                        }

                    }

                    return false

                } else {    // continue down the line
                    node = node.children[char]!!
                }
            }

            return node.children['*'] != null   // it's a 'word'
        }
    }
}