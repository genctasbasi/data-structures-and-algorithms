package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Interweaving%20Strings
 */
class InterweavingStrings {

    @Test
    fun test() {
        val interweaving =
            interweavingStrings("algoexpert", "your-dream-job", "your-algodream-expertjob")
        assertEquals(true, interweaving)
    }

    @Test
    fun test2() {
        val interweaving =
            interweavingStrings("c", "ca", "cac")
        assertEquals(true, interweaving)
    }

    @Test
    fun test3() {
        val interweaving =
            interweavingStrings("abcde", "12345", "ab12cd34e5")
        assertEquals(true, interweaving)
    }

    @Test
    fun test4() {
        val interweaving =
            interweavingStrings("abcde", "12345", "kab12cd34e5")
        assertEquals(true, interweaving)
    }

    @Test
    fun test8() {
        val interweaving =
            interweavingStrings("aabcc", "dbbca", "aadbbcbcac")
        assertEquals(true, interweaving)
    }

    @Test
    fun test18() {
        val interweaving =
            interweavingStrings("algoexpert", "your-dream-job", "your-algodream-expertjo")
        assertEquals(true, interweaving)
    }

    fun interweavingStrings(one: String, two: String, three: String): Boolean {

        var p1 = 0
        var p2 = 0
        var p3 = 0

        var p1Char: Char? = if (p1 >= one.length) null else one[p1]
        var p2Char: Char? = if (p2 >= two.length) null else two[p1]
        var p3Char: Char? = if (p3 >= three.length) null else three[p1]

        while (p3Char != null && !(p1Char == null && p2Char == null)) {

            when {
                p1Char == p3Char && p2Char == p3Char -> {

                    // we have 2 options. we try both & choose the one that works:
                    // fist option: pick and move pointer1
                    val p1ChosenResult = interweavingStrings(
                        one.substring(p1 + 1),
                        two.substring(p2),
                        three.substring(p3 + 1)
                    )

                    // second option: pick and move pointer2
                    val p2ChosenResult = interweavingStrings(
                        one.substring(p1),
                        two.substring(p2 + 1),
                        three.substring(p3 + 1)
                    )

                    when {
                        p1ChosenResult -> p1++
                        p2ChosenResult -> p2++
                        else -> return false
                    }

                    p3++    // we need this regardless
                }

                p1Char == p3Char -> {
                    p1++
                    p3++
                }
                p2Char == p3Char -> {
                    p2++
                    p3++
                }

                else -> {
                    p1++
                    p2++
                }
            }

            p1Char = if (p1 >= one.length) null else one[p1]
            p2Char = if (p2 >= two.length) null else two[p2]
            p3Char = if (p3 >= three.length) null else three[p3]

        }

        // if we managed to come that much, interweaving exits
        return p1Char == null && p2Char == null && p3Char == null
    }
}