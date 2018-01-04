package pro.pedrosa.orderme.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.model.Dishes
import pro.pedrosa.orderme.model.Tables

class DishesDetailActivity : AppCompatActivity() {

    companion object {
        val DISH_ID = "DISH_ID"


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

        // Eliminamos foco del edittext
        val editText = findViewById<EditText>(R.id.edit_text)

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


    }
}
