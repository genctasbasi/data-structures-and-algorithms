package solutions.algoexpert.veryhard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Longest%20String%20Chain
 */
class LongestStringChain {

    @Test
    fun test() {
        val strings = listOf("abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef")
        val expected = listOf("abcdef", "abcde", "abde", "ade", "ae")
        val result = longestStringChain(strings)
        assertEquals(expected, result)
    }

    class Link(var size: Int, var chain: MutableList<String>)

    fun longestStringChain(strings: List<String>): List<String> {

        if (strings.isEmpty()) return listOf()

        val map = hashMapOf<String, Link>()

        // sort by length first
        val sorted = strings.sortedWith(compareBy { it.length })

        // ae, abc, ade, abd, abde, abcde, 1abde, abcdef
        // map
        // ae = 1
        // abc = 1
        // ade = 2

        sorted.forEach { word ->

            var wordMax = 0
            var chain = mutableListOf<String>()
            // val built = StringBuilder()

            word.forEachIndexed { index, char ->
                val sb = StringBuilder(word)
                val deleted = sb.deleteCharAt(index)

                map[deleted.toString()]?.let { link ->
                    if (link.size > wordMax) {
                        chain = link.chain
                        wordMax = link.size
                    }
                }
            }

            chain.add(word)
            val newList = mutableListOf<String>()
            newList.addAll(chain)
            map[word] = Link(wordMax + 1, newList)
        }

        var max = 0
        var chain = mutableListOf<String>()

        map.forEach {
            if (it.value.size > max) {
                max = it.value.size
                chain = it.value.chain
            }
        }

        // don't return if it has a single element - single element means
        // actually there is no chain
        return if (chain.size == 1) listOf() else chain.sortedWith(compareBy { -it.length })
    }
}