package pro.pedrosa.orderme.adapter

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import pro.pedrosa.orderme.model.Dish
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.intellij.lang.annotations.JdkConstants
import pro.pedrosa.orderme.R


class DishRecyclerViewAdapter (val dish: List<Dish>) : RecyclerView.Adapter<DishRecyclerViewAdapter.DishViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_dish, parent, false)

        // Le decimos a la vista de este ViewHolder a quién llamar cuando se le pulse
        view.setOnClickListener(onClickListener)

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
        val allergens = itemView.findViewById<RecyclerView>(R.id.dish_allergens)

        fun bindDish( dish: Dish) {
            // Contexto
            val context = itemView.context


            // Actualizamos la vista con el modelo
            dishImage.setImageResource(dish.image)
            name.text = dish.name
            price.text = dish.price.toString()

            //Horizontal
            allergens.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            allergens.itemAnimator = DefaultItemAnimator()

            allergens.adapter = AllergenRecyclerViewAdapter(dish.allergensToResources())

        }


    }
}