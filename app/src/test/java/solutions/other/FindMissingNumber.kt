package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Test

class FindMissingNumber {

    @Test
    fun `find the missing number in the array`() {
        // given
        val list = listOf(1, 5, 8, 9, 3, 7, 4, 6, 0)

        // when
        val missingNumber = findMissingNumberBySorting(list)
        val missingNumber2 = findMissingWithMinAndMax(list)

        assertEquals(2, missingNumber2)
    }

    /**
     * with min() and max() - O(n)
     * */
    private fun findMissingWithMinAndMax(list: List<Int>): Int? {

        val mutableList = list.toMutableList()

        val min = list.min()
        val max = list.max()

        (min..max).forEach {
            val isRemoved = mutableList.remove(it)
            if (!isRemoved) return it
        }

        // all exits
        return null
    }


    /**
     * By sorting
     * O(nlog(n) & O(n) for the second loop so it's O(nlog(n)) in total
     */
    private fun findMissingNumberBySorting(list: List<Int>): Int? {

        var current: Int? = null
        val sortedList = list.sorted()

        sortedList.forEachIndexed { index, i ->
            if (current != null) {

                if (index + 1 == sortedList.size) {
                    return null
                } else {
                    val next = sortedList[index]
                    if (current!! + 1 != next) {
                        return current!! + 1
                    }
                }
            }

            current = i
        }

        return null
    }
}