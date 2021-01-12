package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Numbers%20In%20Pi
 */
class NumbersInPI {

    @Test
    fun test() {
        val numbersInPi = numbersInPi(
            "3141592653589793238462643383279", listOf(
                "314159265358979323846",
                "26433",
                "8",
                "3279",
                "314159265",
                "35897932384626433832",
                "79"
            )
        )

        assertEquals(2, numbersInPi)
    }

    fun numbersInPi(pi: String, numbers: List<String>): Int {

        
        return -1
    }
}