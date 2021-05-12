package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * Practice tests
 */
class Sandbox {

    @Test
    fun dfsMerge() {
        val lists = listOf(
            listOf("name", "email1", "email2", "email3"),
            listOf("name", "email4", "email1"),
            listOf("name", "email5"),
            listOf("name", "email6", "email7"),
            listOf("name", "email8", "email5", "email9")
        )

        // 1. build graph & name
        val graph = hashMapOf<String, HashSet<String>>()
        val names = hashMapOf<String, String>()
        val output = mutableListOf<List<String>>()

        lists.forEach { accounts ->
            val name = accounts[0]
            val emailCount = accounts.size - 1

            (1..emailCount).forEach { emailIndex ->

                names[accounts[emailIndex]] = name

                if (emailIndex != 1) {

                    if (graph[accounts[emailIndex]] == null)
                        graph[accounts[emailIndex]] = hashSetOf()

                    if (graph[accounts[emailIndex - 1]] == null)
                        graph[accounts[emailIndex - 1]] = hashSetOf()

                    graph[accounts[emailIndex]]?.add(accounts[emailIndex - 1])
                    graph[accounts[emailIndex - 1]]?.add(accounts[emailIndex])
                }

            }
        }

        graph.keys.forEach {
            val list = mutableListOf<String>()
            doDfsToThis(graph, it, list)
            if (list.isNotEmpty())
                output.add(list)
        }

        assertEquals(3, output.size)
    }

    val visited = hashSetOf<String>()
    fun doDfsToThis(
        graph: Map<String, HashSet<String>>,
        item: String,
        output: MutableList<String>
    ) {

        if (visited.contains(item)) return
        visited.add(item)

        output.add(item)

        graph[item]?.forEach {
            doDfsToThis(graph, it, output)
        }
    }

    @Test
    fun longestSubarray() {

        val nums = intArrayOf(10, 1, 2, 4, 8, 2)
        val limit = 5

        val maxQ: Deque<Int> = LinkedList()
        val minQ: Deque<Int> = LinkedList()

        var start = 0
        var size = 0
        for (end in nums.indices) {

            while (!maxQ.isEmpty() && maxQ.peekLast() < nums[end]) {
                maxQ.pollLast()
            }
            while (!minQ.isEmpty() && minQ.peekLast() > nums[end]) {
                minQ.pollLast()
            }

            maxQ.addLast(nums[end])
            minQ.addLast(nums[end])

            if (maxQ.peekFirst() - minQ.peekFirst() > limit) {
                if (nums[start] == maxQ.peekFirst()) {
                    maxQ.pollFirst()
                }
                if (nums[start] == minQ.peekFirst()) {
                    minQ.pollFirst()
                }
                start++
            }

            size = Math.max(size, end - start + 1)
        }

        assertEquals(5, size)
    }

    /**
     * Deque: Pronounced as 'deck', meaning 'double ended queue'
     */
    @Test
    fun deque() {

        val deque: Deque<Int> = LinkedList<Int>()

        deque.add(7)
        deque.addFirst(1)
        deque.addFirst(2)
        deque.addFirst(3)
        deque.add(8)

        deque.addLast(100)
        deque.addLast(200)
        deque.addLast(300)

        assertEquals(3, deque.first)
        assertEquals(300, deque.last)
    }

    @Test
    fun `min heap`() {

        val minHeap: Queue<Int> = PriorityQueue<Int>()
        minHeap.offer(10)
        minHeap.offer(5)
        assertEquals(5, minHeap.peek())

        minHeap.offer(2)
        assertEquals(2, minHeap.peek())

        val head = minHeap.poll()
        assertEquals(2, head)
        assertEquals(5, minHeap.peek())
        assertEquals(2, minHeap.size)
    }

    @Test
    fun `max heap`() {
        val maxHeap: Queue<Int> = PriorityQueue<Int>(Collections.reverseOrder())
        maxHeap.offer(5)
        maxHeap.offer(10)

        assertEquals(10, maxHeap.peek())

        maxHeap.offer(20)
        assertEquals(20, maxHeap.peek())

        val head = maxHeap.poll()
        assertEquals(20, head)
        assertEquals(10, maxHeap.peek())
        assertEquals(2, maxHeap.size)
    }

    @Test
    fun `tree map`() {

        val map = TreeMap<Int, Int>()   // It's a red-black tree implementation
        map[10] = 20
        map[15] = 25

        map.subMap(10, false, 20, true).clear()
        assertEquals(10, map.floorKey(15))
    }

    @Test
    fun `maximize expressions`() {
        val input = listOf(3, 6, 1, -3, 2, 7)
        val output = maximizeExpression(input)
        assertEquals(4, output)
    }

    private fun maximizeExpression(input: List<Int>): Int {

        if (input.size < 4) return 0

        var start = 0
        var end = 3
        var max = Int.MIN_VALUE

        while (start <= input.lastIndex) {
            while (end <= input.lastIndex) {

                val combinations = getCombinations(
                    0,
                    listOf(
                        input[start],
                        input[start + 1],
                        input[start + 2],
                        input[start + 3]
                    )
                )

                combinations.forEach {
                    if (isInOrder(it[0], it[1], it[2], it[3], input)) {
                        max = Math.max(max, cal(it[0], it[1], it[2], it[3]))
                    }
                }

                end++
            }

            start++
            end = start + 3
        }

        return max
    }

    private fun getCombinations(
        startIndex: Int,
        list: List<Int>
    ): MutableList<MutableList<Int>> {

        if (startIndex == 3) {
            return mutableListOf(mutableListOf(list[3]))
        }

        val combinations = mutableListOf<MutableList<Int>>()
        val num = list[startIndex]
        val rest = getCombinations(startIndex + 1, list)

        rest.forEach {

            it.forEachIndexed { index, i ->
                val preList = it.subList(0, index + 1)
                val postList = it.subList(index + 1, it.lastIndex + 1)
                val newList = (preList + num + postList).toMutableList()
                combinations.add(newList)
            }

            val l = mutableListOf(num) + it
            combinations.add(l.toMutableList())
        }

        return combinations
    }

    fun cal(a: Int, b: Int, c: Int, d: Int) = a - b + c - d

    fun isInOrder(a: Int, b: Int, c: Int, d: Int, list: List<Int>) =
        list.indexOf(a) < list.lastIndexOf(b) &&
                list.indexOf(b) < list.lastIndexOf(c) &&
                list.indexOf(c) < list.lastIndexOf(d)
}