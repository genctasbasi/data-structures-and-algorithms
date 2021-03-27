package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/simplify-path/
 */
class SimplifyPath {

    @Test
    fun test() {
        assertEquals("/home", simplifyPath("/home/"))
    }

    @Test
    fun test1() {
        assertEquals("/", simplifyPath("/../"))
    }

    @Test
    fun test2() {
        assertEquals("/c", simplifyPath("/a/./b/../../c/"))
    }

    @Test
    fun test3() {
        assertEquals("/home/foo", simplifyPath("/home//foo/"))
    }

    fun simplifyPath(path: String): String {

        val list = path.split('/')

        val stack = Stack<String>()
        list.forEach {
            when (it) {
                "" -> {
                    // ignore
                }

                ".." -> {
                    if (stack.isNotEmpty()) stack.pop()
                }

                "." -> {
                    // ignore, stay in same folder
                }

                else -> {   // folder name, add to stack
                    stack.push(it)
                }

            }
        }
        val sb = StringBuilder()
        stack.forEach {
            sb.append("/$it")
        }

        if (sb.isEmpty()) sb.append("/")
        return sb.toString()
    }
}