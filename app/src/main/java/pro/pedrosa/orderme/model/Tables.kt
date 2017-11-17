package pro.pedrosa.orderme.model
import java.io.Serializable


class Tables : Serializable{


    private var tables : List<Table> =
        listOf(
        Table("Mesa 1", mutableListOf(Dish("Arroz con bogavante"), Dish("Ensalada"))),
        Table("Mesa 2", mutableListOf(Dish("Mero"))),
        Table("Mesa 3", mutableListOf(Dish("Sushi"))),
        Table("Mesa 4")
        )

    fun toArray() = tables.toTypedArray()

    //    fun getCity(index: Int) = cities[index]
    operator fun get(i: Int) = tables[i]

    val count
        get() = tables.size
}