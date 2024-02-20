package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Test

class RestaurantMenu {

    val starters = listOf("Prawn Coctail", "Bruschetta", "Aranchini", "Asian Glazed Wings")
    val mains = listOf(
        "Pizza Margherita",
        "Pizza Pepperoni",
        "Burger Quarter Pounder",
        "Cheese Burger",
        "Ratatuille"
    )
    val desserts = listOf("Cheesecake", "Chocolate Fudge Cake", "Tiramisu", "Profiterole")

    val chefsItems =
        listOf("Bruschetta", "Pizza Margherita", "Pizza Pepperoni", "Cheesecake", "Tiramisu")

    // Q: Sefin pisirebildigi yemeklere gore restoran menusunu yeniden duzenleyin. Listeler restoranda pisen yemekler ama bu sefin calistigi gun sadece bu yemekler pisirilebilir

// A: starters, mains ve deserts dizilerini sefin pisirebildiklerine gore filtrele.


    @Test
    fun `filter chef food`() {
        println("starters : ${starters.filter { it in chefsItems }}")
        println("main : ${mains.filter { it in chefsItems }}")
        println("deserts : ${desserts.filter { it in chefsItems }}")
    }


}