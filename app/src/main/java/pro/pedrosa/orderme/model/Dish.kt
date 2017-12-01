package pro.pedrosa.orderme.model

import android.widget.ImageView
import pro.pedrosa.orderme.R
import java.io.Serializable

data class Dish (var id:Int?, var name: String, var price: Int?, var description: String?, var image: Int, var allergens: List<String>?): Serializable {
    constructor(name: String) : this(null, name, null, null, 0, null)

    override fun toString() = name


    fun allergensToResources(): List<Int> {

        // allergens: List<String>? ->  List<Int>

        // Recorro la lista y convierto y recurso


           val allergensToResources : List<Int>? = allergens?.map{
               when (it) {
                   "celery01" -> R.drawable.celery01
                   "eggs02"   -> R.drawable.eggs02
                   "fish03"   -> R.drawable.fish03
                   "milk04"   -> R.drawable.milk04
                   "nuts05"   -> R.drawable.nuts05
                   "seeds06"  -> R.drawable.seeds06
                   "soya07"   -> R.drawable.soya07
                   else -> R.drawable.celery01
               }
           }


        return allergensToResources ?: listOf()
        /* var listAllergens = listOf(R.drawable.nuts05,R.drawable.milk04)
       return listAllergens

   }*/
    }
}