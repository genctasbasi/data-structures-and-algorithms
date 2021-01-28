package solutions.algoexpert.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/First%20Duplicate%20Value
 *
 */
class FirstDuplicateValue {

    @Test
    fun testCase1() {
        val result = firstDuplicateValue(mutableListOf(2, 1, 5, 2, 3, 3, 4))
        assertEquals(2, result)
    }

    fun firstDuplicateValue(array: MutableList<Int>): Int {

        val map = mutableMapOf<Int, Unit>()
        array.forEach {
            if (map[it] != null) return it

            map[it] = Unit
        }

        return -1
    }

}