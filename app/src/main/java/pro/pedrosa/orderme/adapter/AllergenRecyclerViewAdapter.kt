package pro.pedrosa.orderme.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.content_allergen.view.*
import pro.pedrosa.orderme.R


class AllergenRecyclerViewAdapter (val allergen: List<Int>): RecyclerView.Adapter<AllergenRecyclerViewAdapter.AllergenViewHolder>() {
    override fun onBindViewHolder(holder: AllergenViewHolder?, position: Int) {
        holder?.bindAllergen(allergen[position])
    }

    override fun getItemCount(): Int = allergen.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AllergenViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_allergen ,parent, false)
        return AllergenViewHolder(view)
    }


    inner class AllergenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindAllergen(allergen: Int) {
            // Accedemos al contexto
            val context = itemView.context
            val allergenImage = itemView.findViewById<ImageView>(R.id.allergen_image)
            //V -> M
            allergenImage.setImageResource(allergen)
        }

    }
}