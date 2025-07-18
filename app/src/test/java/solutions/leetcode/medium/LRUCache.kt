package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/lru-cache/
 */
class LRUCache {

    @Test
    fun test() {
        val cache = LRUCache(2)
        cache.put(1, 1)
        cache.put(2, 2)
        assertEquals(1, cache.get(1))
        cache.put(3, 3)
        assertEquals(-1, cache.get(2))
        assertEquals(3, cache.get(3))
        cache.put(4, 4)
        assertEquals(-1, cache.get(1))
        assertEquals(3, cache.get(3))
        assertEquals(4, cache.get(4))
    }

    @Test
    fun test2() {
        val cache = LRUCache(2)
        assertEquals(-1, cache.get(2))
        cache.put(2, 6)
        assertEquals(-1, cache.get(1))
        cache.put(1, 5)
        cache.put(1, 2)
        assertEquals(2, cache.get(1))
        assertEquals(6, cache.get(2))
    }

    @Test
    fun test3() {
        val cache = LRUCache(2)
        cache.put(2, 1)
        cache.put(3, 2)
        assertEquals(2, cache.get(3))
        assertEquals(1, cache.get(2))
        cache.put(4, 3)
        assertEquals(1, cache.get(2))
        assertEquals(-1, cache.get(3))
        assertEquals(3, cache.get(4))
    }

    class Node(var value: Int, val key: Int, var prev: Node?, var next: Node?)

    class LRUCache(private val capacity: Int) {

        val map = mutableMapOf<Int, Node>()

        var head: Node? = null
        var tail: Node? = null

        fun get(key: Int): Int {
            if (map[key] == null) return -1
            makeHead(map[key]!!)
            return map[key]!!.value
        }

        fun put(key: Int, value: Int) {

            if (map[key] != null) {
                map[key]?.value = value
                makeHead(map[key]!!)
            } else if (map.size >= capacity) { // remove tail, make this one head
                map.remove(tail?.key)
                tail = tail?.next
                tail?.prev = null

                val newNode = Node(value, key, null, null)
                map[key] = newNode
                makeHead(newNode)
            } else {
                val newNode = Node(value, key, null, null)
                map[key] = newNode
                makeHead(newNode)
            }
        }

        private fun makeHead(node: Node) {

            if (head == node) return

            if (node == tail) {
                tail = node.next
            }

            node.prev?.next = node.next
            node.next?.prev = node.prev

            head?.next = node
            node.next = null
            node.prev = head

            head = node

            if (tail == null)
                tail = node
        }
    }
}