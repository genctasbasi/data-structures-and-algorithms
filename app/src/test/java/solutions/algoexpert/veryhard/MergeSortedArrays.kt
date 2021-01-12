package solutions.algoexpert.veryhard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Merge%20Sorted%20Arrays
 */
class MergeSortedArrays {

    @Test
    fun test() {
        val sort = mergeSortedArrays(
            listOf(
                listOf(1, 5, 9, 21),
                listOf(-1, 0),
                listOf(-124, 81, 121),
                listOf(3, 6, 12, 20, 150)
            )
        )

        assertEquals(listOf(-124, -1, 0, 1, 3, 5, 6, 9, 12, 20, 21, 81, 121, 150), sort)
    }

    fun mergeSortedArrays(arrays: List<List<Int>>): List<Int> {

        var merged = listOf<Int>()

        arrays.forEach { array ->
            merged = merge(merged, array)
        }

        return merged
    }

    private fun merge(array1: List<Int>, array2: List<Int>): List<Int> {
        if (array1.isEmpty()) return array2
        if (array2.isEmpty()) return array1

        val merged = mutableListOf<Int>()
        var p1 = 0
        var p2 = 0

        var item1: Int? = array1[p1]
        var item2: Int? = array2[p2]

        while (item1 != null || item2 != null) {

            when {

                item1 == null -> {
                    merged.add(item2!!)
                    p2++
                }
                item2 == null -> {
                    merged.add(item1)
                    p1++
                }
                else -> {

                    if (item1 < item2) {
                        merged.add(item1)
                        p1++
                    } else {
                        merged.add(item2)
                        p2++
                    }
                }
            }


            item1 = if (p1 < array1.size) array1[p1] else null
            item2 = if (p2 < array2.size) array2[p2] else null
        }

        return merged.toList()
    }
}