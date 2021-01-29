package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Sunset%20Views
 *
 */
class SunsetViews {

    @Test
    fun testCase1() {
        val result = sunsetViews(listOf(3, 5, 4, 4, 3, 1, 3, 2), "EAST")
        assertEquals(listOf(1, 3, 6, 7), result)
    }

    @Test
    fun testCase2() {
        val result = sunsetViews(listOf(3, 5, 4, 4, 3, 1, 3, 2), "WEST")
        assertEquals(listOf(0, 1), result)
    }

    fun sunsetViews(buildings: List<Int>, direction: String): List<Int> {

        val views = mutableListOf<Int>()
        var currentMax = Int.MIN_VALUE

        when (direction) {

            "EAST" -> {
                (buildings.lastIndex downTo 0).forEachIndexed { _, index ->
                    if (buildings[index] > currentMax) {
                        views.add(index)
                        currentMax = buildings[index]
                    }
                }
            }

            "WEST" -> {
                (0..buildings.lastIndex).forEachIndexed { _, index ->
                    if (buildings[index] > currentMax) {
                        views.add(index)
                        currentMax = buildings[index]
                    }
                }
            }
        }

        return views.sorted()
    }

}