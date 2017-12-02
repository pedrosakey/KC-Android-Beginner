package pro.pedrosa.orderme.model

data class Order (val dish: Dish, var number: Int) {
    override fun toString(): String = dish.name + " X " + number

    override fun equals(other: Any?): Boolean {
        if(other is Order) {
            return other.dish.name == dish.name
        }
        return false
    }
}