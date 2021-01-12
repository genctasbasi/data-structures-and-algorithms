package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Min%20Rewards
 */
class MinRewards {

    @Test
    fun test() {
        val scores = minRewards(mutableListOf(8, 4, 2, 1, 3, 6, 7, 9, 5))
        assertEquals(25, scores)
    }

    @Test
    fun test2() {
        val scores = minRewards(mutableListOf(0, 4, 2, 1, 3))
        assertEquals(9, scores)
    }

    @Test
    fun test3() {
        val scores = minRewards(mutableListOf(2, 1, 4, 3, 6, 5, 8, 7, 10, 9))
        assertEquals(15, scores)
    }

    fun minRewards(scores: List<Int>): Int {
        val rewards = mutableListOf<Int>()

        var prevItem = Int.MIN_VALUE
        var currentReward = 0

        for (i in scores.indices) {

            val item = scores[i]

            if (item > prevItem) {
                currentReward++
                rewards.add(currentReward)
            } else {

                val fixNeeded = rewards.last() == 1
                rewards.add(1)
                currentReward = 1

                if (fixNeeded) {    // fixing needed
                    fixRewards(rewards)
                    currentReward = rewards.last()
                }
            }

            prevItem = item
        }

        return rewards.sum()
    }

    private fun fixRewards(rewards: MutableList<Int>) {

        var prevReward = Int.MIN_VALUE
        for (i in rewards.size - 1 downTo 0) {
            val reward = rewards[i]

            if (reward == prevReward) {
                rewards[i] = rewards[i] + 1
            }

            prevReward = rewards[i]
        }
    }

    fun minRewards2(scores: List<Int>): Int {

        val rewards = mutableListOf<Int>()
        var rewardTotal = 0
        val minIndexPair = getMinIndex(scores)
        val minIndex = minIndexPair.first
        val min = minIndexPair.second

        var index = minIndex - 1

        var currentReward = 1 // for the min index
        rewards.add(currentReward)
        rewardTotal = currentReward
        var prev = min
        while (index >= 0) {

            if (scores[index] > prev) {
                currentReward++
            } else {
                currentReward = 1
            }

            rewardTotal += currentReward
            rewards.add(0, currentReward)

            prev = scores[index]
            index--
        }

        index = minIndex + 1
        currentReward = 1

        prev = min
        while (index < scores.size) {

            if (scores[index] > prev) {
                currentReward++
            } else {
                currentReward = 1
            }

            rewardTotal += currentReward
            rewards.add(currentReward)
            prev = scores[index]
            index++
        }

        var hasChanged = false

        do {
            hasChanged = false

            for (i in rewards.indices) {

                if (i + 1 < rewards.size && rewards[i + 1] == rewards[i]) {
                    rewards[i] = rewards[i] + 1
                    hasChanged = true
                    break
                }
            }


        } while (hasChanged)



        return rewards.sum()
    }

    private fun getMinIndex(scores: List<Int>): Pair<Int, Int> {

        var min = Int.MAX_VALUE
        var minIndex = -1

        scores.forEachIndexed { index, i ->

            if (i < min) {
                minIndex = index
                min = i
            }
        }

        return Pair(minIndex, min)
    }

}