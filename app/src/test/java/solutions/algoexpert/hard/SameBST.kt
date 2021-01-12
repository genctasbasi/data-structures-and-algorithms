package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Same%20BSTs
 * Not allowed to construct a BST - just work on the array
 */
class SameBST {

    @Test
    fun test() {

        val list1 = listOf(10, 15, 8, 12, 94, 81, 5, 2, 11)
        val list2 = listOf(10, 8, 5, 15, 2, 12, 11, 64, 81)

        val isSame = sameBsts(list1, list2)
        assertEquals(true, isSame)
    }

    fun sameBsts(arrayOne: List<Int>, arrayTwo: List<Int>): Boolean {

        if (arrayOne.isEmpty() && arrayTwo.isEmpty()) return true

        if (arrayOne.first() != arrayTwo.first() || arrayOne.size != arrayTwo.size) return false

        if (arrayOne.size == 1 && arrayTwo.size == 1
            && arrayOne.first() == arrayTwo.first()
        ) return true

        val smaller1 = getSmaller(arrayOne.first(), arrayOne.subList(1, arrayOne.size))
        val smaller2 = getSmaller(arrayTwo.first(), arrayTwo.subList(1, arrayTwo.size))

        val areSmallsSame = sameBsts(smaller1, smaller2)

        val bigger1 = getBigger(arrayOne.first(), arrayOne.subList(1, arrayOne.size))
        val bigger2 = getBigger(arrayTwo.first(), arrayTwo.subList(1, arrayTwo.size))

        val areBigsSame = sameBsts(bigger1, bigger2)

        return arrayOne.first() == arrayTwo.first() &&
                areSmallsSame && areBigsSame
    }

    private fun getSmaller(value: Int, array: List<Int>) = array.filter { it <= value }
    private fun getBigger(value: Int, array: List<Int>) = array.filter { it >= value }
}