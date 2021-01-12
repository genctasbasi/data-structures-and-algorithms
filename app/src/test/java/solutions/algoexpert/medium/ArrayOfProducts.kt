package solutions.algoexpert.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Array%20Of%20Products
 */

class ArrayOfProducts {

    @Test
    fun test() {
        val arrayOfProducts = arrayOfProducts(listOf(5, 1, 4, 2))
        assertEquals(listOf(8, 40, 10, 20), arrayOfProducts)
    }

    @Test
    fun test2() {
        val arrayOfProducts = arrayOfProducts(listOf(0, 1, 4, 2))
        assertEquals(listOf(8, 0, 0, 0), arrayOfProducts)
    }

    @Test
    fun test3() {
        val arrayOfProducts = arrayOfProducts(listOf(5, 0, 4, 2))
        assertEquals(listOf(0, 40, 0, 0), arrayOfProducts)
    }

    @Test
    fun test4() {
        val arrayOfProducts = arrayOfProducts(listOf(5, 0, 0, 2))
        assertEquals(listOf(0, 0, 0, 0), arrayOfProducts)
    }

    fun arrayOfProducts(array: List<Int>): List<Int> {

        val productArray = mutableListOf<Int>()
        var totalProduct = 1
        var zeroCount = 0
        array.forEach {
            if (it == 0)
                zeroCount++
            else
                totalProduct *= it
        }

        array.forEach {
            if (zeroCount > 1)
                productArray.add(0)
            else if (zeroCount == 1) {

                if (it == 0) {
                    productArray.add(totalProduct)
                }else{
                    productArray.add(0)
                }
            } else {
                productArray.add(totalProduct / it)
            }
        }

        return productArray
    }
}