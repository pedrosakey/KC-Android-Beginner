package pro.pedrosa.orderme.fragments

import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import pro.pedrosa.orderme.R
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

           this.setTables()


        }

        return root
    }

    fun setTables() {
        val list = root.findViewById<ListView>(R.id.dishes_table_fragment)
        val adapter = ArrayAdapter<Order>(activity, android.R.layout.simple_list_item_1, table?.order?.toTypedArray())
        adapter.notifyDataSetChanged()
        list.adapter = adapter

        // Nos enteramos de que se ha pulsado un elemento de la lista así:
        list.setOnItemClickListener { parent, view, position, id ->
            // Aviso al listener
            val dishClientOrder : Order? = table?.order?.get(position)


            val arrayAdapter = ArrayAdapter<String>(root.context, android.R.layout.simple_list_item_1, dishClientOrder?.dishComments)


                    if (dishClientOrder!!.dishComments!!.isEmpty()) {
                        AlertDialog.Builder(root.context)
                                .setTitle("Client Comments")
                                .setMessage("No comments")
                                .setPositiveButton("OK", { dialog, _ -> })
                                .show()
                    } else {
                        AlertDialog.Builder(root.context)
                                .setTitle("Client Comments")
                                .setAdapter(arrayAdapter) { dialog, _ -> }
                                .setPositiveButton("OK", { dialog, _ -> })
                                .show()
                    }

        }

    }

}
