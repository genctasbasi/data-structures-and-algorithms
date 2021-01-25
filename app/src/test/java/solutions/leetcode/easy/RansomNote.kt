package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/ransom-note/
 */
class RansomNote {

    @Test
    fun test() {
        val result = canConstruct("a", "b")
        assertEquals(false, result)
    }

    @Test
    fun test2() {
        val result = canConstruct("aa", "ab")
        assertEquals(false, result)
    }

    @Test
    fun test3() {
        val result = canConstruct("aa", "aab")
        assertEquals(true, result)
    }

    @Test
    fun test4() {
        val result = canConstruct("xyz", "zaxbyc")
        assertEquals(true, result)
    }

    @Test
    fun test5() {
        val result = canConstruct("xyzz", "zaxbyc")
        assertEquals(false, result)
    }

    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        if (ransomNote.isEmpty()) return true
        if (magazine.isEmpty()) return false

        val map = mutableMapOf<Char, Int>()
        magazine.forEach {
            map[it] = (map[it] ?: 0) + 1
        }

        ransomNote.forEach {
            if (map[it] == null || map[it] == 0) return false else map[it] = map[it]!! - 1
        }

        return true
    }
}