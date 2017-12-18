package pro.pedrosa.orderme.activities

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.*
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.fragments.TableFragment
import pro.pedrosa.orderme.fragments.TableListFragment
import pro.pedrosa.orderme.fragments.TablePagerFragment
import pro.pedrosa.orderme.model.Dish
import pro.pedrosa.orderme.model.Order
import pro.pedrosa.orderme.model.Table
import pro.pedrosa.orderme.model.Tables

class TablePagerActivity : AppCompatActivity(), TableFragment.OnClickAddButtonListenener{


    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"
        private val REQUEST_CODE_DISHES = 1


        fun intent(context: Context, tableIndex: Int) : Intent {
            val intent = Intent(context, TablePagerActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }

    }

    var tableIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_pager)

        // Configuramos la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)


        //Parametro de la mesa a mostrar
        tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX,0)

        // Cargamos el fragmento

        if(fragmentManager.findFragmentById(R.id.fragment_table_pager) == null) {
            val fragment = TablePagerFragment.newInstance(tableIndex)
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_table_pager,fragment)
                    .commit()

        }


    }

    override fun onClickAddButton(table: Table? ) {
        //Obtengo la referencia la fragmento y le paso la posicion
        val fragmentPager = fragmentManager.findFragmentById(R.id.fragment_table_pager) as? TablePagerFragment
        if (fragmentPager != null) {
            startActivityForResult(DishesActivity.intent(this,fragmentPager.getPosition()), REQUEST_CODE_DISHES)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Obtengo la referencia la fragmento y le paso la posicion
        val fragmentPager = fragmentManager.findFragmentById(R.id.fragment_table_pager) as? TablePagerFragment
        fragmentPager?.updateTableFragment()



    }

    override fun onPause() {
        super.onPause()
        Log.v("TAG", "TablePagerActivity: onPause")

    }


}
