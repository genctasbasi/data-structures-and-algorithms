package solutions.other

import org.junit.Test

class ListSpecificOperations {


    @Test
    fun `list specific operations`() {

        val numbersArray = listOf<Int>(1, 5, 7, 2, 9, 0, 4)



        println("Max: ${numbersArray.max()}")
        println("Min: ${numbersArray.min()}")

    }
}