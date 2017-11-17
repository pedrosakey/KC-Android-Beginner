package pro.pedrosa.orderme.model

import java.io.Serializable

data class Table (var name: String, var dishes: MutableList<Dish>) : Serializable{

    constructor(name : String) :  this(name, mutableListOf())

    override fun toString() = name
}