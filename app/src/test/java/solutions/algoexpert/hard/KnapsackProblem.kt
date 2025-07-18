package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Knapsack%20Problem
 */
class KnapsackProblem {

    @Test
    fun test2() {
        val knapsackProblem = knapsackProblem(
            listOf(listOf(1, 2), listOf(4, 3), listOf(5, 6), listOf(6, 7)), // value, weight
            10
        )

        assertEquals(Pair(10, listOf(1, 3)), knapsackProblem)
    }

    @Test
    fun test4() {
        val knapsackProblem = knapsackProblem(
            listOf(
                listOf(2, 1),
                listOf(70, 70),
                listOf(30, 30),
                listOf(69, 69),
                listOf(100, 100)
            ), // value, weight
            100
        )

        assertEquals(Pair(101, listOf(0, 2, 3)), knapsackProblem)
    }

    @Test
    fun test5() {
        val knapsackProblem = knapsackProblem(
            listOf(
                listOf(2, 1),
                listOf(7, 7),
                listOf(3, 3),
                listOf(6, 6),
                listOf(10, 10)
            ), // value, weight
            10
        )

        assertEquals(Pair(11, listOf(0, 2, 3)), knapsackProblem)
    }

    @Test
    fun testOther() {
        val knapsackProblem = knapsackProblem(
            listOf(listOf(1, 1), listOf(4, 3), listOf(5, 4), listOf(7, 5)), // value, weight
            7
        )

        assertEquals(Pair(9, listOf(1, 2)), knapsackProblem)
    }

    private fun knapsackProblem(items: List<List<Int>>, availableCapacity: Int): Int {

        if (availableCapacity == 0) return 0

        val capacities = Array(availableCapacity + 1) { 0 }
        val capacitiesP = Array(availableCapacity + 1) { 0 }

        items.forEachIndexed { _, list ->

            val itemValue = list[0]
            val itemWeight = list[1]

            for (capacity in 1 until capacities.size) {

                if (capacity < itemWeight) {  // just copy what we had before
                    capacities[capacity] = capacitiesP[capacity]

                } else {
                    // now we have the capacity so we have two options:
                    // option1: what we already had before (not picking this new item)
                    val option1 = capacitiesP[capacity]

                    // option2: picking this item + whatever we had without it
                    val whatWeHadBeforeThisItem = capacitiesP[capacity - itemWeight]
                    val option2 = itemValue + whatWeHadBeforeThisItem

                    if (option1 > option2) {    // not picking the item
                        capacities[capacity] = option1
                    } else {    // picking this item makes sense
                        capacities[capacity] = option2
                    }
                }
            }

            capacities.copyInto(capacitiesP)
        }

        // what we have at the end of our list should be what we need:
        return capacities.last()
    }

    /**
     * fucked up this
     */
    fun knapsackProblem3(items: List<List<Int>>, availableCapacity: Int): Pair<Int, List<Int>> {

        val capacities = Array<Int>(availableCapacity + 1) { 0 }
        val capacitiesP = Array<Int>(availableCapacity + 1) { 0 }
        var indexes = Array<MutableList<Int>>(availableCapacity + 1) { mutableListOf() }
        var indexesP = Array<MutableList<Int>>(availableCapacity + 1) { mutableListOf() }

        var listIndexes = mutableSetOf<Int>()

        items.forEachIndexed { index, list ->

            val currentValue = list[0]
            val currentWeight = list[1]

            for (capacity in 1 until capacities.size) {

                val totalValueAtPreviousCapacity = capacities[capacity - 1]

                if (capacity < currentWeight) {
                    capacities[capacity] = capacitiesP[capacity]
                    indexes[capacity] = indexesP[capacity]
                } else if (capacity >= currentWeight) {
                    // option 1 is we don't take this new item so value is the totalValueAtPreviousCapacity
                    // option 2 is we take this item, plus whatever left:
                    val option2Val =
                        if (currentValue == capacities[capacity - currentWeight])
                            currentValue else currentValue + capacities[capacity - currentWeight]

                    if (totalValueAtPreviousCapacity > option2Val) {  // just copy the previous value
                        capacities[capacity] = totalValueAtPreviousCapacity
                        indexes[capacity] = indexes[capacity - 1]

                    } else {
                        indexes[capacity].clear()
                        capacities[capacity] =
                            if (currentValue == capacities[capacity - currentWeight]) {
                                indexes[capacity].add(index)
                                currentValue

                            } else {
                                indexes[capacity].add(index)
                                val indexOf =
                                    items.indexOfFirst { it[0] == capacities[capacity - currentWeight] }
                                if (indexOf != -1 && indexOf != index)
                                    indexes[capacity].add(indexOf)

                                currentValue + capacities[capacity - currentWeight]
                            }
                    }
                }

                capacities.copyInto(capacitiesP)
                indexes.copyInto(indexesP)
            }
        }

        return Pair(capacities.last(), indexes.last().sorted())
    }

    /**
     * fucked up this too
     */
    fun knapsackProblem2(items: List<List<Int>>, availableCapacity: Int): Pair<Int, List<Int>> {

        val capacities = Array<MutableList<Int>>(availableCapacity + 1) { mutableListOf() }

        items.forEachIndexed { index, list ->

            val currentValue = list[0]
            val currentWeight = list[1]

            for (capacity in 1 until capacities.size) {

                val totalValueAtPreviousCapacity = capacities[capacity - 1].sumBy { items[it][0] }

                if (capacity < currentWeight) {
                    capacities[capacity] = capacities[capacity - 1]
                } else if (capacity >= currentWeight) {
                    // option 1 is we don't take this new item so value is the totalValueAtPreviousCapacity
                    // option 2 is we take this item, plus whatever left:
                    val option2Val =
                        currentValue + capacities[capacity - currentWeight].sumBy { items[it][0] }

                    if (totalValueAtPreviousCapacity > option2Val) {  // just copy the previous value
                        capacities[capacity] = capacities[capacity - 1]
                    } else {
                        capacities[capacity].add(index) // that's the current value
                        capacities[capacity].addAll(capacities[capacity - currentWeight]) // that's the current index - the current value
                    }
                }
            }
        }

        return Pair(capacities.last().sum(), capacities.last())
    }
}