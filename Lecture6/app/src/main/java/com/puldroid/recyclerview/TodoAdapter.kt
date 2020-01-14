package com.puldroid.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movies.view.*

class TodoAdapter(val todo: ArrayList<String>) :
    RecyclerView.Adapter<TodoAdapter.ItemVieHolder>() {
    var onItemClickListener: MovieItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVieHolder {
        return ItemVieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.simple_expandable_list_item_1,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = todo.size

    override fun onBindViewHolder(holder: ItemVieHolder, position: Int) {
        holder.bind(todo[position])

    }

    inner class ItemVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: String) {
            itemView.apply {
                text1.text = todo
            }
        }

    }
}