package pro.pedrosa.orderme.model

import java.io.Serializable

/**
 * Created by pedrosa on 2/1/18.
 */
object Dishes : Serializable {

   lateinit var dishes : List<Dish>

    fun totalDishes (dishes: List<Dish>) {
        this.dishes = dishes
    }

    operator fun get(i: Int) = dishes[i]

}