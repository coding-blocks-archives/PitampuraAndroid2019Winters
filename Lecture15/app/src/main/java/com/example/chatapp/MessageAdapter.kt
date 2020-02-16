package com.example.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_message.view.*
import java.util.*

class MessageAdapter(val data:List<Message>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) =
        holder.bind(data[position])


    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Message) = with(itemView) {
            nameTv.text = item.name
            msgTv.text = item.msg
            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}