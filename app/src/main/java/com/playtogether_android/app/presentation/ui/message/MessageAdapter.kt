package com.playtogether_android.app.presentation.ui.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemMessageRoomBinding
import com.playtogether_android.domain.model.message.MessageData

class MessageListAdapter(val itemClick: (MessageData) -> Unit) :
    ListAdapter<MessageData, MessageListAdapter.MessageListViewHolder>(MessageListComparator()) {

    class MessageListViewHolder(
        private val binding: ItemMessageRoomBinding,
        val itemClick: (MessageData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MessageData) {
            binding.data = data
            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }

    class MessageListComparator() : DiffUtil.ItemCallback<MessageData>() {
        override fun areItemsTheSame(oldItem: MessageData, newItem: MessageData): Boolean {
            return oldItem.roomId == newItem.roomId
        }

        override fun areContentsTheSame(oldItem: MessageData, newItem: MessageData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val binding =
            ItemMessageRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageListViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        return holder.onBind(getItem(position))
    }
}

/*
class MessageListAdapter(val itemClick: (MessageData) -> Unit) :
    RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder>() {

    val messageList = mutableListOf<MessageData>()

    class MessageListViewHolder(
        private val binding: ItemMessageRoomBinding,
        val itemClick: (MessageData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MessageData) {
            binding.data = data
            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val binding =
            ItemMessageRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageListViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        return holder.onBind(messageList[position])
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}*/
