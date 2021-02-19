package solutions.algoexpert.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Tournament%20Winner
 */
class TournamentWinner {

    @Test
    fun test() {
        assertEquals(
            "Python", tournamentWinner(
                listOf(
                    listOf("HTML", "C#"),
                    listOf("C#", "Python"),
                    listOf("Python", "HTML")
                ),

                listOf(0, 0, 1)
            )
        )
    }

    fun tournamentWinner(competitions: List<List<String>>, results: List<Int>): String {

        val map = hashMapOf<String, Int>()
        competitions.forEachIndexed { index, list ->

            val result = results[index]
            val home = list[0]
            val away = list[1]

            if (result == 1) map[home] = (map[home] ?: 0) + 3
            if (result == 0) map[away] = (map[away] ?: 0) + 3
        }

        return map.maxBy { it.value }?.key ?: ""
    }

}