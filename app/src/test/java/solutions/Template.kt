package solutions

import junit.framework.TestCase.assertEquals
import org.junit.Test

class Template {
    @Test
    fun test() {
        val result = solution()
        assertEquals(2, result)
    }

    fun solution(): Int {
        return 0
    }
}