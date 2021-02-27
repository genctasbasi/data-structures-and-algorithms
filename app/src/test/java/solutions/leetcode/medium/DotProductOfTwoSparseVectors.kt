package solutions.leetcode.medium

/**
 * https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
 */
class DotProductOfTwoSparseVectors {

    class SparseVector(val nums: IntArray) {

        fun dotProduct(vec: SparseVector): Int {

            var total = 0
            var p = 0

            while (p <= vec.nums.lastIndex) {
                total += vec.nums[p] * nums[p]
                p++
            }

            return total
        }
    }
}