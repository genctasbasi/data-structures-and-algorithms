package solutions.algoexpert.easy

import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Tandem%20Bicycle
 */
class TandemBicycle {

    @Test
    fun test() {
        val redShirtSpeeds = mutableListOf(5, 5, 3, 9, 2)
        val blueShirtSpeeds = mutableListOf(3, 6, 7, 2, 1)
        val fastest = true
        val expected = 32
        val output = tandemBicycle(redShirtSpeeds, blueShirtSpeeds, fastest)
        assert(expected == output)
    }

    @Test
    fun test1() {
        val redShirtSpeeds = mutableListOf(1, 2, 1, 9, 12, 3)
        val blueShirtSpeeds = mutableListOf(3, 3, 4, 6, 1, 2)
        val fastest = true
        val expected = 37
        val output = tandemBicycle(redShirtSpeeds, blueShirtSpeeds, fastest)
        assert(expected == output)
    }

    // 1, 1, 2, 3, 9, 12
    // 1, 2, 3, 3, 4, 6
    // 12-1, 9-1, 6-1 4, 3

    fun tandemBicycle(
        redShirtSpeeds: MutableList<Int>,
        blueShirtSpeeds: MutableList<Int>,
        fastest: Boolean
    ): Int {

        val redSorted = redShirtSpeeds.sorted()
        val blueSorted = blueShirtSpeeds.sorted()
        val size = redShirtSpeeds.size

        var count = 0
        var pRed = redSorted.lastIndex
        var pBlue = blueSorted.lastIndex
        var added = 0
        if (fastest) {
            while (added < size) {

                if (redSorted[pRed] > blueSorted[pBlue]) {
                    count += redSorted[pRed]
                    pRed--
                } else {
                    count += blueSorted[pBlue]
                    pBlue--
                }

                added++
            }

            return count
        }

        var p = redSorted.lastIndex
        while (p >= 0) {
            count += Math.max(redSorted[p], blueSorted[p])
            p--
        }
        return count
    }
}