package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/car-fleet/
 */
class CarFleet {

    @Test
    fun test() {
        val result = carFleet(12, intArrayOf(10, 8, 0, 5, 3), intArrayOf(2, 4, 1, 1, 3))
        assertEquals(3, result)
    }

    @Test
    fun test2() {
        val result = carFleet(16, intArrayOf(11, 14, 13, 6), intArrayOf(2, 2, 6, 7))
        assertEquals(2, result)
    }

    @Test
    fun test3() {
        val result = carFleet(
            31,
            intArrayOf(5, 26, 18, 25, 29, 21, 22, 12, 19, 6),
            intArrayOf(7, 6, 6, 4, 3, 4, 9, 7, 6, 4)
        )
        assertEquals(6, result)
    }

    data class Fleet(
        var position: Int,
        val speed: Int,
        var hasJoined: Boolean,
        var isRemoving: Boolean
    )

    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        if (position.isEmpty() || speed.isEmpty() || position.size != speed.size) return 0

        var fleetCount = 0
        val list = TreeSet<Fleet>(compareBy { it.position })

        var fleetInFront: Fleet? = null

        position.forEachIndexed { index, it ->
            list.add(Fleet(it, speed[index], false, false))
        }

        while (list.isNotEmpty()) {

            list.reversed().forEach { fleet ->

                if (fleetInFront == null) {   // no fleet in front
                    fleet.position = fleet.position + fleet.speed
                    if (fleet.position >= target) fleet.isRemoving = true

                } else {    // fleet in front
                    if (fleet.position + fleet.speed >= fleetInFront!!.position) {  // 'collide'

                        fleet.hasJoined = fleet.position + fleet.speed <= target

                        fleet.position = fleetInFront!!.position
                        fleet.isRemoving = true
                    } else {
                        fleet.position = fleet.position + fleet.speed
                        if (fleet.position >= target) fleet.isRemoving = true
                    }
                }

                fleetInFront = fleet
            }

            fleetCount += list.count { it.position >= target && !it.hasJoined }
            list.removeIf { it.isRemoving }
            fleetInFront = null
        }

        return fleetCount
    }
}