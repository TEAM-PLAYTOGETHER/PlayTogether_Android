package com.playtogether_android.app.presentation.ui.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.playtogether_android.app.databinding.ItemMyChatBinding
import com.playtogether_android.app.databinding.ItemOtherChatBinding
import com.playtogether_android.domain.model.message.ChatData

class ChatAdapter2 : ListAdapter<ChatData, ChatViewHolder<*>>(ChatComparator()) {

    private class ChatComparator:DiffUtil.ItemCallback<ChatData>(){
        override fun areItemsTheSame(oldItem: ChatData, newItem: ChatData): Boolean {
            return oldItem.messageId == newItem.messageId
        }

        override fun areContentsTheSame(oldItem: ChatData, newItem: ChatData): Boolean {
            return oldItem==newItem
        }

    }

    class MyChatViewHolder(private val binding : ItemMyChatBinding) : ChatViewHolder<ChatData>(binding.root){
        override fun bind(item: ChatData) {
            binding.data=item
        }
    }

    class OtherChatViewHolder(private val binding : ItemOtherChatBinding) : ChatViewHolder<ChatData>(binding.root){
        override fun bind(item:ChatData){
            binding.data=item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder<*> {
        val layoutInflater= LayoutInflater.from(parent.context)
        val bindingMyChat = ItemMyChatBinding.inflate(layoutInflater, parent, false)
        val bindingOtherChat = ItemOtherChatBinding.inflate(layoutInflater, parent, false)

        return when(viewType){
            ChatData.TYPE_MY_MESSAGE -> ChatAdapter.MyChatViewHolder(bindingMyChat)
            ChatData.TYPE_FRIEND_MESSAGE -> ChatAdapter.OtherChatViewHolder(bindingOtherChat)
            else->throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder<*>, position: Int) {
        val item = getItem(position)
        when(holder){
            is ChatAdapter.MyChatViewHolder ->holder.bind(item)
            is ChatAdapter.OtherChatViewHolder ->holder.bind(item)
            else->throw IllegalArgumentException()
        }
    }
}