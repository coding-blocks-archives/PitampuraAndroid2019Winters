package com.puldroid.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.RepoViewHolder>() {

    private var data: List<Repositories> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repo, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<Repositories>) {
        this.data = data
        notifyDataSetChanged()
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Repositories) = with(itemView) {
            // TODO: Bind the data with View
            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}