package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/find-and-replace-in-string/
 */
class FindAndReplaceInString {

    @Test
    fun test() {
        assertEquals(
            "eeebffff",
            findReplaceString("abcd", intArrayOf(0, 2), arrayOf("a", "cd"), arrayOf("eee", "ffff"))
        )
    }

    @Test
    fun test1() {
        assertEquals(
            "vbfrssozp",
            findReplaceString(
                "vmokgggqzp",
                intArrayOf(3, 5, 1),
                arrayOf("kg", "ggq", "mo"),
                arrayOf("s", "so", "bfr")
            )
        )
    }

    @Test
    fun test2() {
        assertEquals(
            "vbfrssozp",
            findReplaceString(
                "wqzzcbnwxc",
                // wqzzcbnnee
                intArrayOf(5, 2, 7),
                arrayOf("bn", "zzc", "wxc"),
                arrayOf("t", "lwb", "nee")
            )
        )
    }

    @Test
    fun test3() {
        assertEquals(
            "jjievdtjfb",
            findReplaceString(
                "jjievdtjfb",
                intArrayOf(4, 6, 1),
                arrayOf("md", "tjgb", "jf"),
                arrayOf("foe", "oov", "e")
            )
        )
    }

    @Test
    fun test4() {
        assertEquals(
            "crqymfyijmtdrnhccms",
            findReplaceString(
                "cizokxcijwbyspcfcqws",
                intArrayOf(17, 1, 14, 3, 9, 11),
                arrayOf("qw", "iz", "cf", "okxc", "wb", "ysp"),
                arrayOf("m", "rq", "hc", "ymfy", "mt", "drn")
            )
        )
    }

    class Change(val index: Int, val source: String, val target: String)

    fun findReplaceString(
        S: String,
        indexes: IntArray,
        sources: Array<String>,
        targets: Array<String>
    ): String {

        if (S.isEmpty()) return S
        if (indexes.isEmpty()) return S

        val changes = mutableListOf<Change>()
        var p = 0
        var output = S

        while (p <= indexes.lastIndex) {
            changes.add(Change(indexes[p], sources[p], targets[p]))
            p++
        }

        val changesSorted = changes.sortedWith(compareBy { it.index }).reversed()

        changesSorted.forEachIndexed { index, it ->

            val sourceString = it.source
            val stringInS = S.substring(it.index, it.index + it.source.length)

            if (sourceString == stringInS) {
                output = replace(output, it.index, it.source, it.target)
            }
        }

        return output
    }

    /**
     * This didn't work, obviously for the reason that the first edit changes the string and the indexes
     * for the following changes don't work.
     */
//    fun findReplaceStringFirstTry(
//        S: String,
//        indexes: IntArray,
//        sources: Array<String>,
//        targets: Array<String>
//    ): String {
//
//        if (S.isEmpty()) return S
//        if (indexes.isEmpty()) return S
//
//        var output = S
//
//        var offset = 0
//        indexes.forEachIndexed { index, it ->
//
//            val sourceString = sources[index]
//            val stringInS = S.substring(it, it + sources[index].length)
//
//            if (sourceString == stringInS) {
//                val result = replace(
//                    output,
//
//                    if (index != 0 && indexes[index - 1] < indexes[index])
//                        it + offset else it,
//
//                    sources[index], targets[index]
//                )
//                output = result.first
//                offset += result.second
//            }
//        }
//
//        return output
//    }

    private fun replace(
        output: String,
        startIndex: Int,
        sourceString: String,
        targetString: String
    ): String {

        val prefix = output.substring(0, startIndex)
        val suffix = output.substring(startIndex + sourceString.length)

        return prefix + targetString + suffix
    }
}