package pro.pedrosa.orderme.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.TextView
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.adapter.DishRecyclerViewAdapter
import pro.pedrosa.orderme.fragments.TableFragment
import pro.pedrosa.orderme.model.Dish
import pro.pedrosa.orderme.model.Table

class DishesActivity : AppCompatActivity() {



    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int) : Intent {
            val intent = Intent(context, DishesActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }

    }

    lateinit var dishList: RecyclerView

    var dish : List<Dish> = downloadDishes()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes)

        // Configuramos la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_dishes)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle("Add Dishes")
        setSupportActionBar(toolbar)

        // Accdemos al RecyclerView
        dishList = findViewById(R.id.dishes_recyclerview)

        // 2) Le decimos cómo debe visualizarse el RecyclerView (su LayoutManager)
        dishList.layoutManager = LinearLayoutManager(this)

        // 3) Le decimos cómo debe animarse el RecyclerView (su ItemAnimator)
        dishList.itemAnimator = DefaultItemAnimator()

        // 4) Por último, un RecylerView necesita un adapter
        dishList.adapter = DishRecyclerViewAdapter(dish)




    }

    // Descargamos datos de los platos
    private fun downloadDishes() : List<Dish> {

        val dishes : MutableList<Dish> = mutableListOf()

        val dish1 = Dish(0,"Espinacas con queso de cabra",50,R.drawable.ensalada_espinacas_1,listOf("milk", "nuts"))
        val dish2 = Dish(0,"Espinacas con queso de cabra",50,R.drawable.ensalada_espinacas_1,listOf("milk", "nuts"))
        val dish3 = Dish(0,"Espinacas con queso de cabra",50,R.drawable.ensalada_espinacas_1,listOf("milk", "nuts"))
        val dish4 = Dish(0,"Espinacas con queso de cabra",50,R.drawable.ensalada_espinacas_1,listOf("milk", "nuts"))
        val dish5 = Dish(0,"Espinacas con queso de cabra",50,R.drawable.ensalada_espinacas_1,listOf("milk", "nuts"))

        dishes.add(dish1)
        dishes.add(dish2)
        dishes.add(dish3)
        dishes.add(dish4)
        dishes.add(dish5)

        return dishes
    }

}
