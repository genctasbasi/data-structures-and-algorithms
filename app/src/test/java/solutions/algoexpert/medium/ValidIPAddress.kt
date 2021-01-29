package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Valid%20IP%20Addresses
 *
 */
class ValidIPAddress {

    @Test
    fun testCase1() {
        val result = validIPAddresses("0001")
        assertEquals(listOf<String>(), result)
    }

    @Test
    fun testCase2() {
        val result = validIPAddresses("1921680")
        assertEquals(
            listOf(
                "1.9.216.80",
                "1.92.16.80",
                "1.92.168.0",
                "19.2.16.80",
                "19.2.168.0",
                "19.21.6.80",
                "19.21.68.0",
                "19.216.8.0",
                "192.1.6.80",
                "192.1.68.0",
                "192.16.8.0"
            ), result
        )
    }

    fun validIPAddresses(string: String): List<String> {

        if (string.length < 4) return emptyList()

        val list = mutableListOf<String>()

        var p0 = 1
        var p1 = 2
        var p2 = 3

        while (p0 < p1 && p1 < p2 && p2 < string.length) {

            val part1 = string.substring(0, p0)

            while (p1 < p2 && p2 < string.length) {


                val part2 = string.substring(p0, p1)

                while (p2 < string.length) {

                    val part3 = string.substring(p1, p2)
                    val part4 = string.substring(p2, string.lastIndex + 1)

                    if (isValid(listOf(part1, part2, part3, part4))) {
                        list.add("$part1.$part2.$part3.$part4")
                    }

                    p2++
                }

                p1++
                p2 = p1 + 1
            }

            p0++
            p1 = p0 + 1
            p2 = p1 + 1
        }

        return list
    }

    fun isValid(parts: List<String>): Boolean {

        parts.forEach {
            if (it.length > 1 && it.startsWith('0')) return false
            if (it.length > 3) return false
            if (it.toInt() > 255) return false
        }

        return true
    }

}