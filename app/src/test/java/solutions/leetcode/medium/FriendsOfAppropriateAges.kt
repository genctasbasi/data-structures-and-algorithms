package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/friends-of-appropriate-ages/
 */
class FriendsOfAppropriateAges {

    @Test
    fun test() {
        assertEquals(3, numFriendRequests(intArrayOf(20, 30, 100, 110, 120)))
    }

    @Test
    fun test1() {
        assertEquals(2, numFriendRequests(intArrayOf(16, 16)))
    }

    @Test
    fun test2() {
        assertEquals(2, numFriendRequests(intArrayOf(16, 17, 18)))
    }

    fun numFriendRequests(ages: IntArray): Int {

        // count ages
        val map = hashMapOf<Int, Int>()
        ages.forEach { map[it] = (map[it] ?: 0) + 1 }

        val agesAggregated = map.keys.toList()

        var p1 = 0
        var p2 = 0
        var count = 0

        while (p1 <= agesAggregated.lastIndex) {

            while (p2 <= agesAggregated.lastIndex) {
                if (canSendRequest(agesAggregated[p1], agesAggregated[p2])
                ) {
                    count += (map[agesAggregated[p1]]!! * map[agesAggregated[p2]]!!)
                    if (p1 == p2) count -= map[agesAggregated[p1]]!!
                }
                p2++
            }

            p1++
            p2 = 0
        }

        return count
    }

    /**
     * O(n2) - mention this but don't even start coding!
     */
    fun `numFriendRequests O(n2)`(ages: IntArray): Int {

        if (ages.size == 1) return 0

        var p1 = 0
        var p2 = 0
        var count = 0

        while (p1 <= ages.lastIndex) {

            while (p2 <= ages.lastIndex) {
                if (p1 != p2
                    && canSendRequest(ages[p1], ages[p2])
                ) count++
                p2++
            }

            p1++
            p2 = 0
        }

        return count
    }

    // request is always assumed from age1 to age2
    fun canSendRequest(age1: Int, age2: Int): Boolean {

        if (age2 > 100 && age1 < 100) return false
        if (age2 > age1) return false
        if (age2 <= (age1 * 0.5 + 7)) return false

        return true
    }

}