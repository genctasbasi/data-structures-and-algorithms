package solutions.algoexpert.medium

import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Suffix%20Trie%20Construction
 */

class SuffixTrieConstruction {

    @Test
    fun test() {
        val suffixTrie = SuffixTrie("babc")

        assertTrue(suffixTrie.contains("c"))
        assertTrue(suffixTrie.contains("bc"))
        assertTrue(suffixTrie.contains("abc"))
        assertTrue(suffixTrie.contains("babc"))

        assertFalse(suffixTrie.contains("xx"))
        assertFalse(suffixTrie.contains("bab"))
        assertFalse(suffixTrie.contains("ab"))
    }

    data class TrieNode(
        val children: MutableMap<Char, TrieNode> = mutableMapOf()
    )

    class SuffixTrie(str: String) {
        private val endSymbol = '*'
        var root = TrieNode()

        init {
            populate(str)
        }

        fun populate(str: String) {

            for (i in str.indices) {
                var trieNode = root

                for (j in i until str.length) {
                    val char = str[j]

                    if (trieNode.children[char] == null) {   // this node is new
                        trieNode.children[char] = TrieNode()
                    }

                    trieNode = trieNode.children[char]!!
                }

                // put an end
                trieNode.children[endSymbol] = TrieNode()
            }
        }

        fun contains(str: String): Boolean {
            var trie = root
            str.forEach {

                if (trie.children[it] == null)
                    return false
                else
                    trie = trie.children[it]!!
            }

            return trie.children[endSymbol] != null
        }
    }
}