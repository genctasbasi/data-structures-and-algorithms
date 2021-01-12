package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Shorten%20Path
 */
class ShortenPath {

    @Test
    fun test() {
        val path = shortenPath("/foo/../test/../test/../foo//bar/./baz")
        assertEquals("/foo/bar/baz", path)
    }

    @Test
    fun test3() {
        val path = shortenPath("foo/bar/baz")
        assertEquals("foo/bar/baz", path)
    }

    @Test
    fun test4() {
        val path = shortenPath("/../../foo/bar/baz")
        assertEquals("/foo/bar/baz", path)
    }

    @Test
    fun test5() {
        val path = shortenPath("../../foo/bar/baz")
        assertEquals("../../foo/bar/baz", path)
    }

    fun shortenPath(path: String): String {

        val stack = java.util.Stack<String>()
        val tokens = path.split("/")
        val isAbsolute = path.startsWith("/")

        tokens.forEachIndexed { index, token ->
            when (token) {
                ".." -> {
                    if (stack.isEmpty()) {
                        if (!isAbsolute)
                            stack.add(token)
                    } else if (stack.peek() == "..")
                        stack.add(token)
                    else
                        stack.pop()
                }
                "" -> {
                }
                "." -> {
                }
                else -> {
                    stack.add(token)
                }
            }
        }

        val final = stack.joinToString("") { "$it/" }.dropLast(1)
        return if (isAbsolute && !final.startsWith("/")) "/$final" else final
    }
}