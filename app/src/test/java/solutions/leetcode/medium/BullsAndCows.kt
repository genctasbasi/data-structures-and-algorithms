package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/bulls-and-cows/
 */
class BullsAndCows {

    @Test
    fun test() {
        val result = getHint("1807", "7810")
        assertEquals("1A3B", result)
    }

    @Test
    fun test1() {
        val result = getHint("180675675677575677", "781346363546356340")
        assertEquals("1A9B", result)
    }

    fun getHint(secret: String, guess: String): String {

        if (secret.isEmpty() || guess.isEmpty()) return "0A0B"
        if (secret == guess) return "${secret.length}A0B"

        val mapSecret = mutableMapOf<Char, Int>()
        var bulls = 0

        secret.forEachIndexed { index, char ->
            mapSecret[char] = (mapSecret[char] ?: 0) + 1
            if (char == guess[index]) {
                bulls++
            }
        }

        var cows = 0
        guess.forEach {
            if (mapSecret[it] ?: 0 > 0) {
                cows++
                mapSecret[it] = mapSecret[it]!! - 1
            }
        }

        cows -= bulls
        return "${bulls}A${cows}B"
    }
}

