package pro.pedrosa.orderme.model

import android.os.Parcelable
import java.io.Serializable

data class Order (val dish: Dish, var number: Int, var dishComments: MutableList<String>?) : Serializable{

    constructor (dish: Dish, number: Int) : this(dish,number, mutableListOf())

    override fun toString(): String = dish.name + " X " + number

    override fun equals(other: Any?): Boolean {
        if(other is Order) {
            return other.dish.name == dish.name
        }
        return false
    }

    fun addComment(comment: String) {
        dishComments?.add(comment)
    }

    fun joinComments (order : Order){
        var commentsToJoin = order.dishComments
        if (commentsToJoin != null ) {
            dishComments?.addAll(commentsToJoin)
        }

    }

    fun orderPrice () : String =
        "Price: " + dish.price + " x"+ number + "....." + dish.price!!*number

}