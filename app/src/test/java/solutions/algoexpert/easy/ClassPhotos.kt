package solutions.algoexpert.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Class%20Photos
 */
class ClassPhotos {

    @Test
    fun test() {
        assertEquals(true, classPhotos(mutableListOf(5, 8, 1, 3, 4), mutableListOf(6, 9, 2, 4, 5)))
    }

    fun classPhotos(
        redShirtHeights: MutableList<Int>,
        blueShirtHeights: MutableList<Int>
    ): Boolean {

        val redSorted = redShirtHeights.sorted()
        val blueSorted = blueShirtHeights.sorted()

        val redSmall = redSorted[0] < blueSorted[0]

        var p = 0

        while (p <= redSorted.lastIndex) {

            val red = redSorted[p]
            val blue = blueSorted[p]

            if (redSmall && red >= blue) return false
            if (!redSmall && red <= blue) return false

            p++
        }

        return true
    }
}