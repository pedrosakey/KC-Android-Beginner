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
        val ADD_MORE = 3
        val NO_ADD_MORE = 4
        val EXTRA_RESULT_ADDED = "EXTRA_RESULT_ADDED"





        fun intent(context: Context, dishId: Int) : Intent {
            val intent = Intent(context, DishesDetailActivity::class.java)
            intent.putExtra(DISH_ID, dishId)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes_detail)

        // Configuramos la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_dishes_detail)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle("Dish to Order")
        setSupportActionBar(toolbar)

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
                        setResult(ADD_MORE)
                        finish()
                    })
                    .setNegativeButton("NO", { _, _ ->
                        // StartAcivityForResult volver a las mesas
                        /* var tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX,0)*/

                         //Join orders
                         var orderToJoin = mutableListOf(Order(dish,1))
                         Tables[0]?.joinOrder(orderToJoin)

                         val resultIntent = Intent()
                         resultIntent.putExtra(EXTRA_RESULT_ADDED, "Añadimos dish")

                         // Indicamos que resultIntent es lo que recibirá la actividad anterior
                         setResult(NO_ADD_MORE, resultIntent)

                         // Finalizamos esta actividad
                         finish()

                     }).show()


        }
    }
}