package pro.pedrosa.orderme.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pay.*
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.model.Dish
import pro.pedrosa.orderme.model.Order
import pro.pedrosa.orderme.model.Tables

class PayActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"
        val EXTRA_ERR_MSG = "EXTRA_ERR_MSG"

        //Codes
        private val REQUEST_CODE_DISHES_DETAIL = 2
        val ERR_DOWNLOAD = 5
        lateinit var dishCache: List<Dish>


        fun intent(context: Context, tableIndex: Int) : Intent {
            val intent = Intent(context, PayActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }

    }

    private var tableIndex : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        // Configuramos la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_pay)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle("Pay")
        setSupportActionBar(toolbar)

        //Parametro de la mesa a mostrar
        tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX,0)

        // Mesa
        val tableName = findViewById<TextView>(R.id.table_name)
        tableName.text = Tables[tableIndex]?.name
        // Cargamos los pedidos
        // TODO Poner precios por productos
        val list = findViewById<ListView>(R.id.dish_order_pay)
        val adapter = ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, Tables[tableIndex]?.order?.toTypedArray())
        adapter.notifyDataSetChanged()
        list.adapter = adapter

        list.setOnItemClickListener { parent, view, position, id ->
            AlertDialog.Builder(this)
                    .setMessage(Tables[tableIndex].order[position].orderPrice())
                    .setPositiveButton("OK", { dialog, _ -> })
                    .show()

        }

       // Calculamos el precio
        val totalPrice = findViewById<TextView>(R.id.table_price)
        totalPrice.text = Tables[tableIndex]?.priceOrder().toString()

        // Pay button
        val payButton = findViewById<Button>(R.id.button_pay)
        payButton.setOnClickListener {
            // Reiniciar mesa
            Tables[tableIndex]?.restart()
            setResult(Activity.RESULT_OK)
            finish()
        }

        button_cancel_pay.setOnClickListener {
            finish()
        }



    }
}
