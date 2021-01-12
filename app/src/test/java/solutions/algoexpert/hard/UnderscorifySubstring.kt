package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Underscorify%20Substring
 */
class UnderscorifySubstring {

    @Test
    fun test() {
        val string =
            underscorifySubstring("testthis is a testtest to see if testestest it works", "test")
        assertEquals("_test_this is a _testtest_ to see if _testestest_ it works", string)
    }

    @Test
    fun test9() {
        val string =
            underscorifySubstring("tzttztttz", "ttt")
        assertEquals("tzttz_ttt_z", string)
    }

    @Test
    fun test11() {
        val string =
            underscorifySubstring("abcabcabcabcabcabcabcabcabcabcabcabcabcabc", "abc")
        assertEquals("_abcabcabcabcabcabcabcabcabcabcabcabcabcabc_", string)
    }

    @Test
    fun test8() {
        val string =
            underscorifySubstring("ttttttttttttttbtttttctatawtatttttastvb", "ttt")
        assertEquals("_tttttttttttttt_b_ttttt_ctatawta_ttttt_astvb", string)
    }

    fun underscorifySubstring(string: String, substring: String): String {

        if (string.isEmpty() || substring.isEmpty() || substring.length > string.length) return ""

        var startIndex = 0
        var endIndex = substring.lastIndex

        var underscoreStart = -1
        var underscorePotentialEnd = -1
        val underscoreList = mutableListOf<Int>()

        while (endIndex <= string.lastIndex) {

            val currentSelection = string.substring(startIndex, endIndex + 1)
            if (currentSelection == substring) {

                if (underscorePotentialEnd == -1) {
                    underscoreStart = startIndex
                }

                underscorePotentialEnd = endIndex

                if (endIndex == string.lastIndex) {
                    underscoreList.add(underscoreStart)
                    underscoreList.add(underscorePotentialEnd + 1)
                }
            } else {

                if (underscorePotentialEnd != -1 &&
                    (startIndex > underscorePotentialEnd
                            || endIndex == string.lastIndex)
                ) {
                    underscoreList.add(underscoreStart)
                    underscoreList.add(underscorePotentialEnd + 1)

                    underscorePotentialEnd = -1
                    underscoreStart = -1
                }
            }

            startIndex++
            endIndex++
        }

        val sb = java.lang.StringBuilder()

        string.forEachIndexed { index, c ->
            if (underscoreList.contains(index))
                sb.append("_")

            sb.append(c)
        }

        if (underscoreList.contains(string.length)) sb.append("_")
        return sb.toString()
    }

    fun underscorifySubstring2(string: String, substring: String): String {

        var subStringIndex = 0
        val list = mutableListOf<Pair<Int, Char>>()

        string.forEachIndexed { index, c ->

            if (c == substring[subStringIndex]) {

                if (subStringIndex == substring.lastIndex) {   // ending
                    list.add(Pair(index, 'E'))
                    subStringIndex = 0
                }

                if (subStringIndex == 0 && c == substring[subStringIndex]) {    // starting
                    list.add(Pair(index, 'S'))
                }

                subStringIndex++

            } else {
                subStringIndex = 0
                // remove if any non-closed one
                if (list.isNotEmpty() && list.last().second == 'S') {
                    list.removeAt(list.lastIndex)
                }

                if (subStringIndex == 0 && c == substring[subStringIndex]) {    // starting
                    list.add(Pair(index, 'S'))
                    subStringIndex++
                }
            }
        }

        val map = mutableMapOf<Pair<Int, Char>, Boolean>()

        list.forEachIndexed { index, pair ->

            map[pair] = false

            if (pair.second == 'S') {
                if (index - 1 >= 0 && list[index - 1].second == 'E') {
                    if (list[index - 1].first == list[index].first
                        || list[index - 1].first == (list[index].first - 1)
                    ) {
                        map[pair] = true
                        map[list[index - 1]] = true
                    }
                }
            }
        }

        val underscoreIndexes = map.filter { !it.value }.keys.map { it.first }
        val underscores = map.filter { !it.value }
        val sb = StringBuilder()

        string.forEachIndexed { index, c ->

            if (underscoreIndexes.contains(index)) {
                val letter = underscores.keys.find { it.first == index }?.second
                when (letter) {
                    'S' -> {
                        sb.append("_")
                        sb.append(c)
                    }
                    'E' -> {
                        sb.append(c)
                        sb.append("_")
                    }
                }

            } else {
                sb.append(c)
            }
        }

        return sb.toString()
    }
}