package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Powerset
 */
class Powerset {

    @Test
    fun test() {

        val permutations = powerset(listOf(1, 2, 3))
        val expected = listOf(
            listOf(),
            listOf(1),
            listOf(2),
            listOf(3),
            listOf(1, 2),
            listOf(1, 3),
            listOf(2, 3),
            listOf(1, 2, 3)
        )

        Assert.assertEquals(expected, permutations)
    }

    fun powerset(array: List<Int>): List<List<Int>> {

        if (array.isEmpty()) return listOf()

        if (array.size == 1) return listOf(array)

        val returnList = mutableSetOf<List<Int>>()

        array.forEachIndexed { index, item ->

            val subLists = powerset(array.subList(index + 1, array.size))

            subLists.forEach {
                val newList = it.toMutableList()
                newList.add(item)

                returnList.add(newList)
            }

            returnList.add(listOf(item))
        }

        returnList.add(listOf())
        return returnList.toList()
    }

}