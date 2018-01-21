package pro.pedrosa.orderme.model
import java.io.Serializable


object Tables : Serializable{


    private var tables : List<Table> =
        listOf(
        Table("Mesa 1", mutableListOf(Order(Dish("Ensalada con espinacas"),2, mutableListOf()))),
        Table("Mesa 2",  mutableListOf(Order(Dish("Ración de rabas"),2, mutableListOf()))),
        Table("Mesa 3",  mutableListOf(Order(Dish("Ensalada mixta"),2, mutableListOf()))),
        Table("Mesa 4")
        )

    fun toArray() = tables.toTypedArray()

    operator fun get(i: Int) = tables[i]

    val count
        get() = tables.size
}