package com.playtogether_android.app.presentation.ui.message.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemChatDateBinding
import com.playtogether_android.app.databinding.ItemChatLoadingBinding
import com.playtogether_android.app.databinding.ItemMyChatBinding
import com.playtogether_android.app.databinding.ItemOtherChatBinding
import com.playtogether_android.app.presentation.ui.message.adapter.ChatData2.Companion.MY_CHAT
import com.playtogether_android.app.presentation.ui.message.adapter.ChatData2.Companion.OTHER_CHAT
import com.playtogether_android.app.presentation.ui.message.adapter.ChattingData.Companion.CHAT
import com.playtogether_android.app.presentation.ui.message.adapter.ChattingData.Companion.DATE
import com.playtogether_android.app.presentation.ui.message.adapter.ChattingData.Companion.LOADING

class ChattingAdapter : RecyclerView.Adapter<ChatViewHolder2>() {
    val chatList = mutableListOf<ChattingData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder2 {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            MY_CHAT -> {
                val binding = ItemMyChatBinding.inflate(layoutInflater)
                return MyChatViewHolder(binding)
            }
            OTHER_CHAT -> {
                val binding = ItemOtherChatBinding.inflate(layoutInflater)
                return OtherChatViewHolder(binding)
            }
            DATE -> {
                val binding = ItemChatDateBinding.inflate(layoutInflater)
                return DateViewHolder(binding)
            }
            LOADING -> {
                val binding = ItemChatLoadingBinding.inflate(layoutInflater)
                return LoadingViewHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder2, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount(): Int = chatList.size

    override fun getItemViewType(position: Int): Int {
        val type = chatList[position].viewType

        return if (type == CHAT) {
            val data = chatList[position] as ChatData2
            data.getChatViewType()
        } else type
    }
}