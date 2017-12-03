package pro.pedrosa.orderme.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewSwitcher
import kotlinx.android.synthetic.main.activity_dishes.*
import org.json.JSONObject
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.adapter.DishRecyclerViewAdapter
import pro.pedrosa.orderme.fragments.TableFragment
import pro.pedrosa.orderme.model.Dish
import pro.pedrosa.orderme.model.Table
import java.net.URL
import java.util.*
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import pro.pedrosa.orderme.model.Order
import pro.pedrosa.orderme.model.Tables
import kotlin.collections.LinkedHashMap


class DishesActivity : AppCompatActivity() {

    enum class VIEW_INDEX(val index: Int) {
        LOADING(0),
        DISH(1)
    }

    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"
        val EXTRA_RESULT = "EXTRA_RESULT"

        fun intent(context: Context, tableIndex: Int) : Intent {
            val intent = Intent(context, DishesActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }

    }

    lateinit var dishList: RecyclerView
    lateinit var viewSwitcher: ViewSwitcher


    var dishSelected : List<Dish>? = null
    var dishes : List<Dish>? = null
    set(value){
        field = value
        if(value!=null) {
            //Creamos el adapter
            val adapter = DishRecyclerViewAdapter(value)

            // Hacemos click en un elemento
            adapter.onClickListener = View.OnClickListener { view ->
                // Averiguamos qué índice del ViewHolder es el que ha provocado esta llamada
                val position = dishes_recyclerview.getChildAdapterPosition(view)

                /* Mostramos un mensajillo con el número pulsado
                Toast.makeText(this, "Han pulsado la posicion ${position}", Toast.LENGTH_SHORT)
                        .show()*/

                // StartAcivityForResult cuando click en un dish
                var tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX,0)

                var orderToJoin = mutableListOf(Order(Dish("Ensalada con espinacas"),2))
                Tables[tableIndex]?.joinOrder(orderToJoin)

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_RESULT, "Añadimos dish")

                // Indicamos que resultIntent es lo que recibirá la actividad anterior
                setResult(Activity.RESULT_OK, resultIntent)

                // Finalizamos esta actividad
                finish()

            }

            // Le decimos su adapter
            dishes_recyclerview.adapter = adapter

           // dishList.adapter = DishRecyclerViewAdapter(value)
            viewSwitcher.displayedChild = VIEW_INDEX.DISH.index

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes)

       //ViewSwitcher
        viewSwitcher = findViewById(R.id.view_switcher)
        viewSwitcher.setInAnimation(this, android.R.anim.fade_in)
        viewSwitcher.setOutAnimation(this, android.R.anim.fade_out)

        // Configuramos la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_dishes)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle("Add Dishes")
        setSupportActionBar(toolbar)

        // Accedemos al RecyclerView
        dishList = findViewById(R.id.dishes_recyclerview)

        // 2) Le decimos cómo debe visualizarse el RecyclerView (su LayoutManager)
        dishList.layoutManager = LinearLayoutManager(this)

        // 3) Le decimos cómo debe animarse el RecyclerView (su ItemAnimator)
        dishList.itemAnimator = DefaultItemAnimator()

        // 4) Por último, un RecylerView necesita un adapter
        // set(value)

        // Le decimos cuando pulsamos un elemento del adapter





        updateDishes()

    }

    private fun updateDishes() {
        viewSwitcher.displayedChild = VIEW_INDEX.LOADING.index
        async(UI) {
            val newDishes: Deferred<List<Dish>?> = bg {downloadDishes()}

            val downloadedDishes = newDishes.await()

            if(downloadedDishes!= null) {
                dishes = downloadedDishes
            } else {
                // Ha habido algún tipo de error, se lo decimos al usuario con un diálogo
                AlertDialog.Builder(this@DishesActivity)
                        .setTitle("Error")
                        .setMessage("No me pude descargar la información")
                        .setPositiveButton("Reintentar", { dialog, _ ->
                            dialog.dismiss()
                            updateDishes()
                        })
                        .setNegativeButton("Salir", { _, _ -> finish() })
                        .show()
            }
        }
    }



    // Descargamos datos de los platos
    private fun downloadDishes() : List<Dish>? {
        try {
        // Simulamos un retardo
        Thread.sleep(1000)

            val url = URL("http://www.mocky.io/v2/5a22df4d2f0000be0d5ec661")
            val jsonString = Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next()

            // Analizamos los datos que nos acabamos de descargar
            val jsonRoot = JSONObject(jsonString)
            val list = jsonRoot.getJSONArray("list")

            // Creamos la lista a devolver
            val dishList = mutableListOf<Dish>()

            for (dishIndex in 0 until list.length()) {
                val dish = list.getJSONObject(dishIndex)
                val id = dish.getInt("id").toInt()
                val name = dish.getString("name").toString()
                val description = dish.get("description").toString()
                val price = dish.getInt("price").toInt()
                val image = dish.getString("image").toString()

                val allergens = dish.getJSONArray("allergens")

                var allergenToList = mutableListOf<String>()

                for( i in 0..(allergens.length() - 1) ) {
                    val a = allergens.get(i)
                    allergenToList.add(a as String)
                }

                val imageToInt = when (id) {
                    1 -> R.drawable.ensalada_espinacas_1
                    2 -> R.drawable.ensalada_mixta_2
                    3 -> R.drawable.ensalada_palmitos_3
                    4 -> R.drawable.esparrago_navarra_4
                    5 -> R.drawable.revuelto_ajetes_5
                    6 -> R.drawable.idiazabal_6
                    7 -> R.drawable.bravas_7
                    8 -> R.drawable.rabas_8
                    9 -> R.drawable.fritos_variados_9
                    10 -> R.drawable.sopa_pollo_10
                    else -> R.drawable.ensalada_espinacas_1
                }

                dishList.add(Dish(id, name, price, description, imageToInt, allergenToList))

            }
            return dishList
        }catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

}
