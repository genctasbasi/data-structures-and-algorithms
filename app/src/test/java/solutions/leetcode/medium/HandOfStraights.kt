package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/hand-of-straights/
 */
class HandOfStraights {

    @Test
    fun test() {
        assertEquals(true, isNStraightHand(intArrayOf(1, 2, 3, 6, 2, 3, 4, 7, 8), 3))
    }

    @Test
    fun test1() {
        assertEquals(false, isNStraightHand(intArrayOf(1, 2, 3, 4, 5), 4))
    }

    @Test
    fun test2() {
        assertEquals(false, isNStraightHand(intArrayOf(8, 10, 12), 3))
    }

    @Test
    fun test3() {
        assertEquals(true, isNStraightHand(intArrayOf(1, 1, 2, 2, 3, 3), 3))
    }

    fun isNStraightHand(hand: IntArray, W: Int): Boolean {

        if (hand.isEmpty()) return false
        if (hand.size.rem(W) != 0) return false

        val sorted: MutableList<Int> = hand.sorted().toMutableList()

        var p = 0
        var pickedCount = 0
        var lastPicked: Int? = null

        while (p <= sorted.lastIndex) {

            val current = sorted[p]

            if (lastPicked == null) {
                lastPicked = current
                sorted.removeAt(p)
                pickedCount++
                p = 0
            } else {

                if (current == lastPicked + 1) {
                    sorted.removeAt(p)
                    pickedCount++
                    lastPicked = current
                    p = 0
                } else {
                    p++
                }
            }

            if (pickedCount == W) {
                pickedCount = 0
                lastPicked = null
                p = 0
            }
        }

        return sorted.isEmpty()
    }
}