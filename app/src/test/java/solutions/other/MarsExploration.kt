package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.hackerrank.com/challenges/mars-exploration/submissions/code/201526954
 */
class MarsExploration {

    @Test
    fun test() {
        assertEquals(3, marsExploration("SOSSPSSQSSOR"))
    }

    fun marsExploration(s: String): Int {

        val sos = listOf('S', 'O', 'S')
        var count = 0
        var p = 0
        s.forEach {
            if (it != sos[p]) count++
            p++
            if (p > 2) p = 0
        }

        return count
    }
}