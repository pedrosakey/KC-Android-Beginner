package pro.pedrosa.orderme.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.*
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.model.Dish
import pro.pedrosa.orderme.model.Dishes
import pro.pedrosa.orderme.model.Order
import pro.pedrosa.orderme.model.Tables

class DishesDetailActivity : AppCompatActivity() {

    companion object {
        val DISH_ID = "DISH_ID"
        val EXTRA_RESULT_ADDED = "EXTRA_RESULT_ADDED"
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        //Codes
        val ADD_MORE = 3
        val NO_ADD_MORE = 4



        fun intent(context: Context, dishId: Int, tableIndex: Int) : Intent {
            val intent = Intent(context, DishesDetailActivity::class.java)
            intent.putExtra(DISH_ID, dishId)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }

    }

    var tableIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes_detail)

        // Configuramos la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_dishes_detail)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle("Dish to Order")
        setSupportActionBar(toolbar)

        // Mesa
        tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX,0)
        
        // Mostramos los campos segun la posición
        var dishId = intent.getIntExtra(DISH_ID, 0)
        var dish = Dishes.dishes[dishId]

        var dishImage = findViewById<ImageView>(R.id.dish_image)
        var dishName = findViewById<TextView>(R.id.dish_name)
        var dishPrice = findViewById<TextView>(R.id.dish_price)
        var dishDescription = findViewById<TextView>(R.id.dish_description)

        dishImage.setImageResource(dish.image)
        dishName.text = dish.name
        dishPrice.text = dish.price.toString()
        dishDescription.text = dish.description

        // Button Cancel
        var buttonCancel = findViewById<Button>(R.id.button_cancel)
        buttonCancel.setOnClickListener { finish() }

        // Button Save
        var buttonSave = findViewById<Button>(R.id.button_save)
        buttonSave.setOnClickListener {
            // Add Dish

            // Si queremos añadir mas productos o terminar
            AlertDialog.Builder(this@DishesDetailActivity)
                    .setMessage("Add more dishes?")
                    .setPositiveButton("YES", { dialog, _ ->
                       // dialog.dismiss()
                        // Add more
                        //Join orders
                        var orderToJoin = mutableListOf(Order(dish,1))
                        Tables[tableIndex]?.joinOrder(orderToJoin)

                        setResult(ADD_MORE)
                        finish()
                    })
                    .setNegativeButton("NO", { _, _ ->
                        // StartAcivityForResult volver a las mesas

                        //Join orders
                         var orderToJoin = mutableListOf(Order(dish,1))
                         Tables[tableIndex]?.joinOrder(orderToJoin)

                         val resultIntent = Intent()
                         resultIntent.putExtra(EXTRA_RESULT_ADDED, "Dish Added")

                         // Indicamos que resultIntent es lo que recibirá la actividad anterior
                         setResult(NO_ADD_MORE, resultIntent)

                         // Finalizamos esta actividad
                         finish()

                     }).show()


        }
    }
}