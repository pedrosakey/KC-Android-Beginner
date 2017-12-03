package pro.pedrosa.orderme.model

import android.os.Parcelable
import java.io.Serializable

data class Order (val dish: Dish, var number: Int) :Serializable{
    override fun toString(): String = dish.name + " X " + number

    override fun equals(other: Any?): Boolean {
        if(other is Order) {
            return other.dish.name == dish.name
        }
        return false
    }
}