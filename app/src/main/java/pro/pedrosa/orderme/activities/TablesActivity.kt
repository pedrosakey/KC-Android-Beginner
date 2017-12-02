package pro.pedrosa.orderme.activities
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.fragments.TableListFragment
import pro.pedrosa.orderme.fragments.TablePagerFragment
import pro.pedrosa.orderme.model.Dish
import pro.pedrosa.orderme.model.Table
import pro.pedrosa.orderme.model.Tables

class TablesActivity : AppCompatActivity(), TableListFragment.OnTableSelectedListener{



    override fun onCreate(savedInstanceState: Bundle?) {

        var bundle : Bundle? = null
        super.onCreate(bundle)

        Log.v("TAG", "TablesActivity me creo")

        setContentView(R.layout.activity_tables)

            //Comprobamos en la interfaz que tenemos un framelayout city_list_fragment
            if (findViewById<View>(R.id.table_list_fragment) != null) {
                // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
                if (fragmentManager.findFragmentById(R.id.table_list_fragment) == null) {
                    val fragment = TableListFragment.newInstance()
                    fragmentManager.beginTransaction()
                            .disallowAddToBackStack()
                            .add(R.id.table_list_fragment, fragment)
                            .commit()
                }
            }

            if (findViewById<View>(R.id.fragment_table_pager) != null) {
                if (fragmentManager.findFragmentById(R.id.fragment_table_pager) == null) {
                    val fragment = TablePagerFragment.newInstance(0)
                    fragmentManager.beginTransaction()
                            .disallowAddToBackStack()
                            .add(R.id.fragment_table_pager, fragment)
                            .commit()
                }
            }

    }

    override fun onTableSelected(table: Table?, position: Int) {
        val tablePagerFragment = fragmentManager.findFragmentById(R.id.fragment_table_pager) as? TablePagerFragment
        if (tablePagerFragment == null) {
        startActivity(TablePagerActivity.intent(this, position))
        } else {
            tablePagerFragment.moveToTable(position)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("TAG", "TablesActivity me destruyo")
    }


}
