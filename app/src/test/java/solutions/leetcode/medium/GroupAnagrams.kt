package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/group-anagrams/
 */
class GroupAnagrams {

    @Test
    fun test() {
        val result = groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat"))
        assertEquals(
            listOf(
                listOf("bat"),
                listOf("nat", "tan"),
                listOf("ate", "eat", "tea")
            ), result
        )
    }

    fun groupAnagrams(strs: Array<String>): List<List<String>> {

        if (strs.isEmpty()) return listOf()
        if (strs.size == 1) return listOf(listOf(strs[0]))

        val strsSorted = Array(strs.size) { "" }

        strs.forEachIndexed { index, it ->
            strsSorted[index] = it.toCharArray().sorted().joinToString("")
        }

        val map = mutableMapOf<String, MutableList<String>>()

        strsSorted.forEachIndexed { index, it ->
            if (map[it] == null) {
                map[it] = mutableListOf(strs[index])
            } else {
                map[it]?.add(strs[index])
            }
        }

        val groups = mutableListOf<List<String>>()

        map.values.forEach {
            groups.add(it)
        }

        return groups
    }
}