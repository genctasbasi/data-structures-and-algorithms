package solutions.leetcode.hard

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 *
 */
class CountOfSmallerNumbersAfterSelf {

    @Test
    fun test() {
        assertArrayEquals(
            intArrayOf(4, 2, 1, 1, 0),
            countSmaller(intArrayOf(7, 5, 2, 6, 1)).toIntArray()
        )
    }

    @Test
    fun test1() {
        assertArrayEquals(intArrayOf(0), countSmaller(intArrayOf(0)).toIntArray())
    }

    @Test
    fun test2() {
        assertArrayEquals(intArrayOf(0, 0), countSmaller(intArrayOf(-1, -1)).toIntArray())
    }

    @Test
    fun test3() {
        assertArrayEquals(intArrayOf(0, 1, 0), countSmaller(intArrayOf(0, 2, 0)).toIntArray())
    }

    data class Node(val value: Int, var count: Int, var left: Node? = null, var right: Node? = null)

    fun countSmaller(nums: IntArray): List<Int> {
        if (nums.isEmpty()) return listOf()
        val output = Array(nums.size) { 0 }

        buildTree(nums, output)

        return output.toList()
    }

    private fun buildTree(nums: IntArray, output: Array<Int>) {

        val root = Node(nums.last(), 0)
        output[nums.lastIndex] = 0

        for (i in nums.lastIndex - 1 downTo 0) {
            val num = nums[i]
            insert(root, num, 0, i, output)
        }
    }

    private fun insert(
        root: Node,
        adding: Int,
        newCount: Int,
        index: Int,
        output: Array<Int>
    ): Node {

        if (adding < root.value) {
            if (root.left == null) {
                root.left = Node(adding, newCount)
                output[index] = newCount
                root.count += 1
            } else {
                root.count += 1
                insert(root.left!!, adding, newCount, index, output)
            }
        } else {
            if (root.right == null) {
                root.right = Node(adding, root.count + 1)
                output[index] = root.count + 1
            } else {
                insert(root.right!!, adding, root.count + 1, index, output)
            }
        }

        return root
    }

    fun `countSmaller O(n2)`(nums: IntArray): List<Int> {

        val output = Array(nums.size) { 0 }

        for (i in 0..nums.lastIndex) {
            var count = 0
            for (j in (i + 1)..nums.lastIndex) {
                if (nums[j] < nums[i]) {
                    count++
                }
            }
            output[i] = count
            count = 0
        }

        return output.toList()
    }

}