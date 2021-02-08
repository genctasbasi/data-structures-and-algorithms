package solutions.other

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class TreeSetPractice {

    @Test
    fun test() {

        add("genc") // 3
        add("tasbasi") // 4
        add("another word") // 5
        add("XX")   // 2
        add("G")   // 1

        assertEquals("G", pollFirst())
        assertEquals("XX", treeSet.first())
        assertEquals("tasbasi", getTail("tasbasi").elementAt(0))
        assertEquals("another word", getTail("tasbasi").elementAt(1))
        assertEquals("XX", getHead("genc").elementAt(0))
        assertEquals("another word", reverse().elementAt(0))
        assertEquals("XX", floor("bbb"))
        assertEquals("genc", floor("genc"))
        assertEquals("tasbasi", ceiling("let it"))
        assertEquals("tasbasi", ceiling("tasbasi"))
        assertEquals(null, ceiling("tasbasitasbasi"))
    }

    private val treeSet = TreeSet<String>(compareBy { it.length })

    fun add(value: String) {
        treeSet.add(value)
    }

    fun pollFirst(): String {
        return treeSet.pollFirst()
    }

    fun getTail(value: String): Set<String> {
        return treeSet.tailSet(value)
    }

    fun getHead(value: String): Set<String> {
        return treeSet.headSet(value)
    }

    fun reverse(): Set<String> {
        return treeSet.descendingSet()
    }

    fun floor(value: String): String {
        return treeSet.floor(value)
    }

    fun ceiling(value: String): String? {
        return treeSet.ceiling(value)
    }
}