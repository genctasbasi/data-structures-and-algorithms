package solutions.algoexpert.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Number%20Of%20Ways%20To%20Traverse%20Graph
 */

class NumberOfWaysToTraverseGraph {

    @Test
    fun test() {
        assertEquals(10, numberOfWaysToTraverseGraph(4, 3))
    }

    fun numberOfWaysToTraverseGraph(width: Int, height: Int): Int {
        return explore(width, height, 0, 0)
    }

    fun explore(width: Int, height: Int, i: Int, j: Int): Int {

        if (i >= width) return 0
        if (j >= height) return 0

        if (i == width - 1 && j == height - 1) return 1

        val option1 = explore(width, height, i + 1, j)
        val option2 = explore(width, height, i, j + 1)

        return option1 + option2
    }
}