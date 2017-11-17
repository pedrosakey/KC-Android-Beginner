package pro.pedrosa.orderme.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.model.Table
import pro.pedrosa.orderme.model.Tables


class TableListFragment:Fragment() {

     companion object {

         private val ARG_TABLES = "ARG_TABLES"

         fun newInstance(tables: Tables):TableListFragment {
             val fragment = TableListFragment()
             val args = Bundle()
             args.putSerializable(ARG_TABLES, tables)
             fragment.arguments = args
             return fragment
         }
     }

     lateinit var root: View
     private var tables: Tables? = null
     private var onTableSelectedListener: OnTableSelectedListener? = null



    override fun onCreate(savedInstanceState:Bundle?) {
    super.onCreate(savedInstanceState)
        if(arguments!=null) {
            tables = arguments.getSerializable(ARG_TABLES) as? Tables
        }
    }

     override fun onCreateView(inflater:LayoutInflater?, container:ViewGroup?,
    savedInstanceState:Bundle?):View? {
            if(inflater != null){
                root = inflater.inflate(R.layout.fragment_table_list,container,false)
                val list = root.findViewById<ListView>(R.id.table_list)
                val adapter = ArrayAdapter<Table>(activity, android.R.layout.simple_list_item_1, tables?.toArray())
                list.adapter = adapter

                // Nos enteramos de que se ha pulsado un elemento de la lista así:
                list.setOnItemClickListener { parent, view, position, id ->
                    // Aviso al listener
                    onTableSelectedListener?.onTableSelected(tables?.get(position), position)
                }
            }

                return root
        }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)
    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onTableSelectedListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnTableSelectedListener) {
            onTableSelectedListener = listener
        }
    }


    interface OnTableSelectedListener {
        fun onTableSelected(table: Table?, position: Int)
    }


}