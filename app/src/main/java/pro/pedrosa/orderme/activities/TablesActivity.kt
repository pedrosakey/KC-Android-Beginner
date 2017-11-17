package pro.pedrosa.orderme.activities
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.fragments.TableListFragment
import pro.pedrosa.orderme.model.Dish
import pro.pedrosa.orderme.model.Table
import pro.pedrosa.orderme.model.Tables

class TablesActivity : AppCompatActivity(), TableListFragment.OnTableSelectedListener{

    var tables = Tables()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
        if (fragmentManager.findFragmentById(R.id.table_list_fragment) == null) {
            val fragment = TableListFragment.newInstance(tables)
            fragmentManager.beginTransaction()
                    .add(R.id.table_list_fragment,fragment)
                    .commit()
        }
    }

    override fun onTableSelected(table: Table?, position: Int) {
        startActivity(TablePagerActivity.intent(this, position))
    }



    // Descargamos datos de los platos
    private fun downloadDishes() : List<Dish> {

        val dishes : MutableList<Dish> = mutableListOf()

        val dish1 = Dish("Arroz con bogavante")
        val dish2 = Dish("Solomillo a la pimienta")

        dishes.add(dish1)
        dishes.add(dish2)

        return dishes
    }
}
