package solutions.hackerRank.strings

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.hackerrank.com/challenges/hackerrank-in-a-string/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
 */
class HackerRankInAString {

    @Test
    fun test() {
        assertEquals("YES", hackerrankInString("hhaacckkekraraannk"))
        assertEquals("NO", hackerrankInString("rhbaasdndfsdskgbfefdbrsdfhuyatrjtcrtyytktjjt"))
    }

    val hackerrank = "hackerrank"
    fun hackerrankInString(s: String): String {

        var pIndex = 0

        s.forEach {
            if (pIndex == hackerrank.length) return "YES"
            if (it == hackerrank[pIndex]) {
                pIndex++
            }
        }

        if (pIndex == hackerrank.length) return "YES"

        return "NO"

    }

}