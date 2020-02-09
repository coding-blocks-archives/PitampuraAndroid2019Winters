package com.puldroid.retrofit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * @author aggarwalpulkit596
 */

class UserAdapter(val list: List<User>) : RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(user: User) = with(itemView) {
        userNameTv.text = user.name
        follwersTv.text = user.login
        userImage.shapeAppearanceModel = userImage.shapeAppearanceModel.toBuilder()
            .setBottomRightCorner(CornerFamily.CUT, 100f)
            .setBottomLeftCorner(CornerFamily.CUT, 100f)

            .setTopLeftCorner(CornerFamily.ROUNDED, 100f)
            .setTopRightCorner(CornerFamily.ROUNDED, 100f)

            .build()
        Picasso.get().load(user.avatarUrl).into(userImage)
    }

}
