package solutions.algoexpert.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Non-Constructible%20Change
 */
class NonConstructibleChange {

    @Test
    fun test() {
        assertEquals(20, nonConstructibleChange(mutableListOf(5, 7, 1, 1, 2, 3, 22)))
    }

    fun nonConstructibleChange(coins: MutableList<Int>): Int {

        if (coins.isEmpty()) return 1

        // 5, 7, 1, 1, 2, 3, 22
        // 1, 1, 2, 3, 5, 7, 22
        val coinsSorted = coins.sorted()

        var check = 1

        while (true) {
            if (!canBuild(coinsSorted, 0, check, hashMapOf()))
                return check

            check++
        }

        return -1
    }

    fun canBuild(
        coins: List<Int>,
        startIndex: Int,
        amount: Int,
        mem: HashMap<String, Boolean>
    ): Boolean {

        val key = "$startIndex-$amount"
        if (mem[key] != null) return mem[key]!!

        if (amount <= 0) return false
        if (startIndex > coins.lastIndex) return false
        if (startIndex == coins.lastIndex && amount == coins.last()) return true
        if (amount == coins[startIndex]) return true

        val option1 = canBuild(coins, startIndex + 1, amount, mem)
        mem["${startIndex + 1}-$amount"] = option1

        val option2 = canBuild(coins, startIndex + 1, amount - coins[startIndex], mem)
        mem["${startIndex + 1}-${amount - coins[startIndex]}"] = option2

        return option1 || option2
    }

}