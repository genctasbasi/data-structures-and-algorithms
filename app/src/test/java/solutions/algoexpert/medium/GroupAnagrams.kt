package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * https://www.algoexpert.io/questions/Group%20Anagrams
 */

class GroupAnagrams {

    @Test
    fun test() {
        val result = groupAnagrams(listOf("yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"))
        Assert.assertEquals(
            listOf(
                listOf("yo", "oy"),
                listOf("flop", "olfp"),
                listOf("act", "tac", "cat"),
                listOf("foo")
            ), result
        )
    }

    fun groupAnagrams(words: List<String>): List<List<String>> {

        val map = mutableMapOf<String, MutableList<String>>()

        words.forEach { word ->
            val sorted = sort(word)

            if (map.containsKey(sorted)) {
                map[sorted]?.add(word)
            } else {
                map[sorted] = mutableListOf(word)
            }
        }

        val list = mutableListOf<List<String>>()
        list.addAll(map.values)

        return list
    }

    fun sort(value: String): String {
        val charArray = value.toCharArray()

        Arrays.sort(charArray)
        return String(charArray)
    }
}