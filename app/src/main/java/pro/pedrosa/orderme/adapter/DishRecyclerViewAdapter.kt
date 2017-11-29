package pro.pedrosa.orderme.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import pro.pedrosa.orderme.model.Dish
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import pro.pedrosa.orderme.R

class DishRecyclerViewAdapter (val dish: List<Dish>) : RecyclerView.Adapter<DishRecyclerViewAdapter.DishViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder?, position: Int) {
        holder?.bindDish(dish[position])
    }

    override fun getItemCount(): Int = dish.size

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.dish_name)
        val dishImage = itemView.findViewById<ImageView>(R.id.dish_image)
        val price = itemView.findViewById<TextView>(R.id.dish_price)
        val allergens = itemView.findViewById<TextView>(R.id.dish_allergens)

        fun bindDish( dish: Dish) {

            // Actualizamos la vista con el modelo
            dishImage.setImageResource(dish.image)
            name.text = dish.name
            price.text = dish.price.toString()
            allergens.text = dish.allergens.toString()

        }
    }
}