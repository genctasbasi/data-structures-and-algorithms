package solutions.leetcode.easy

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/missing-ranges/
 */
class MissingRanges {

    @Test
    fun test() {
        val result = findMissingRanges(intArrayOf(0, 1, 3, 50, 75), 0, 99).toTypedArray()
        assertArrayEquals(arrayOf("2", "4->49", "51->74", "76->99"), result)
    }

    @Test
    fun test1() {
        val result = findMissingRanges(intArrayOf(), 1, 1).toTypedArray()
        assertArrayEquals(arrayOf("1"), result)
    }

    @Test
    fun test2() {
        val result = findMissingRanges(intArrayOf(), -3, -1).toTypedArray()
        assertArrayEquals(arrayOf("-3->-1"), result)
    }

    @Test
    fun test3() {
        val result = findMissingRanges(intArrayOf(-1), -1, -1).toTypedArray()
        assertArrayEquals(arrayOf(), result)
    }

    @Test
    fun test4() {
        val result = findMissingRanges(intArrayOf(-1), -2, -1).toTypedArray()
        assertArrayEquals(arrayOf("-2"), result)
    }

    @Test
    fun test5() {
        val result = findMissingRanges(
            intArrayOf(-1000000000, 1000000000),
            -1000000000,
            1000000000
        ).toTypedArray()
        assertArrayEquals(arrayOf("-999999999->999999999"), result)
    }

    // 5, 1, 3, 50, 75
    // 0, 99
    fun findMissingRanges(
        nums: IntArray,
        lower: Int,
        upper: Int
    ): List<String> {
        var lower = lower
        val res: MutableList<String> = ArrayList()
        for (i in nums) {
            if (i > lower) res.add(lower.toString() + if (i - 1 > lower) "->" + (i - 1) else "")
            if (i == upper) return res // Avoid overflow
            lower = i + 1
        }
        if (lower <= upper) res.add(lower.toString() + if (upper > lower) "->$upper" else "")
        return res
    }

    fun findMissingRanges2(nums: IntArray, lower: Int, upper: Int): List<String> {

        val set = hashSetOf<Int>()
        set.addAll(nums.toList())

        var missingFrom: Int? = null
        val output = mutableListOf<String>()
        (lower..upper).forEach {

            if (!set.contains(it)) {
                if (missingFrom == null) missingFrom = it
            } else {
                if (missingFrom != null) {
                    val missingRange =
                        if (missingFrom == it - 1) missingFrom.toString() else "$missingFrom->${it - 1}"
                    output.add(missingRange)
                    missingFrom = null
                }
            }
        }

        if (missingFrom != null) {
            val missingRange =
                if (missingFrom == upper) missingFrom.toString() else "$missingFrom->$upper"
            output.add(missingRange)
        }

        return output
    }
}
