package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/stone-game/
 */
class StoneGame {

    @Test
    fun test() {
        assertEquals(true, stoneGame(intArrayOf(2, 3, 4, 99, 1)))
    }

    fun stoneGame(piles: IntArray): Boolean {

        if (piles.isEmpty()) return false
        if (piles.size < 3) return true

        val queue: Deque<Int> = LinkedList()
        piles.forEach { queue.add(it) }
        return calc(queue, true)
    }

    var alexSum = 0
    var leeSum = 0

    fun calc(queue: Deque<Int>, alexTurn: Boolean): Boolean {

        if (queue.isEmpty()) return alexSum > leeSum

        if (!alexTurn) {
            if (queue.peekFirst() >= queue.peekLast()) {
                val leeTake = queue.pollFirst()
                leeSum += leeTake

                val alexWin = calc(queue, true)
                if (!alexWin) {
                    leeSum -= leeTake
                    queue.addFirst(leeTake)
                }

                return alexWin
            } else {
                val leeTake = queue.pollLast()
                leeSum += leeTake

                val alexWin = calc(queue, true)
                if (!alexWin) {
                    leeSum -= leeTake
                    queue.addLast(leeTake)
                }

                return alexWin
            }

        } else {

            // option1
            val alexTake = queue.pollFirst()
            alexSum += alexTake
            val alexWin = calc(queue, false)

            if (alexWin) return true

            alexSum -= alexTake
            queue.addFirst(alexTake)

            // option 2
            val alexTakeLast = queue.pollLast()
            alexSum += alexTakeLast
            return calc(queue, false)
        }
    }

}