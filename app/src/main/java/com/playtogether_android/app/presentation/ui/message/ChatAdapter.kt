package com.playtogether_android.app.presentation.ui.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemMyChatBinding
import com.playtogether_android.app.databinding.ItemOtherChatBinding
import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.model.message.ChatData.Companion.TYPE_FRIEND_MESSAGE
import com.playtogether_android.domain.model.message.ChatData.Companion.TYPE_MY_MESSAGE
import kotlin.IllegalArgumentException

class ChatAdapter : RecyclerView.Adapter<ChatViewHolder<*>>() {
    var chatList = mutableListOf<ChatData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder<*> {
        val layoutInflater=LayoutInflater.from(parent.context)
        val bindingMyChat = ItemMyChatBinding.inflate(layoutInflater, parent, false)
        val bindingOtherChat = ItemOtherChatBinding.inflate(layoutInflater, parent, false)

        return when(viewType){
            TYPE_MY_MESSAGE->MyChatViewHolder(bindingMyChat)
            TYPE_FRIEND_MESSAGE->OtherChatViewHolder(bindingOtherChat)
            else->throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder<*>, position: Int) {
        val item = chatList[position]
        when(holder){
            is MyChatViewHolder->holder.bind(item)
            is OtherChatViewHolder->holder.bind(item)
            else->throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun getItemViewType(position: Int): Int {
        if(chatList[position].messageType)
            return 0
        else
            return 1
    }

    class MyChatViewHolder(private val binding : ItemMyChatBinding) : ChatViewHolder<ChatData>(binding.root){
        override fun bind(item: ChatData) {
            binding.data=item
            /*binding.tvMychatContent.text = item.content
            binding.tvMychatTime.text = item.time
            if (!item.timeVisible)
                binding.tvMychatTime.visibility = View.GONE*/
        }
    }

    class OtherChatViewHolder(private val binding : ItemOtherChatBinding) : ChatViewHolder<ChatData>(binding.root){
        override fun bind(item:ChatData){
            binding.data=item
            /*binding.tvOthercharContent.text=item.content
            binding.tvOtherchatDate.text=item.time
            if(!item.timeVisible){
                binding.tvOtherchatDate.visibility=View.GONE
            }*/
        }
    }
}