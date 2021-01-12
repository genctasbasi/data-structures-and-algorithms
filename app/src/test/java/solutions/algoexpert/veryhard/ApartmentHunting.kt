package solutions.algoexpert.veryhard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Apartment%20Hunting
 */
class ApartmentHunting {

    @Test
    fun test() {

        val block1 = mutableMapOf<String, Boolean>()
        block1["gym"] = false
        block1["school"] = true
        block1["store"] = false

        val block2 = mutableMapOf<String, Boolean>()
        block2["gym"] = true
        block2["school"] = false
        block2["store"] = false

        val block3 = mutableMapOf<String, Boolean>()
        block3["gym"] = true
        block3["school"] = true
        block3["store"] = false

        val block4 = mutableMapOf<String, Boolean>()
        block4["gym"] = false
        block4["school"] = true
        block4["store"] = false

        val block5 = mutableMapOf<String, Boolean>()
        block5["gym"] = false
        block5["school"] = true
        block5["store"] = true

        val blocks = listOf(block1, block2, block3, block4, block5)

        val apartmentHunting =
            apartmentHunting(blocks, listOf("gym", "school", "store"))
        assertEquals(3, apartmentHunting)
    }

    // O(b * r)
    fun `apartmentHunting O(b * r)`(blocks: List<Map<String, Boolean>>, reqs: List<String>): Int {

        // not implemented this one it's basically about doing two loops for each block, one to left and one to right, to get the min distances.
        return -1
    }

    // O(b * b * r)
    fun apartmentHunting(blocks: List<Map<String, Boolean>>, reqs: List<String>): Int {

        val maxDistancesPerBlock = mutableMapOf<Int, Int>()

        blocks.forEachIndexed { index1, _ -> // index1 is potential stay

            val distancesForCurrentBlock =
                mutableMapOf<String, Pair<Int, Int>>() // Pair<Block index, distance>

            blocks.forEachIndexed { index2, map2 -> // index2 is where we're checking the requirements

                reqs.forEach {
                    if (map2[it] == true) {  // this requirement exist on this block
                        val distance = Math.abs(index1 - index2)

                        if (distancesForCurrentBlock[it]?.second ?: Int.MAX_VALUE > distance) {
                            distancesForCurrentBlock[it] = Pair(index2, distance)
                        }
                    }
                }
            }

            maxDistancesPerBlock[index1] =
                distancesForCurrentBlock.maxBy { it.value.second }?.value?.second ?: -1
        }

        return maxDistancesPerBlock.minBy { it.value }!!.key
    }

}