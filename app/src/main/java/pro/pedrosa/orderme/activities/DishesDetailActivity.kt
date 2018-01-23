package pro.pedrosa.orderme.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.model.Dish
import pro.pedrosa.orderme.model.Dishes
import pro.pedrosa.orderme.model.Order
import pro.pedrosa.orderme.model.Tables


class DishesDetailActivity : AppCompatActivity() {

    companion object {
        val DISH_ID = "DISH_ID"
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
    lateinit var dish : Dish

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes_detail)

        // Configuramos la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_dishes_detail)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle(getString(R.string.toolbar_dishes_activity_title))
        setSupportActionBar(toolbar)

        // Mesa
        tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX,0)

        // Mostramos los campos segun la posición
        val dishId = intent.getIntExtra(DISH_ID, 0)
        dish = Dishes.dishes[dishId]

        val dishImage = findViewById<ImageView>(R.id.dish_image)
        val dishName = findViewById<TextView>(R.id.dish_name)
        val dishPrice = findViewById<TextView>(R.id.dish_price)
        val dishDescription = findViewById<TextView>(R.id.dish_description)

        dishImage.setImageResource(dish.image)
        dishName.text = dish.name
        dishPrice.text = dish.price.toString()
        dishDescription.text = dish.description

        // Button Cancel
        val buttonCancel = findViewById<Button>(R.id.button_cancel)
        buttonCancel.setOnClickListener { finish() }

        // Button Save
        val buttonSave = findViewById<Button>(R.id.button_save)
        buttonSave.setOnClickListener {
            // Add Dish

            // Si queremos añadir mas productos o terminar
            AlertDialog.Builder(this@DishesDetailActivity)
                    .setMessage("Add more dishes?")
                    .setPositiveButton("YES", { dialog, _ ->
                        // dialog.dismiss()
                        // Add more
                        // Join orders
                        addOrder()
                        setResult(ADD_MORE)
                        finish()
                    })
                    .setNegativeButton("NO", { _, _ ->
                        //Join orders
                         addOrder()
                         setResult(NO_ADD_MORE)
                         finish()

                     }).show()
        }
    }
    fun addOrder() {
        // Leer del input
        val editText = findViewById<EditText>(R.id.edit_text)
        val comment = editText.text.toString()
        val orderToJoin = Order(dish,1)
        if(comment.length > 0) {
            orderToJoin.addComment(comment)
        }
        Tables[tableIndex]?.joinOrder(orderToJoin)
    }
}