package solutions.hackerRank.trees

import junit.framework.Assert.assertEquals
import org.junit.Test
import kotlin.math.absoluteValue

/**
 * Tree Challenges
 * https://www.hackerrank.com/interview/interview-preparation-kit/trees/challenges
 * TODO: WIP
 *
 */
class BalancedForest : TreeTestBase() {

    @Test
    fun balanced_forest() {

        var valueToAdd: Int

        // Arrange
        // defined as Array since the original question on HackerRank requires
        // val values = arrayOf(1, 2, 2, 1, 1)
        val values = arrayOf(1, 1, 1, 18, 10, 11, 5, 6)
//        val edgesAll = arrayOf(
//            arrayOf(1, 2),
//            arrayOf(1, 3),
//            arrayOf(1, 4),
//            arrayOf(3, 5)
//        )

        val edgesAll = arrayOf(
            arrayOf(1, 2),
            arrayOf(1, 4),
            arrayOf(2, 3),
            arrayOf(1, 8),
            arrayOf(8, 7),
            arrayOf(7, 6),
            arrayOf(5, 7)
        )

        val cuttingCombinations =
            getCuttingCombinations(edgesAll.map { it.toList() }.toMutableList())
        var minimumValueNeeded = Int.MAX_VALUE

        cuttingCombinations.forEach { edgesToCut ->

            val afterCut = edgesAll.map { it.toList() }.toList().minus(edgesToCut)

            val routeTotals = getRootTotals(values.toList(), afterCut)

            if (routeTotals.size == 3) {  // as we need 3 forests

                val routeGroups = routeTotals.groupBy { it }

                /**
                 * we want 2 groups ie 1 group with 2 elements (same total)
                 * and 1 group with different total so we can add some to it.
                 *
                 * need to be careful not to accept route totals like 1, 1, 5
                 * we need 5, 5, 1 so the smallest group size should be 1
                 */
                if (routeGroups.size == 2 && routeGroups.minBy { it.key }?.value?.size == 1) {
                    val val1 = routeGroups.keys.map { it }[0]
                    val val2 = routeGroups.keys.map { it }[1]

                    valueToAdd = (val1 - val2).absoluteValue

                    if (valueToAdd < minimumValueNeeded) minimumValueNeeded = valueToAdd
                }
            }
        }

        // return if(minimumValueNeeded == Int.MAX_VALUE) -1 else minimumValueNeeded
        assertEquals(minimumValueNeeded, 2)
    }

    private fun getCuttingCombinations(values: MutableList<List<Int>>): List<List<List<Int>>> {

        val ret = mutableListOf<List<List<Int>>>()

        (0 until values.size).forEachIndexed { index1, _ ->

            var index2 = index1 + 1 // start from the next item
            while (index2 < values.size) {
                ret.add(listOf(values[index1], values[index2]))
                index2++
            }
        }

        return ret
    }

    /**
     * Returns list root total value. If there are two roots, say root A and root B, method
     * will return list of two elements, each is the sum of values belonging to each root
     */
    private fun getRootTotals(values: List<Int>, edges: List<List<Int>>): List<Int> {

        val rootTotals = mutableListOf<Int>()

        values.forEachIndexed { index, _ ->
            if (isRoot(index + 1, edges)) {
                rootTotals.add(getForestTotal(index + 1, values, edges))
            }
        }

        return rootTotals.toList()
    }

    /**
     * Route node (index) means there are no nodes (indexes) pointing to that index
     */
    private fun isRoot(index: Int, edges: List<List<Int>>): Boolean {
        return !edges.any { it[1] == index }
    }

    /**
     * recursively gets the total value of given forest (index)
     */
    private fun getForestTotal(index: Int, values: List<Int>, edges: List<List<Int>>): Int {

        var total = 0
        val indexValue = values[index - 1]

        if (!edges.any { it[0] == index }) return indexValue

        edges.filter { it[0] == index }.forEach {
            val edgeTotal = getForestTotal(it[1], values, edges)
            total += edgeTotal
        }

        return indexValue + total
    }
}
