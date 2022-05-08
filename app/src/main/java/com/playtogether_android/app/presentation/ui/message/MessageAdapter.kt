package com.playtogether_android.app.presentation.ui.message

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemMessageRoomBinding
import com.playtogether_android.domain.model.message.MessageListData

class MessageListAdapter(val itemClick: (MessageListData) -> Unit) : RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder>() {

    val messageList = mutableListOf<MessageListData>()

    class MessageListViewHolder(private val binding : ItemMessageRoomBinding, val itemClick: (MessageListData) -> Unit) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data:MessageListData){
            binding.data=data
            itemView.setOnClickListener{
                itemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val binding = ItemMessageRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageListViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        return holder.onBind(messageList[position])
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}