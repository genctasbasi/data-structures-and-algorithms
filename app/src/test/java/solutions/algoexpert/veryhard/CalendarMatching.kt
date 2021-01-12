package solutions.algoexpert.veryhard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Calendar%20Matching
 */
class CalendarMatching {

    @Test
    fun test() {

        val calendar1 =
            listOf(listOf("9:00", "10:30"), listOf("12:00", "13:00"), listOf("16:00", "18:00"))

        val dailyBounds1 = listOf("9:00", "20:00")

        val calendar2 =
            listOf(
                listOf("10:00", "11:30"),
                listOf("12:30", "14:30"),
                listOf("14:30", "15:00"),
                listOf("16:00", "17:00")
            )

        val dailyBounds2 = listOf("10:00", "18:30")

        val meetingDuration = 30

        val calendarMatching =
            calendarMatching(calendar1, dailyBounds1, calendar2, dailyBounds2, meetingDuration)
        assertEquals(
            listOf(
                listOf("11:30", "12:00"),
                listOf("15:00", "16:00"),
                listOf("18:00", "18:30")
            ), calendarMatching
        )

        val s = StringBuilder()
        s.clear()

    }

    fun calendarMatching(
        calendar1: List<List<String>>,
        dailyBounds1: List<String>,
        calendar2: List<List<String>>,
        dailyBounds2: List<String>,
        meetingDuration: Int
    ): List<List<String>> {

        val availableIntervals = mutableListOf<Interval>()
        val intervals1 = calendar1.map { buildInterval(it) }
        val intervals2 = calendar2.map { buildInterval(it) }

        val dailyBoundsDigital1 =
            listOf(getTimeAsDigital(dailyBounds1[0]), getTimeAsDigital(dailyBounds1[1]))

        val dailyBoundsDigital2 =
            listOf(getTimeAsDigital(dailyBounds2[0]), getTimeAsDigital(dailyBounds2[1]))

        var dayStarts = Math.max(dailyBoundsDigital1[0], dailyBoundsDigital2[0])
        var dayEnds = Math.min(dailyBoundsDigital1[1], dailyBoundsDigital2[1])

        var durationStart = 0
        // var durationEnd = 0
        var durationCount = 0
        var minute = 0

        var timeNow = dayStarts + durationCount

        while (timeNow <= dayEnds) {

            if (minute == 60) {
                timeNow += 100
                timeNow -= 60
            } else {
                timeNow++
            }

            val free1 = ifFree(timeNow, intervals1)
            val free2 = ifFree(timeNow, intervals2)

            if (free1 && free2) {

                if (durationStart == 0) {
                    durationStart = timeNow
                }

                durationCount++
            } else {

                if (durationCount > meetingDuration) {    // we had enough time!
                    val interval =
                        Interval(durationStart, durationStart + durationCount, durationCount)
                    availableIntervals.add(interval)

                    // reset
                    durationStart = 0
                    durationCount = 0
                }
            }

            minute++
        }

        return listOf()
    }

    private fun ifFree(timeNow: Int, intervals: List<Interval>): Boolean {

        intervals.forEach {
            if (timeNow > it.start && timeNow < it.end)
                return false

        }

        return true
    }


    class Interval(val start: Int, val end: Int, val duration: Int, var isValid: Boolean = true)

    fun buildInterval(list: List<String>): Interval {

        val start = getTimeAsDigital(list.first())
        val end = getTimeAsDigital(list.last())

        val durationTemp = end - start
        val hoursInMinutes = (durationTemp / 100) * 60
        val minutes = durationTemp.rem(100)
        val duration = hoursInMinutes + minutes

        return Interval(start, end, duration)
    }

    fun getTimeAsDigital(timeStr: String) = timeStr.replace(":", "").toInt()

}