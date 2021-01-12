package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Pattern%20Matcher
 */
class PatternMatcher {

    @Test
    fun test() {
        val result =
            patternMatcher("xxyxxy", "gogopowerrangergogopowerranger")
        assertEquals(listOf("go", "powerranger"), result)
    }

    @Test
    fun test2() {
        val result =
            patternMatcher("yxyx", "abab")
        assertEquals(listOf("b", "a"), result)
    }


    @Test
    fun test3() {
        val result =
            patternMatcher("yxx", "yomama")
        assertEquals(listOf("yo", "ma"), result)
    }

    @Test
    fun test7() {
        val result =
            patternMatcher("xxxx", "testtesttesttest")
        assertEquals(listOf("test"), result)
    }

    @Test
    fun test8() {
        val result =
            patternMatcher("yyyy", "testtesttesttest")
        assertEquals(listOf("test"), result)
    }

    fun patternMatcher(pattern: String, string: String): List<String> {

        val length = string.length
        val xCount = pattern.count { it == 'x' }
        val yCount = pattern.count { it == 'y' }
        
        val possibleLengths = mutableListOf<Pair<Int, Int>>()

        if (xCount == 0) {
            return listOf("", string.substring(0, yCount))
        }

        if (yCount == 0) {
            return listOf(string.substring(0, xCount), "")
        }

        var x = 1   // first possible length
        var y = (length - (x * xCount)) / yCount
        while (y >= 0) {
            possibleLengths.add(Pair(x, y))
            x++
            y = (length - (x * xCount)) / yCount
        }

        val set = mutableSetOf<String>()

        possibleLengths.forEach {

            var currentReadIndex = 0
            if (set.size == 2) return if (pattern.first() == 'x') set.toList() else set.toList()
                .reversed()
            set.clear()

            for (patternLetter in pattern.toCharArray()) {
                when (patternLetter) {
                    'x' -> {
                        set.add(string.substring(currentReadIndex, currentReadIndex + it.first))
                        currentReadIndex += it.first
                    }
                    'y' -> {
                        set.add(string.substring(currentReadIndex, currentReadIndex + it.second))
                        currentReadIndex += it.second
                    }
                }

                if (set.size > 2) break
            }
        }

        return listOf()
    }
}