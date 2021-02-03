package solutions.crackingTheCodingInterview.chapter1

import junit.framework.TestCase
import org.junit.Test

class WeekCount {

    @Test
    fun test() {
        val result = countWeeks(2016, "February", "July", "Friday")
        TestCase.assertEquals(26, result)
    }

    fun countWeeks(year: Int, startMonth: String, endMonth: String, firstDayOfYear: String): Int {

        val startMonthIndex = months.indexOf(startMonth)
        val endMonthIndex = months.indexOf(endMonth)
        val firstDayIndex = days.indexOf(firstDayOfYear)

        // find first monday of the year
        val daysToFirstMondayOfTheYear = 7 - firstDayIndex
        var currentDay =
            daysToFirstMondayOfTheYear + 1 // for 2014, for example, this should be 6th now (first monday of the year)
        var currentMonthIndex = 0
        var weeksCount = 0

        while (currentMonthIndex < endMonthIndex + 1) { // count in 1 weeks

            if (currentMonthIndex >= startMonthIndex) {
                weeksCount++

                if (currentMonthIndex == startMonthIndex && currentDay == 1) weeksCount++
            }

            currentDay += 7

            val monthDays = getMonthDays(year, currentMonthIndex)
            if (currentDay > monthDays) {
                currentDay -= monthDays
                currentMonthIndex++
            }

            if (currentMonthIndex == endMonthIndex + 1
                || (currentMonthIndex == endMonthIndex && currentDay + 6 > monthDays)

            ) {   // passed the end of last month
                break
            }

        }

        return weeksCount
    }

    private val days =
        listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val months = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    private val monthDays = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    private fun getMonthDays(year: Int, month: Int): Int {
        return when {
            month == 2 && year.rem(4) == 0 -> 29
            else -> monthDays[month]
        }
    }
}