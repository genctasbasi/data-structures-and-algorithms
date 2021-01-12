package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Water%20Area
 */
class WaterArea {

    @Test
    fun test() {
        val waterArea = waterArea(mutableListOf(0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3))
        assertEquals(48, waterArea)
    }

    @Test
    fun test2() {
        val waterArea = waterArea(
            mutableListOf(
                0, 0, 4, 0, 0, 0, 8, 0, 0, 6, 0, 0, 5,
                0, 3, 0, 0, 10, 0, 0, 0, 1, 1, 0, 0, 0, 0, 3
            )
        )
        assertEquals(48, waterArea)
    }

    @Test
    fun testMine() {
        val waterArea = waterArea(mutableListOf(0, 8, 0, 0, 5, 0, 0, 3, 0, 4, 0, 0, 7, 0))
        assertEquals(58, waterArea)
    }

    fun waterArea(heights: List<Int>): Int {

        var leftPillar = -1
        val pillars = mutableListOf<Pillar>()
        for (i in heights.indices) {
            val item = heights[i]

            if (item == 0 && leftPillar == -1) continue // water without pillar

            if (item > 0 && leftPillar == -1) {
                leftPillar = item
                continue
            }

            when (item) {

                0 -> {
                    val pillar = Pillar(leftPillar, null, item)
                    pillars.add(pillar)
                }
                else -> {

                    // update previous pillars - TODO this can be improved
                    pillars.forEach {
                        if (it.rightPillar ?: -1 < item) {
                            it.rightPillar = item
                        }
                    }

                    val pillar = Pillar(leftPillar, null, item)
                    pillars.add(pillar)

                    if (item > leftPillar)
                        leftPillar = item
                }
            }
        }

        // now calculate the water
        var totalWater = 0
        pillars.forEach {
            if (it.rightPillar != null) {
                val minPillar = Math.min(it.leftPillar, it.rightPillar!!)
                val waterHeight = minPillar - it.height
                totalWater += Math.max  (waterHeight, 0)
            }
        }

        return totalWater
    }

    data class Pillar(val leftPillar: Int, var rightPillar: Int? = null, val height: Int)

//    data class Float(
//        val leftPillar: Int,
//        val leftPillarIndex: Int,
//        var rightPillar: Int,
//        var rightPillarIndex: Int,
//        val waterSurface: Int
//    )
//
//    private val floatingList = mutableListOf<Float>()
//    private var waterVolume = 0
//
//    fun waterArea(heights: List<Int>): Int {
//
//        // var highestLeftPillar = -1
//        var leftPillar = -1
//        var leftPillarIndex = 0
//
//        for (i in heights.indices) {
//
//            val item = heights[i]
//
//            if (item == 0 && leftPillar == -1) continue // water without pillar
//
//            when (item) {
//
//                0 -> {   // water
//                }
//                else -> {  // pillar
//
//                    if (leftPillar == -1) {
//                        leftPillar = item
//                        // highestLeftPillar = item
//                        leftPillarIndex = i
//                    } else {    // this is right pillar now
//
//                        val volumes = getWaterVolume(leftPillar, item, i - leftPillarIndex - 1)
//                        waterVolume += volumes.first
//                        volumes.second?.let { floatingList.add(it) }
//
//                        leftPillar = item   // this is left pillar now
//                        leftPillarIndex = i
//                    }
//                }
//            }
//        }
//
//        return waterVolume
//    }
//
//    private fun getWaterVolume(
//        leftPillar: Int,
//        rightPillar: Int,
//        gapBetween: Int
//    ): Pair<Int, Float?> { // volume, floating
//
//        val volume = Math.min(leftPillar, rightPillar) * gapBetween
//
//        return if (rightPillar > leftPillar) {
//
//            // update floats
//            floatingList.forEach {
//                if (rightPillar > it.rightPillar && rightPillar > it.leftPillar) {
//                    waterVolume += ((leftPillar - rightPillar) * gapBetween)
//                } else if (rightPillar > it.rightPillar) {
//                    waterVolume += ((rightPillar - it.rightPillar) * gapBetween)
//                    it.rightPillar = rightPillar
//                }
//            }
//
//            Pair(volume, null)
//
//        } else {
//            val floating = Float(leftPillar, rightPillar, gapBetween, false)
//            Pair(volume, floating)
//        }
//    }

}