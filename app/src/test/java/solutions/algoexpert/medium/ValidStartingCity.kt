package solutions.algoexpert.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import kotlin.math.roundToInt

/**
 * https://www.algoexpert.io/questions/Valid%20Starting%20City
 */
class ValidStartingCity {

    @Test
    fun test() {
        assertEquals(4, validStartingCity(listOf(5, 25, 15, 10, 15), listOf(1, 2, 1, 0, 3), 10))
    }

    @Test
    fun test1() {
        assertEquals(2, validStartingCity(listOf(5, 2, 3), listOf(1, 0, 1), 5))
    }

    fun validStartingCity(distances: List<Int>, fuel: List<Int>, mpg: Int): Int {

        distances.forEachIndexed { index, distance ->
            val canReach = canReach(distances, fuel, mpg.toDouble(), index)
            if (canReach) return index
        }

        throw IllegalStateException("Shouldn't come here by the definition of the problem")
    }

    private fun canReach(
        distances: List<Int>,
        fuel: List<Int>,
        mpg: Double,
        startIndex: Int
    ): Boolean {

        var currentIndex = startIndex
        var fuelNow = 0.0
        do {

            // get the fuel
            fuelNow += fuel[currentIndex]

            if ((fuelNow * mpg).roundToInt() < distances[currentIndex]) {    // cannot reach
                return false
            }

            fuelNow -= (distances[currentIndex] / mpg)
            currentIndex = (currentIndex + 1).rem(distances.size)
        } while (currentIndex != startIndex)

        return true
    }

}