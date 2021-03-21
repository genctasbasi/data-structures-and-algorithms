package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/interval-list-intersections/
 * https://www.youtube.com/watch?v=Qh8ZjL1RpLI&ab_channel=TECHDOSE
 */
class IntervalListIntersections {

    @Test
    fun test() {

        val result = intervalIntersection(
            arrayOf(
                intArrayOf(0, 2),
                intArrayOf(5, 10),
                intArrayOf(13, 23),
                intArrayOf(24, 25)
            ), arrayOf(
                intArrayOf(1, 5),
                intArrayOf(8, 12),
                intArrayOf(15, 24),
                intArrayOf(25, 26)
            )
        )

        val expected = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(5, 5),
            intArrayOf(8, 10),
            intArrayOf(15, 23),
            intArrayOf(24, 24),
            intArrayOf(25, 25)
        )

        assertArrayEquals(expected, result)
    }

    fun intervalIntersection(
        firstList: Array<IntArray>,
        secondList: Array<IntArray>
    ): Array<IntArray> {

        val output = mutableListOf<IntArray>()

        var p1 = 0
        var p2 = 0

        while (p1 <= firstList.lastIndex && p2 <= secondList.lastIndex) {
            val s1 = firstList[p1][0]
            val e1 = firstList[p1][1]

            val s2 = secondList[p2][0]
            val e2 = secondList[p2][1]

            if (doesIntersect(s1, e1, s2, e2)) {
                val intersectFrom = Math.max(s1, s2)
                val intersectTo = Math.min(e1, e2)
                output.add(intArrayOf(intersectFrom, intersectTo))
            }

            if (e1 > e2) p2++ else p1++
        }

        return output.toTypedArray()
    }

    fun doesIntersect(s1: Int, e1: Int, s2: Int, e2: Int) =
        e1 >= s2 && e2 >= s1

    /**
     * this didn't work
     */
    fun intervalIntersectionWithTreeMap(
        firstList: Array<IntArray>,
        secondList: Array<IntArray>
    ): Array<IntArray> {

        if (firstList.isEmpty()) return secondList
        if (secondList.isEmpty()) return firstList

        val output = mutableListOf<IntArray>()
        val treeMap = TreeMap<Int, Int>()
        firstList.forEach {
            val from = it[0]
            val to = it[1]
            treeMap[from] = to
        }

        secondList.forEach {

            val from = it[0]
            val to = it[1]

            val floor = treeMap.floorKey(from)
            val ceiling = treeMap.ceilingKey(from)

            if (floor == null && ceiling == null) {
                return firstList
            }

            if (floor == null) {  // case 1, 2 or 3

                if (to < ceiling) {   // case 1, don't add anything

                } else if (to == ceiling) {
                    output.add(intArrayOf(to, to))
                } else if (to < treeMap[ceiling]!!) {   // case 2
                    output.add(intArrayOf(ceiling, to))

                } else if (to >= treeMap[ceiling]!!) {  // case 3
                    output.add(intArrayOf(ceiling, treeMap[ceiling]!!))
                }
            } else {  // there is a floor

                if (to <= treeMap[floor]!!) { // case 4
                    output.add(intArrayOf(from, to))
                } else if (to >= treeMap[floor]!!) { // case 5
                    output.add(intArrayOf(from, treeMap[floor]!!))
                } else if (from > treeMap[floor]!!) { // case 6
                    // don't add anything
                }
            }
        }

        return output.toTypedArray()
    }
}