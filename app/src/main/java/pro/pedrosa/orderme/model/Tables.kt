package pro.pedrosa.orderme.model
import java.io.Serializable


object Tables : Serializable{


    private var tables : List<Table> =
        listOf(
        Table("Mesa 1", mutableListOf(Order(Dish(1,"Ensalada con espinacas",5, "las espinacas",1, mutableListOf()), 2, mutableListOf()))),
        Table("Mesa 2"),
        Table("Mesa 3"),
        Table("Mesa 4")
        )

    fun toArray() = tables.toTypedArray()

    operator fun get(i: Int) = tables[i]

    val count
        get() = tables.size
}