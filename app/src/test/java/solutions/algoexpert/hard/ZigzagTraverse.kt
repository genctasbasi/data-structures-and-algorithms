package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Zigzag%20Traverse
 */
class ZigzagTraverse {

    @Test
    fun test() {

        val list = listOf(
            listOf(1, 3, 4, 10),
            listOf(2, 5, 9, 11),
            listOf(6, 8, 12, 15),
            listOf(7, 13, 14, 16)
        )

        val path = zigzagTraverse(list)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16), path)
    }

    @Test
    fun test2() {

        val list = listOf(listOf(1, 2, 3, 4, 5))

        val path = zigzagTraverse(list)
        assertEquals(listOf(1, 2, 3, 4, 5), path)
    }

    @Test
    fun test3() {

        val list = listOf(listOf(1), listOf(2), listOf(3), listOf(4), listOf(5))

        val path = zigzagTraverse(list)
        assertEquals(listOf(1, 2, 3, 4, 5), path)
    }

    @Test
    fun test4() {
        val list = listOf(listOf(1, 3), listOf(2, 4), listOf(5, 7), listOf(6, 8), listOf(9, 10))

        val path = zigzagTraverse(list)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), path)
    }

    @Test
    fun test5() {
        val list = listOf(
            listOf(1, 3, 4, 10, 11),
            listOf(2, 5, 9, 12, 19),
            listOf(6, 8, 13, 18, 20),
            listOf(7, 14, 17, 21, 24),
            listOf(15, 16, 22, 23, 25)
        )

        val path = zigzagTraverse(list)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), path)
    }

    enum class Directions {
        South,
        NorthEast,
        East,
        SouthWest,
        None
    }

    fun zigzagTraverse(array: List<List<Int>>): List<Int> {

        var direction = Directions.None
        var prevDirection = Directions.None
        val path = mutableListOf<Int>()
        var pathValue = 0
        var hasEnded = false
        var indexes = Pair(0, 0)

        while (!hasEnded) {

            indexes = getIndexesByDirection(indexes.first, indexes.second, direction)

            val indexDown = indexes.first
            val indexRight = indexes.second

            pathValue = array[indexDown][indexRight]

            path.add(pathValue)

            // get the new direction
            direction = when (direction) {

                Directions.None -> {
                    if (array.lastIndex == 0) Directions.East
                    else Directions.South
                }

                Directions.South -> {

                    if (array.first().lastIndex == 0) { // single column
                        Directions.South
                    } else if (indexRight == array.first().lastIndex) {
                        Directions.SouthWest
                    } else {
                        Directions.NorthEast
                    }

                }

                Directions.NorthEast -> {

                    if (indexRight == array.first().lastIndex) {
                        Directions.South
                    } else {
                        // let's see if it's time to change direction or not:
                        if (indexDown == 0 && indexRight == array.first().lastIndex) { // meaning we're at the top right corner
                            Directions.South
                        } else if (indexDown == 0) {    // at the top
                            Directions.East
                        } else if (indexRight == array.first().lastIndex) { // at the right edge
                            Directions.South
                        } else {  // then direction doesn't change
                            Directions.NorthEast
                        }
                    }
                }

                Directions.East -> {
                    when {
                        array.first().lastIndex == 0 -> Directions.South
                        array.lastIndex == 0 -> Directions.East
                        indexDown == 0 -> {
                            Directions.SouthWest
                        }
                        else -> {
                            Directions.NorthEast
                        }
                    }
                }

                Directions.SouthWest -> {
                    // let's see if it's time to change direction or not:
                    if (array.first().lastIndex == 1 && indexRight == 0 && indexDown == array.lastIndex) {
                        Directions.East
                    } else if (indexRight == 0 && indexDown == array.lastIndex) { // left bottom corner
                        Directions.East
                    } else if (indexRight == 0) {
                        Directions.South
                    } else if (indexDown == array.lastIndex) {  // at bottom row
                        Directions.East
                    } else {  // direction doesn't change
                        Directions.SouthWest
                    }
                }
            }

            hasEnded = indexRight == array.first().lastIndex
                    && indexDown == array.lastIndex
        }

        return path
    }

    private fun getIndexesByDirection(
        indexDown: Int,
        indexRight: Int,
        direction: Directions
    ): Pair<Int, Int> {

        return when (direction) {
            Directions.South -> {
                Pair(indexDown + 1, indexRight)
            }
            Directions.NorthEast -> {
                Pair(indexDown - 1, indexRight + 1)
            }
            Directions.East -> {
                Pair(indexDown, indexRight + 1)
            }
            Directions.SouthWest -> {
                Pair(indexDown + 1, indexRight - 1)
            }
            Directions.None -> {
                Pair(indexDown, indexRight)
            }
        }
    }

}