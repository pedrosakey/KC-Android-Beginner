package pro.pedrosa.orderme.fragments

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.activities.TablePagerActivity
import pro.pedrosa.orderme.model.Dish
import pro.pedrosa.orderme.model.Order
import pro.pedrosa.orderme.model.Table

class TableFragment : Fragment() {

   companion object {
        private val ARG_TABLE = "ARG_TABLE"

        fun newInstance(table: Table) : TableFragment {
            val fragment = TableFragment()

            val arguments = Bundle()
            arguments.putSerializable(ARG_TABLE, table)
            fragment.arguments = arguments
            return fragment
        }
    }

    var onClickAddButtonListenener: OnClickAddButtonListenener? = null

    lateinit var root: View
    var table : Table? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if(arguments!=null) {
            table = arguments.getSerializable(ARG_TABLE) as? Table
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (inflater != null) {

            root = inflater.inflate(R.layout.fragment_table, container, false)
            val tableName = root.findViewById<TextView>(R.id.table_name)
            tableName.text = table?.name

           this.setTables()

            // Nos han pulsado el boton
            val button = root.findViewById<Button>(R.id.button)
            button.setOnClickListener {
                onClickAddButtonListenener?.onClickAddButton(table)
//                val adapter = ArrayAdapter<Order>(activity, android.R.layout.simple_list_item_1, table?.order?.toTypedArray())
//                list.adapter = adapter
            }



        }



        return root
    }

    fun setTables() {
        val list = root.findViewById<ListView>(R.id.dishes_table_fragment)
        val adapter = ArrayAdapter<Order>(activity, android.R.layout.simple_list_item_1, table?.order?.toTypedArray())
        adapter.notifyDataSetChanged()
        list.adapter = adapter

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonOnAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonOnAttach(activity)
    }

    fun commonOnAttach(context: Context?) {
        // Aquí nos llaman cuando el fragment "se engancha" a la actividad, y por tanto ya pertence a ella
        // Lo que vamos a hacer es quedarnos con la referencia a esa actividad para cuando tengamos que avisarle de "cosas"
        if (context is OnClickAddButtonListenener) {
            onClickAddButtonListenener = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        // Si la actividad se "desengancha" de este fragment ya no tiene sentido guardar una referencia a ella, ya no le vamos
        // a avisar de nada, lo ponemos a null
        onClickAddButtonListenener = null
    }

    interface OnClickAddButtonListenener {
        fun onClickAddButton(table: Table?)
    }







}
