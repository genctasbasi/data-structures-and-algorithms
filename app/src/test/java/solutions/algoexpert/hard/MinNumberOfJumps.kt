package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Min%20Number%20Of%20Jumps
 */
class MinNumberOfJumps {

    @Test
    fun test() {
        val minJump = minNumberOfJumps(mutableListOf(3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3))
        assertEquals(4, minJump)
    }

    @Test
    fun test14() {
        val minJump =
            minNumberOfJumps(mutableListOf(3, 12, 2, 1, 2, 3, 15, 1, 1, 1, 3, 2, 3, 2, 1, 1, 1, 1))
        assertEquals(3, minJump)
    }

    fun minNumberOfJumps(array: List<Int>): Int {

        if (array.size < 2) return 0

        val jumps = Array(array.size) { Int.MAX_VALUE }
        jumps[jumps.size - 1] = 0
        jumps[jumps.size - 2] = 1

        for (i in array.size - 3 downTo 0) {

            if (i + array[i] >= array.lastIndex) {
                jumps[i] = 1
            } else {
                val indexJump = array[i]

                val option1 = jumps[i + 1] + 1
                val option2 = (jumps.toList().subList(i, i + indexJump + 1).min())?.plus(1) ?: option1

                jumps[i] = Math.min(option1, option2)
            }
        }

        return jumps.first()
    }

}