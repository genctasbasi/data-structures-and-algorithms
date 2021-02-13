package solutions.leetcode.hard

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/string-transforms-into-another-string/
 */
class StringTransformsIntoAnotherString {

    @Test
    fun test() {
        val result = canConvert("aabcc", "ccdee")
        Assert.assertEquals(true, result)
    }

    @Test
    fun test1() {
        val result = canConvert("aabcce", "ccdeea")
        Assert.assertEquals(false, result)
    }

    @Test
    fun test2() {
        val result = canConvert("leetcode", "codeleet")
        Assert.assertEquals(false, result)
    }

    @Test
    fun test3() {
        val result = canConvert("xxx", "xxx")
        Assert.assertEquals(true, result)
    }

    @Test
    fun test4() {
        val result = canConvert("ab", "ba")
        Assert.assertEquals(true, result)
    }

    @Test
    fun test5() {
        val result = canConvert("abcdefghijklmnopqrstuvwxyz", "bcadefghijklmnopqrstuvwxzz")
        Assert.assertEquals(true, result)
    }

    @Test
    fun test6() {
        val result = canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza")
        Assert.assertEquals(false, result)
    }

    /**
     * I tried checking a cycle but apparently we don't need it. An 'extra' character can help to 'break' a cycle,
     * that 'extra' character being a one that doesn't exist in str2.
     * Therefore all we need to do is to make sure every vertex has one edge (a character from str1 is
     * pointing to only one character in str2) AND if we used all 26 chars in both strings, they need to be same (an edge case)
     *
     * I think one need to explain to the interviewee why an extra character can break a cycle.
     */
    fun canConvert(str1: String, str2: String): Boolean {
        if (str1 == str2) return true
        if (str1.isEmpty() || str2.isEmpty()) return false
        if (str1.length != str2.length) return false

        val graph = mutableMapOf<Char, Char>()
        val str2Set = hashSetOf<Char>()

        str1.forEachIndexed { index, it ->
            if (graph[it] != null && graph[it] != str2[index]) {
                return false
            }
            str2Set.add(str2[index])
            graph[it] = str2[index]
        }

        // well this is what I 'copied' from one of the solutions, couldn't think of myself
        // App
        if (str2Set.size == 26 && graph.size == 26 && str2 != str1) {
            return false
        }

        return true
    }

    fun canConvert2(str1: String, str2: String): Boolean {

        if (str1 == str2) return true
        if (str1.isEmpty() || str2.isEmpty()) return false
        if (str1.length != str2.length) return false

        val graph = mutableMapOf<Char, Char>()

        str1.forEachIndexed { index, it ->
            if (graph[it] != null && graph[it] != str2[index]) return false
            graph[it] = str2[index]
        }

        val visited = hashMapOf<Char, Int>()
        graph.keys.forEach {
            if (isCyclic(graph, it, visited)) return false
        }

        return true
    }

    // 0: not visited, 1: visited, 2: visiting
    fun isCyclic(graph: Map<Char, Char>, char: Char?, visited: HashMap<Char, Int>): Boolean {

        if (char == null) return false

        if (visited[char] == 2) return true
        visited[char] = 2

        if (isCyclic(graph, graph[char], visited))
            return true


        visited[char] = 1
        return false
    }
}