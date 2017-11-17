package pro.pedrosa.orderme.model

import java.io.Serializable

data class Dish (var id:Int?, var name: String, var allergens: List<String>?): Serializable{
    constructor(name: String) : this(null,name,null)

   override fun toString() = name
}