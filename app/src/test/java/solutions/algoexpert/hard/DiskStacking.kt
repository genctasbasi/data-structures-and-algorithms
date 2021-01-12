package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Disk%20Stacking
 */
class DiskStacking {

    @Test
    fun test() {
        val diskStacking = diskStacking(
            listOf(
                listOf(2, 1, 2),    //w, d, h
                listOf(3, 2, 3),
                listOf(2, 2, 8),
                listOf(2, 3, 4),
                listOf(1, 3, 1),
                listOf(4, 4, 5)
            )
        )

        assertEquals(listOf(listOf(2, 1, 2), listOf(3, 2, 3), listOf(4, 4, 5)), diskStacking)
    }

    @Test
    fun test4() {
        val diskStacking = diskStacking(
            listOf(
                listOf(2, 1, 2),    //w, d, h
                listOf(3, 2, 3),
                listOf(2, 2, 8)
            )
        )

        assertEquals(listOf(listOf(2, 2, 8)), diskStacking)
    }

    @Test
    fun test5() {
        val diskStacking = diskStacking(
            listOf(
                listOf(2, 1, 2),
                listOf(3, 2, 3),
                listOf(2, 2, 8),
                listOf(2, 3, 4),
                listOf(2, 2, 1),
                listOf(4, 4, 5)
            )
        )

        assertEquals(listOf(listOf(2, 3, 4), listOf(4, 4, 5)), diskStacking)
    }

    @Test
    fun test8() {
        val diskStacking = diskStacking(
            listOf(
                listOf(2, 1, 2),
                listOf(3, 2, 3),
                listOf(2, 2, 8),
                listOf(2, 3, 4),
                listOf(1, 2, 1),
                listOf(4, 4, 5),
                listOf(1, 1, 4)
            )
        )

        assertEquals(listOf(listOf(1, 1, 4), listOf(2, 2, 8)), diskStacking)
    }

    fun diskStacking(disks: List<List<Int>>): List<List<Int>> {
        val maxHeightDisks = mutableListOf<MutableList<List<Int>>>()

        // sort by height first
        val sorted = disks.sortedWith(Comparator { p0, p1 ->
            when {
                p0[2] > p1[2] -> 1
                p0[2] < p1[2] -> -1
                else -> 0
            }
        })

        for (i in sorted.indices) {
            maxHeightDisks.add(mutableListOf(sorted[i]))
        }

        for (i in sorted.indices) {

            val disk = sorted[i]
            var maxHeight = disk[2]
            var potentialDisks = listOf<List<Int>>()

            for (j in 0..i) {
                val diskBefore = sorted[j]
                if (canBePlaced(diskBefore, disk)) {
                    val potentialHeight = disk[2] + maxHeightDisks[j].sumBy { it[2] }

                    if (potentialHeight > maxHeight) {
                        maxHeight = potentialHeight
                        potentialDisks = maxHeightDisks[j]
                    }
                }
            }

            for (k in potentialDisks.size - 1 downTo 0) {
                maxHeightDisks[i].add(0, potentialDisks[k])
            }
        }

        return maxHeightDisks.maxBy { it.sumBy { it[2] } }?.toList() ?: listOf()
    }

    private fun canBePlaced(diskBefore: List<Int>, disk: List<Int>) =
        diskBefore[0] < disk[0] && diskBefore[1] < disk[1] && diskBefore[2] < disk[2]

    fun diskStacking3(disks: List<List<Int>>): List<List<Int>> {

        var maxHeight = Int.MIN_VALUE
        var maxHeightStacks = listOf<List<Int>>()

        disks.forEach { diskInCheck ->

            val wList = mutableListOf(diskInCheck[0])
            val dList = mutableListOf(diskInCheck[1])
            val hList = mutableListOf(diskInCheck[2])
            val potentialList = mutableListOf<List<Int>>()

            potentialList.add(diskInCheck)

            for (i in disks.indices) {

                val disk = disks[i]

                if (
                    wList.min() ?: Int.MIN_VALUE > disk[0] &&
                    dList.min() ?: Int.MIN_VALUE > disk[1] &&
                    hList.min() ?: Int.MIN_VALUE > disk[2]
//
//                    disk[0] < diskInCheck[0] &&
//                    disk[1] < diskInCheck[1] &&
//                    disk[2] < diskInCheck[2] &&
//                    !wList.min() .contains(disk[0]) &&
//                    !dList.contains(disk[1]) &&
//                    !hList.contains(disk[2])
                ) {
                    wList.add(disk[0])
                    dList.add(disk[1])
                    hList.add(disk[2])

                    potentialList.add(disk)
                }
            }

            val height2 = dList.sum()

            if (height2 > maxHeight) {
                maxHeight = height2
                maxHeightStacks = potentialList
            }
        }

        val sorted = maxHeightStacks.sortedWith(Comparator { p0, p1 ->
            when {
                p0[0] > p1[0] -> 1
                p0[0] < p1[0] -> -1
                else -> 0
            }
        }).sortedWith(Comparator { p0, p1 ->
            when {
                p0[1] > p1[1] -> 1
                p0[1] < p1[1] -> -1
                else -> 0
            }
        }).sortedWith(Comparator { p0, p1 ->
            when {
                p0[2] > p1[2] -> 1
                p0[2] < p1[2] -> -1
                else -> 0
            }
        })

        return sorted.toList()
    }

    fun diskStacking2(disks: List<List<Int>>): List<List<Int>> {

        var maxHeight = Int.MIN_VALUE
        var maxHeightStacks = mutableSetOf<List<Int>>()

        disks.forEach { diskInCheck ->
            val wCanStack = disks.filter { it[0] > diskInCheck[0] }
            val dCanStack = disks.filter { it[1] > diskInCheck[1] }
            val hCanStack = disks.filter { it[2] > diskInCheck[2] }

            val common = wCanStack.intersect(dCanStack).intersect(hCanStack).toMutableList()
            removeDuplicates(common)

            common.add(diskInCheck)
            val height = common.sumBy { it[2] }
            if (height > maxHeight) {
                maxHeight = height
                maxHeightStacks = common.toMutableSet()
            }
        }

        val sorted = maxHeightStacks.sortedWith(Comparator { p0, p1 ->
            when {
                p0[0] > p1[0] -> 1
                p0[0] < p1[0] -> -1
                else -> 0
            }
        }).sortedWith(Comparator { p0, p1 ->
            when {
                p0[1] > p1[1] -> 1
                p0[1] < p1[1] -> -1
                else -> 0
            }
        }).sortedWith(Comparator { p0, p1 ->
            when {
                p0[2] > p1[2] -> 1
                p0[2] < p1[2] -> -1
                else -> 0
            }
        })

        return sorted.toList()
    }

    private fun removeDuplicates(common: MutableList<List<Int>>) {
        val wList = mutableListOf<Int>()
        val hList = mutableListOf<Int>()
        val dList = mutableListOf<Int>()
        val removeList = mutableListOf<List<Int>>()

        val sorted = common.sortedWith(Comparator { p0, p1 ->
            when {
                p0[2] > p1[2] -> -1
                p0[2] < p1[2] -> 1
                else -> 0
            }
        }).toMutableList()

        sorted.forEach {
            if (dList.contains(it[2])) {
                removeList.add(it)
            } else {
                dList.add(it[2])
            }
        }

        sorted.forEach {
            if (wList.contains(it[0])) {
                removeList.add(it)
            } else {
                wList.add(it[0])
            }
        }

        sorted.forEach {
            if (hList.contains(it[1])) {
                removeList.add(it)
            } else {
                hList.add(it[1])
            }
        }

        removeList.forEach {
            common.remove(it)
        }
    }
}