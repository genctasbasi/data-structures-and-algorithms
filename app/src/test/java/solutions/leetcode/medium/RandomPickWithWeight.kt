package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/random-pick-with-weight/
 */
class RandomPickWithWeight {

    @Test
    fun test() {
        val solution = Solution(intArrayOf(1, 3, 4, 2))
        solution.pickIndex()
        // randomized result - cannot verify
    }

    @Test
    fun test2() {
        val solution = Solution(intArrayOf(1))
        assertEquals(0, solution.pickIndex())
    }

    @Test
    fun test3() {
        val solution = Solution(
            intArrayOf(
                54425,
                15121,
                9017,
                3577,
                21290,
                27914,
                12436,
                21847,
                62517,
                75919,
                50322,
                32336,
                19291,
                5999,
                15957,
                43968,
                12434,
                48966,
                97743,
                73202,
                67360,
                37007,
                36127,
                49239,
                74277,
                72805,
                58,
                57620,
                76895,
                29746,
                18579,
                32815,
                18706,
                51454,
                83733,
                29339,
                13943,
                20724,
                96835,
                21569,
                17863,
                48853,
                16526,
                91675,
                26817,
                29690,
                39964,
                50786,
                65073,
                36803,
                16183,
                6064,
                80281,
                43249,
                92085,
                59985,
                74333,
                18553,
                18616,
                21688,
                41904,
                38447,
                53148,
                17260,
                81301,
                32809,
                55336,
                35592,
                86659,
                52219,
                21474,
                73589,
                28817,
                31060,
                42627,
                58410,
                71833,
                75686,
                27448,
                13931,
                36310,
                97881,
                56437,
                76120,
                31885,
                18588,
                8089,
                36469,
                65507,
                51359,
                24008,
                44996,
                6572,
                51068,
                67381,
                69285,
                421,
                3578,
                42090,
                8022,
                79142,
                36988,
                26566,
                38728,
                49610,
                93507,
                19289,
                27227,
                53657,
                57285,
                36735,
                90430,
                13361,
                9751,
                47611,
                17828,
                8737,
                63746,
                48718,
                13469,
                11080,
                525,
                38372,
                42575,
                88605,
                13077,
                22668,
                32387,
                4856,
                15544,
                4079,
                18989,
                71667,
                40981,
                43907,
                2266,
                97529,
                54559,
                93937,
                84006,
                67213,
                95105,
                78339,
                2664,
                64449,
                24599,
                21139,
                74648,
                63385,
                56750,
                62474,
                70073,
                26896,
                72028,
                53750,
                52835,
                58140,
                29514,
                32762,
                95097,
                19389,
                376,
                99736,
                66678,
                45122,
                3910,
                9053,
                23034,
                97913,
                83558
            )
        )
        assertEquals(0, solution.pickIndex())
    }

    class Solution(w: IntArray) {

        private val sums = mutableListOf<Int>()

        init {
            var sumSoFar = 0
            w.forEach {
                sumSoFar += it
                sums.add(sumSoFar)
            }
        }

        fun pickIndex(): Int {
            val rnd = Math.random()
            val target = sums[sums.lastIndex] * rnd
            return getIndex(sums, target, 0, sums.lastIndex)
        }

        private fun getIndex(sums: MutableList<Int>, target: Double, start: Int, end: Int): Int {
            if (start >= end) return start
            val mid = (start + end).div(2)
            return if (sums[mid] > target) {
                getIndex(sums, target, start, mid)
            } else {
                getIndex(sums, target, mid + 1, end)
            }
        }
    }

    /**
     * This solution works with smaller numbers but probably doesn't scale well with big numbers
     * I don't think this solution would be acceptable in an interview. It would be a 'good start' though, IMHO
     */
    class SolutionOld(w: IntArray) {

        private val weights = mutableListOf<Int>()
        private val pool = mutableListOf<Int>()

        init {
            val sum = w.sum()

            if (sum != 0) {
                w.forEach {
                    weights.add((it.toDouble().div(sum) * 100000).toInt())
                }
            }

            weights.forEachIndexed { index, i ->
                (1..i).forEach {
                    pool.add(index)
                }
            }
        }

        fun pickIndex(): Int {
            return pool.random()
        }
    }
}