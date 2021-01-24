package solutions.algoexpert.veryhard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/LRU%20Cache
 */
class LRUCache {

    @Test
    fun test() {

        val lruCache = LRUCache(3)
        lruCache.insertKeyValuePair("b", 2)
        lruCache.insertKeyValuePair("a", 1)
        lruCache.insertKeyValuePair("c", 3)
        assertEquals(
            "c", lruCache.getMostRecentKey()
        )
        assertEquals(1, lruCache.getValueFromKey("a"))
        assertEquals("a", lruCache.getMostRecentKey())

        lruCache.insertKeyValuePair("d", 4)
        assertEquals(null, lruCache.getValueFromKey("b"))
        lruCache.insertKeyValuePair("a", 5)
        assertEquals(5, lruCache.getValueFromKey("a"))
    }

    @Test
    fun test2() {

        val lruCache = LRUCache(1)

        assertEquals(null, lruCache.getValueFromKey("a"))

        lruCache.insertKeyValuePair("a", 1)
        assertEquals(1, lruCache.getValueFromKey("a"))

        lruCache.insertKeyValuePair("a", 9001)
        assertEquals(9001, lruCache.getValueFromKey("a"))

        lruCache.insertKeyValuePair("b", 2)
        assertEquals(null, lruCache.getValueFromKey("a"))
        assertEquals(2, lruCache.getValueFromKey("b"))

        lruCache.insertKeyValuePair("c", 3)
        assertEquals(null, lruCache.getValueFromKey("a"))
        assertEquals(null, lruCache.getValueFromKey("b"))
        assertEquals(3, lruCache.getValueFromKey("c"))
    }

    @Test
    fun test3() {

        val lruCache = LRUCache(4)

        lruCache.insertKeyValuePair("a", 1)
        lruCache.insertKeyValuePair("b", 2)
        lruCache.insertKeyValuePair("c", 3)
        lruCache.insertKeyValuePair("d", 4)

        assertEquals(1, lruCache.getValueFromKey("a"))

        lruCache.insertKeyValuePair("e", 5)
        assertEquals(1, lruCache.getValueFromKey("a"))

        assertEquals(1, lruCache.getValueFromKey("a"))
        assertEquals(null, lruCache.getValueFromKey("b"))
        assertEquals(3, lruCache.getValueFromKey("c"))
        lruCache.insertKeyValuePair("f", 5)

        assertEquals(3, lruCache.getValueFromKey("c"))
        assertEquals(null, lruCache.getValueFromKey("d"))

        lruCache.insertKeyValuePair("g", 5)
        assertEquals(null, lruCache.getValueFromKey("e"))
    }

    class LRUNode(
        var key: String,
        var value: Int,
        var next: LRUNode? = null,
        var prev: LRUNode? = null
    )

    class LRUCache(private val maxSize: Int) {

        val map = mutableMapOf<String, LRUNode>()
        var head: LRUNode? = null
        var tail: LRUNode? = null

        fun insertKeyValuePair(key: String, value: Int) {

            if (map.isEmpty()) {
                head = LRUNode(key, value)
                tail = head
                head?.let { map[key] = it }
                return
            }

            if (map.size == maxSize) {
                if (map[key] == null) {   // then this is a new node. first remove the least used one (tail)
                    map.remove(tail?.key)
                    tail = tail?.next
                    tail?.prev = null
                } else {
                    map[key]?.value = value
                }
            }

            if (map.isEmpty()) {
                head = null
                tail = null
            }

            // now we can add it
            val newNode = map[key] ?: LRUNode(key, value)
            moveToHead(newNode)
            map[key] = newNode
        }

        private fun moveToHead(newNode: LRUNode) {
            newNode.prev?.next = newNode.next   // 'tie' it
            newNode.next?.prev = newNode.prev

            if (newNode == tail) tail = newNode.next
            newNode.next = null
            newNode.prev = head

            head?.next = newNode
            head = newNode
            if (tail == null) tail = head

            head?.next = null
            tail?.prev = null
        }

        fun getValueFromKey(key: String): Int? {
            // move it to head first
            // get the node
            val node = map[key] ?: return null

            if (map.size == 1) return node.value

            moveToHead(node)
            return node.value
        }

        fun getMostRecentKey(): String? {
            return head?.key
        }
    }
}