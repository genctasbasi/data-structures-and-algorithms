package solutions.hackerRank.dictionariesAndHashmaps

import org.junit.Assert
import org.junit.Test

/**
 * https://www.hackerrank.com/challenges/count-triplets-1/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
 * TODO: WIP That fails, need a fix
 */
class CountTriplets {

    @Test
    fun `test cases`() {
        // Assert.assertEquals(6, countTriplets2(listOf<Long>(1, 3, 9, 9, 27, 81).toTypedArray(), 3))
        // Assert.assertEquals(4, countTriplets2(listOf<Long>(1,5,5,25,125).toTypedArray(), 5))
        // Assert.assertEquals(2, countTriplets2(listOf<Long>(1,2,2,4).toTypedArray(), 2))
        Assert.assertEquals(161700, countTriplets2(listOf<Long>(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1).toTypedArray(), 1))
        // 1, 1, 1, 3, 3, 9, 9
        // 1, 1, 2, 3
        // 1, 2, 3, 3, 9
        // 1, 3,
        // {0, 2, 4}, {0, 2, 5}, {0, 3, 4}, {0, 3, 5}
    }

    private fun countTriplets2(arr: Array<Long>, r: Long): Long {
        var localCount = 0L
        var totalCount = 0L
        val map = mutableMapOf<Long, Long>()

        arr.forEach {
            map[it] = map[it]?.plus(1) ?: 1
        }

        var tripletIndex = 0
        var previousKey = -1L

        loop1@ for (i in 0 until map.size) {

            val currentKey = map.keys.elementAt(i)
            val values = map.values.elementAt(i)
            tripletIndex = 0
            localCount = values
            previousKey = currentKey

            loop2@ for (j in i + 1 until map.size) {

                val lookingKey = map.keys.elementAt(j)
                val lookingValues = map.values.elementAt(j)

                tripletIndex++

                if (lookingKey == previousKey * r) {
                    localCount *= lookingValues // 40
                    previousKey = lookingKey    // 9
                }

                if (tripletIndex == 2) {
                    totalCount += localCount    // 40
                    continue@loop1
                }

            }
        }

        return totalCount
    }

    private fun countTriplets(arr: Array<Long>, r: Long): Long {

        var currentNumber = -1L
        var multiplier = 1L
        var tripletIndex = 0    // 0, 1 or 2
        var tripletMultipliers = mutableListOf(1L, 1L, 1L)
        var count = 0L

        loop@ for (i in arr.indices) {

            currentNumber = arr[i]

            for (j in i + 1 until arr.size) {

                when {
                    arr[j] == currentNumber -> multiplier++      // i.e: 1, 1
                    arr[j] == currentNumber * r -> {    // i.e: 1, 3
                        tripletMultipliers[tripletIndex] = multiplier

                        if (tripletIndex == 2) {    // already finished
                            count += tripletMultipliers[0] * tripletMultipliers[1] * tripletMultipliers[2]
                            tripletMultipliers = mutableListOf(1L, 1L, 1L)
                            tripletIndex = 0
                            multiplier = 1
                            continue@loop
                        }

                        tripletIndex++
                        multiplier = 1
                    }

                    else -> {
                    }  // i.e. 1, 2

                }

                if (tripletIndex == 3 || j == arr.size - 1) {    // already finished
                    count += tripletMultipliers[0] * tripletMultipliers[1] * tripletMultipliers[2]
                    tripletMultipliers = mutableListOf(1L, 1L, 1L)
                    tripletIndex = 0
                    multiplier = 1
                    continue@loop
                }



                currentNumber = arr[j]
            }

        }

        return count
    }
}