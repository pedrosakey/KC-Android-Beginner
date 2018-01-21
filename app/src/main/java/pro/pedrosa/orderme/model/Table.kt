package pro.pedrosa.orderme.model

import java.io.Serializable

data class Table (var name: String, var order: MutableList<Order>) : Serializable{

    constructor(name : String) :  this(name, mutableListOf())

    override fun toString() = name

    fun joinOrder (orderTojoin:Order){

        // Compruebo si existe sino lo añado
        if(order.contains(orderTojoin)){
           var iOrder = order.indexOf(orderTojoin)
            var oNumber = orderTojoin.number

            var orderOriginal = order.get(iOrder)
            orderOriginal.number = orderOriginal.number + oNumber
            orderOriginal.joinComments(orderTojoin)

            // Delete & insert
            order.removeAt(iOrder)
            order.add(iOrder, orderOriginal)

        } else {
            order.add(orderTojoin)
        }


    }
}