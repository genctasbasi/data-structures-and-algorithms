package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Index%20Equals%20Value
 */
class IndexEqualsValue {

    @Test
    fun test() {
        val result =
            indexEqualsValue(mutableListOf(-12, 1, 2, 3, 12))
        assertEquals(1, result)
    }

    fun indexEqualsValue(array: List<Int>): Int {

        if(array.isEmpty()) return -1

        val index = indexEqualsValue(array, 0, array.lastIndex)
        return if (index == Int.MAX_VALUE) -1 else index
    }

    fun indexEqualsValue(array: List<Int>, left: Int, right: Int): Int {

        if (left == right || right < 0) {
            return if (array[left] == left) left else Int.MAX_VALUE
        }

        if (left > right){
            return if (array[right] == right) left else Int.MAX_VALUE
        }

        val mid = (left + right) / 2

        val indexLeft = indexEqualsValue(array, left, mid - 1)
        val indexRight = indexEqualsValue(array, mid + 1, right)

        return when {
            array[mid] == mid ->
                Math.min(Math.min(indexLeft, indexRight), mid)

            else -> Math.min(indexLeft, indexRight)
        }
    }

}