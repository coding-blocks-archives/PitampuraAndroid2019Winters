package com.puldroid.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puldroid.mvvm.R
import com.puldroid.mvvm.data.models.Repositories
import com.puldroid.mvvm.utils.SpannableLanguage
import com.puldroid.mvvm.utils.SpannableRepoName
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo.view.*
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
            tvRepoName.text =
                SpannableRepoName(
                    itemView.context,
                    "${item.author} / ${item.name}"
                )
            item.language?.let {
                itemView.tvLanguage.text =
                    SpannableLanguage(
                        it,
                        item.languageColor
                    )
            }
            Picasso.get().load(item.avatar).into(imgAvatar)
            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}