package pro.pedrosa.orderme.model

import java.io.Serializable

data class Table (var name: String, var order: MutableList<Order>) : Serializable{

    constructor(name : String) :  this(name, mutableListOf())

    override fun toString() = name

    fun joinOrder (orderTojoin: MutableList<Order>){

        // Copruebo si existe sino lo añado
        if(order.contains(orderTojoin[0])){
           var iOrder = order.indexOf(orderTojoin[0])
            var oNumber = orderTojoin[0].number

            var orderOriginal = order.get(iOrder)
            orderOriginal.number = orderOriginal.number + oNumber

            // Delete & insert
            order.removeAt(iOrder)
            order.add(iOrder, orderOriginal)

        } else {
            order.add(orderTojoin[0])
        }


    }
}