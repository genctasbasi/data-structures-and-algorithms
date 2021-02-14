package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/russian-doll-envelopes/
 */
class RussianDollEnvelopes {

    @Test
    fun test() {
        val result = maxEnvelopes(
            arrayOf(intArrayOf(5, 4), intArrayOf(6, 14), intArrayOf(6, 7), intArrayOf(2, 3))
        )
        assertEquals(3, result)
    }

    fun maxEnvelopes(envelopes: Array<IntArray>): Int {

        envelopes.sortWith(compareBy({ it[0] }, { it[1] }))

        val lis = Array(envelopes.size) { 1 }

        for (i in 1..envelopes.lastIndex) {
            for (j in 0 until i) {

                val iEnv = envelopes[i]
                val jEnv = envelopes[j]

                if (iEnv[0] > jEnv[0] && iEnv[1] > jEnv[1]) {
                    if (lis[j] >= lis[i]) {
                        lis[i] = 1 + lis[j]
                    }
                }
            }
        }

        return lis.max() ?: -1
    }
}