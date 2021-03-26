package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/group-shifted-strings/
 */
class GroupShiftedStrings {

    @Test
    fun test() {

        val list =
            groupStrings(arrayOf("abc", "bcd", "acef", "xyz", "az", "ba", "a", "z")).toTypedArray()

        assertArrayEquals(
            arrayOf(
                listOf("acef"),
                listOf("a", "z"),
                listOf("abc", "bcd", "xyz"),
                listOf("az", "ba")
            ), list
        )
    }

    @Test
    fun test1() {

        val list =
            groupStrings(arrayOf("ab", "ba")).toTypedArray()

        assertArrayEquals(
            arrayOf(
                listOf("ba"),
                listOf("ab")
            ), list
        )
    }

    @Test
    fun test2() {

        val list =
            groupStrings(arrayOf("eqdf", "qcpr")).toTypedArray()

        assertArrayEquals(
            arrayOf(
                listOf("eqdf", "qcpr")
            ), list
        )
    }

    @Test
    fun test3() {

        val list =
            groupStrings(
                arrayOf(
                    "fpbnsbrkbcyzdmmmoisaa",
                    "cpjtwqcdwbldwwrryuclcngw",
                    "a",
                    "fnuqwejouqzrif",
                    "js",
                    "qcpr",
                    "zghmdiaqmfelr",
                    "iedda",
                    "l",
                    "dgwlvcyubde",
                    "lpt",
                    "qzq",
                    "zkddvitlk",
                    "xbogegswmad",
                    "mkndeyrh",
                    "llofdjckor",
                    "lebzshcb",
                    "firomjjlidqpsdeqyn",
                    "dclpiqbypjpfafukqmjnjg",
                    "lbpabjpcmkyivbtgdwhzlxa",
                    "wmalmuanxvjtgmerohskwil",
                    "yxgkdlwtkekavapflheieb",
                    "oraxvssurmzybmnzhw",
                    "ohecvkfe",
                    "kknecibjnq"
                )
            ).toTypedArray()

        assertArrayEquals(
            arrayOf(
                listOf("eqdf", "qcpr")
            ), list
        )
    }

    fun groupStrings(strings: Array<String>): List<List<String>> {
        if (strings.size == 1) return listOf(listOf(strings[0]))

        val output = mutableListOf<List<String>>()
        val map = hashMapOf<String, MutableList<String>>()

        strings.forEach {
            val len = it.length

            var found = false
            map.keys.forEach { key ->
                if (key.length == len && isShifted(key, it)) {
                    map[key]!!.add(it)
                    found = true
                }
            }

            if (!found) {
                map[it] = mutableListOf(it)
            }
        }

        map.values.forEach { output.add(it) }

        return output
    }

    private fun isShifted(string1: String, string2: String): Boolean {

        if (string1.length != string2.length) return false

        if (string1.length == 1) return true

        var diff = string2[0] - string1[0]
        if (diff < 0) diff += 26

        var p = 1
        while (p <= string1.lastIndex) {
            val char1 = (string1[p] - 97).toInt() // z: 25
            val char2 = (string2[p] - 97).toInt() // a:0

            var diffCheck = char2 - char1
            if (diffCheck < 0) diffCheck += 26

            if (diff != diffCheck) {
                return false
            }

            p++
        }

        return true
    }
}