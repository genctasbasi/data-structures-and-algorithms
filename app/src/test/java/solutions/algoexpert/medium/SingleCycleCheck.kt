package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Single%20Cycle%20Check
 */
class SingleCycleCheck {

    @Test
    fun testCase1() {
        Assert.assertEquals(true, hasSingleCycle(listOf(2, 3, 1, -4, -4, 2)))
    }

    @Test
    fun testCase2() {
        Assert.assertEquals(true, hasSingleCycle(listOf(2, 2, -1)))
    }

    @Test
    fun testCase3() {
        Assert.assertEquals(true, hasSingleCycle(listOf(1, 2, 3, 4, -2, 3, 7, 8, -26)))
    }

    @Test
    fun testCase4() {
        Assert.assertEquals(false, hasSingleCycle(listOf(1, -1, 1, -1)))
    }

    fun hasSingleCycle(array: List<Int>): Boolean {

        val map = mutableMapOf<Int, Boolean>()
        map[0] = true
        var loopIndex = 0
        do {

            loopIndex += array[loopIndex]

            if (loopIndex < 0) {
                loopIndex = array.size + loopIndex.rem(array.size)
                loopIndex = loopIndex.rem(array.size)
            } else if (loopIndex >= array.size) {
                loopIndex = loopIndex.rem(array.size)
            }

            if (loopIndex == 0) break

            if (map[loopIndex] == true) return false
            map[loopIndex] = true

        } while (loopIndex != 0)

        return map.size == array.size && map.any { it.value }
    }

}