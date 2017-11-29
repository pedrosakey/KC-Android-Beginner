package pro.pedrosa.orderme.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pro.pedrosa.orderme.R
import pro.pedrosa.orderme.fragments.TableFragment
import pro.pedrosa.orderme.model.Table

class DishesActivity : AppCompatActivity() {



    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int) : Intent {
            val intent = Intent(context, DishesActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes)

        val text = findViewById<TextView>(R.id.table_number)
        text.text = intent.getIntExtra("EXTRA_TABLE_INDEX", 0).toString()


    }
}
