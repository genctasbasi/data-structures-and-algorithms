package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/word-break/
 */
class WordBreak {

    @Test
    fun test() {
        val result = wordBreak("leetcode", listOf("leet", "code"))
        assertEquals(true, result)
    }

    @Test
    fun test2() {
        val result = wordBreak("aaaaa", listOf("aa", "aaa"))
        assertEquals(true, result)
    }

    fun wordBreak(s: String, wordDict: List<String>): Boolean {

        if (wordDict.isEmpty()) return false

        // create a map
        val map = HashSet<String>()
        wordDict.forEach { map.add(it) }

        return exists(s, 0, map, hashMapOf())
    }

    fun exists(
        s: String,
        startIndex: Int,
        lookup: HashSet<String>,
        mem: HashMap<Int, Boolean>
    ): Boolean {

        if (startIndex > s.lastIndex) return true
        if (mem[startIndex] != null) return mem[startIndex]!!

        var index = startIndex
        val sb = StringBuilder()

        while (index <= s.lastIndex) {

            sb.append(s[index])

            if (lookup.contains(sb.toString())) {
                val exists = exists(s, index + 1, lookup, mem)
                mem[index + 1] = exists
                if (exists) return true
            }

            index++
        }
        mem[index] = false
        return false
    }
}