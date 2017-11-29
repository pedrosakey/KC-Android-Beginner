package pro.pedrosa.orderme.model

import android.widget.ImageView
import java.io.Serializable

data class Dish (var id:Int?, var name: String, var price: Int?, var image: Int,var allergens: List<String>?): Serializable{
    constructor(name: String) : this(null,name,null,0,null)

   override fun toString() = name
}