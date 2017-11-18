package pro.pedrosa.orderme.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.model.Dish
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

            val list = root.findViewById<ListView>(R.id.dishes_table_fragment)
            val adapter = ArrayAdapter<Dish>(activity, android.R.layout.simple_list_item_1, table?.dishes?.toTypedArray())
            list.adapter = adapter
        }
        return root
    }





}
