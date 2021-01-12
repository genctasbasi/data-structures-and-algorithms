package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/River%20Sizes
 */
class RiverSizes {

    @Test
    fun testCase1() {
        val list = listOf(
            listOf(1, 0, 0, 1, 0),
            listOf(1, 0, 1, 1, 0),
            listOf(0, 0, 1, 0, 0),
            listOf(1, 0, 1, 0, 1),
            listOf(1, 0, 1, 1, 0)
        )
        val sizes = riverSizes(list)
        assertEquals(listOf(2, 7, 1, 2), sizes)
    }

    @Test
    fun testCase2() {
        val list = listOf(
            listOf(1, 0, 0, 1, 0),
            listOf(1, 0, 1, 0, 0),
            listOf(0, 0, 1, 0, 1),
            listOf(1, 0, 1, 0, 1),
            listOf(1, 0, 1, 1, 0)
        )

        val sizes = riverSizes(list)
        assertEquals(listOf(2, 1, 5, 2, 2), sizes)
    }

    @Test
    fun testCase3() {
        val list = listOf(
            listOf(1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0),
            listOf(1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0),
            listOf(0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1),
            listOf(1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
            listOf(1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1)
        )

        val sizes = riverSizes(list)
        assertEquals(listOf(2, 1, 21, 5, 2, 1), sizes)
    }

    fun riverSizes(matrix: List<List<Int>>): List<Int> {

        val visitedList = mutableListOf<String>()
        val riverSizes = mutableListOf<Int>()

        matrix.forEachIndexed { index1, list ->
            list.forEachIndexed { index2, item ->

                val riverSize = visitItem(matrix, Pair(index1, index2), visitedList)
                if (riverSize > 0)
                    riverSizes.add(riverSize)
            }
        }

        return riverSizes
    }

    private fun visitItem(
        matrix: List<List<Int>>,
        item: Pair<Int, Int>,
        visitedList: MutableList<String>,
        riverSize: Int = 0
    ): Int {

        if (isVisited(visitedList, item.first, item.second)) {
            return riverSize
        }

        visitedList.add("${item.first}-${item.second}")

        var newRiverSize = riverSize
        if (matrix[item.first][item.second] == 1) {

            val unvisitedRiverNeighbours =
                getUnvisitedRiverNeighbours(matrix, visitedList, item)

            unvisitedRiverNeighbours.forEach {
                newRiverSize = visitItem(matrix, it, visitedList, newRiverSize)
            }

            newRiverSize++
        }

        return newRiverSize
    }

    private fun getUnvisitedRiverNeighbours(
        matrix: List<List<Int>>,
        visitedList: List<String>,
        item: Pair<Int, Int>
    ): List<Pair<Int, Int>> {

        val neighbours = mutableListOf<Pair<Int, Int>>()

        // up
        if (item.first != 0 && matrix[item.first - 1][item.second] == 1
            && !isVisited(visitedList, item.first - 1, item.second)
        ) {
            neighbours.add(Pair(item.first - 1, item.second))
        }

        // down
        if (item.first != matrix.size - 1 && matrix[item.first + 1][item.second] == 1
            && !isVisited(visitedList, item.first + 1, item.second)
        ) {
            neighbours.add(Pair(item.first + 1, item.second))
        }

        // right
        if (item.second != matrix[0].size - 1 && matrix[item.first][item.second + 1] == 1
            && !isVisited(visitedList, item.first, item.second + 1)
        ) {
            neighbours.add(Pair(item.first, item.second + 1))
        }

        // left
        if (item.second != 0 && matrix[item.first][item.second - 1] == 1
            && !isVisited(visitedList, item.first, item.second - 1)
        ) {
            neighbours.add(Pair(item.first, item.second - 1))
        }

        return neighbours
    }

    private fun isVisited(visitedList: List<String>, index1: Int, index2: Int) =
        visitedList.contains("$index1-$index2")
}