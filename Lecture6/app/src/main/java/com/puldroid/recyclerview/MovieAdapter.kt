package com.puldroid.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movies.view.*

/**
 * @author aggarwalpulkit596
 */

class MovieAdapter(val movies: ArrayList<Movies>) :
    RecyclerView.Adapter<ItemVieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVieHolder {
        return ItemVieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movies,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ItemVieHolder, position: Int) {
        holder.bind(movies[position])
    }
}

class ItemVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movies) {
        itemView.apply {
            text1.text = movie.name + "( " + movie.year + " )"
            textView2.text = movie.year
            imageView.setImageResource(movie.image)
        }

    }
}