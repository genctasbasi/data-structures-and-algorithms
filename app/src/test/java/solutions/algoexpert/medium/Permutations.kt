package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Permutations
 */
class Permutations {

    @Test
    fun test() {

        val permutations = getPermutations(listOf(1, 2, 3,4,5))
        val expected = listOf(
            listOf(1, 2, 3),
            listOf(1, 3, 2),
            listOf(2, 1, 3),
            listOf(2, 3, 1),
            listOf(3, 1, 2),
            listOf(3, 2, 1)
        )

        Assert.assertEquals(expected, permutations)
    }

    fun getPermutations(array: List<Int>): List<List<Int>> {

        if(array.isEmpty()) return listOf()
        if (array.size == 1) return listOf(array)

        val firstElement = array[0]
        val subArray = array.subList(1, array.size)

        val subPermutations = getPermutations(subArray)

        val lists = mutableListOf<List<Int>>()

        subPermutations.forEach {

            it.forEachIndexed { index, i ->
                val list = it.toMutableList()
                list.add(index, firstElement)
                lists.add(list)
            }

            // add to the end too
            val list = it.toMutableList()
            list.add(firstElement)
            lists.add(list)
        }

        return lists
    }

}