package solutions.algoexpert.veryhard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Waterfall%20Streams
 */
class WaterfallStreams {

    @Test
    fun test() {
        val result = waterfallStreams(
            listOf(
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
                listOf(1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
                listOf(0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0),
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
                listOf(1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0),
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0),
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
            ), 3
        )
        assertEquals(listOf(0.0, 0.0, 0.0, 25.0, 25.0, 0.0, 0.0), result)
    }

    @Test
    fun test5() {
        val result = waterfallStreams(
            listOf(
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
                listOf(1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0),
                listOf(0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0),
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
                listOf(1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0),
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0),
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
            ), 3
        )
        assertEquals(listOf(0.0, 0.0, 0.0, 0.0, 25.0, 0.0, 0.0), result)
    }

    fun waterfallStreams(array: List<List<Double>>, source: Int): List<Double> {

        val stack = java.util.Stack<Water>()
        val buckets = Array(array[0].size) { 0.0 }

        // first water:
        val firstWater = Water(0, source, 100.0, Direction.DOWN)
        stack.push(firstWater)

        while (stack.isNotEmpty()) {

            val currentWater = stack.pop()

            // see if we reach the last row:
            if (currentWater.row == array.lastIndex) {    // already at the last row, update the bucket
                buckets[currentWater.col] = buckets[currentWater.col] + currentWater.amount
                continue    // well this one finished
            }

            val nextSlots = getNextSlot(array, currentWater)
            nextSlots.forEach {
                stack.add(it)
            }
        }

        return buckets.toList()
    }

    /**
     * return row and column for the current water: Pair<row, column>
     * It's a list we're returning because water can go in 2 different directions
     */
    fun getNextSlot(array: List<List<Double>>, water: Water): List<Water> {

        // so water can go only in 3 directions: downward, left or right. if it goes downwards, it won't
        // go left or right so that's the first thing we should check

        if (array[water.row + 1][water.col] == 0.0) { // water is going down
            water.row += 1
            water.direction = Direction.DOWN
            return listOf(water)
        } else {  // so water cannot go downwards, let's check if it can go right or left (or, right AND left)

            val canGoLeft =
                water.col != 0 && array[water.row][water.col - 1] == 0.0 && water.direction != Direction.RIGHT
            val canGoRight =
                water.col != array[0].lastIndex && array[water.row][water.col + 1] == 0.0 && water.direction != Direction.LEFT

            return if (canGoLeft && canGoRight) { // then halve the water
                listOf(
                    Water(water.row, water.col - 1, water.amount / 2, Direction.LEFT),
                    Water(water.row, water.col + 1, water.amount / 2, Direction.RIGHT)
                )
            } else if (canGoLeft) {
                water.col -= 1
                water.amount =
                    if (water.direction == Direction.DOWN) water.amount / 2 else water.amount
                water.direction = Direction.LEFT
                listOf(water)
            } else if (canGoRight) {
                water.col += 1
                water.amount =
                    if (water.direction == Direction.DOWN) water.amount / 2 else water.amount
                water.direction = Direction.RIGHT
                listOf(water)
            } else {
                emptyList() // water is simply stuck
            }
        }
    }

    class Water(
        var row: Int,
        var col: Int,
        var amount: Double,
        var direction: Direction
    )

    enum class Direction { LEFT, RIGHT, DOWN }
}