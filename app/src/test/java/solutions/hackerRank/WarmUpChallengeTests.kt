package solutions.hackerRank

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Warm-up Challenges from
 * https://www.hackerrank.com/interview/interview-preparation-kit/warmup/challenges
 *
 */
class WarmUpChallengeTests {
    @Test
    fun sock_merchant() {

        // Arrange
        val testData = listOf(10, 20, 20, 10, 10, 30, 50, 10, 20)

        // Act
        val pairs = testData.groupBy { it }.values.sumBy { it.size.div(2) }

        // Assert
        assertEquals(pairs, 3)  // there are 3 pairs
    }

    /**
     * solution without Collection functions
     */
    @Test
    fun sock_merchant_without_collection_functions() {

        // Arrange
        val testData = listOf(10, 20, 20, 10, 10, 30, 50, 10, 20)
        val countMap = mutableMapOf<Int, Int>()
        var totalPairs = 0

        // Act
        testData.forEach {
            val count = countMap[it] ?: 0
            countMap[it] = count + 1

            if (countMap[it]?.rem(2) == 0)
                totalPairs++

        }

        // Assert
        assertEquals(totalPairs, 3)  // there are 3 pairs
    }

    @Test
    fun jumping_on_the_clouds() {

        // Arrange
        val testData = listOf(0, 0, 1, 0, 0, 1, 0)
        val cloudSize = testData.size
        var jumpCount = 0
        var cloudIndex = 0

        // Act
        do {
            if (cloudIndex + 2 < cloudSize && testData[cloudIndex + 2] == 0) {
                cloudIndex += 2
            } else if (cloudIndex + 1 < cloudSize && testData[cloudIndex + 1] == 0) {
                cloudIndex++
            } else {
                break
            }

            jumpCount++

        } while (cloudIndex < testData.size)

        // Assert
        assertEquals(jumpCount, 4)
    }

    @Test
    fun counting_valleys() {

        // Arrange
        val valley = "UDDDUDUU"
        var valleyCount = 0
        var currentLevel = 0
        var isInValley = false

        // Act
        valley.forEach { step ->

            if (step == 'U')
                currentLevel++
            else
                currentLevel--

            if (!isInValley && currentLevel < 0) {   // this is a (new) valley now
                valleyCount++
                isInValley = true
            }

            if (currentLevel >= 0)
                isInValley = false
        }

        // Assert
        assertEquals(valleyCount, 1)
    }

    @Test
    fun repeated_strings() {
        // Arrange
        val lookFor = 'a'
        val sequence = "aba"
        val repeat: Long = 10
        var count: Long

        // we have this many sequences
        val howManySequences = repeat.div(sequence.length)

        // we have this many occurrences of `a` in a single sequence
        val foundInSingleSequence = sequence.count { it == lookFor }

        // total this many occurrences in all complete sequences
        count = howManySequences * foundInSingleSequence

        // find how many `a` in the 'remaining' sequence
        val remainingSequence = sequence.substring(0, repeat.rem(sequence.length).toInt())
        val foundInRemainingSequence = remainingSequence.count { it == lookFor }

        // total count
        count += foundInRemainingSequence

        // Assert
        assertEquals(count, 7)
    }
}
