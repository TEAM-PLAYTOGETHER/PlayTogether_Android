package com.playtogether_android.app.presentation.ui.message.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemChatDateBinding
import com.playtogether_android.app.databinding.ItemChatLoadingBinding
import com.playtogether_android.app.databinding.ItemMyChatBinding
import com.playtogether_android.app.databinding.ItemOtherChatBinding
import com.playtogether_android.app.util.ChatDateStringReformat
import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.model.message.ChatData.Companion.MY_CHAT
import com.playtogether_android.domain.model.message.ChatData.Companion.OTHER_CHAT
import com.playtogether_android.domain.model.message.ChattingData
import com.playtogether_android.domain.model.message.ChattingData.Companion.CHAT
import com.playtogether_android.domain.model.message.ChattingData.Companion.DATE
import com.playtogether_android.domain.model.message.ChattingData.Companion.LOADING
import com.playtogether_android.domain.model.message.DateData
import com.playtogether_android.domain.model.message.LoadingData

class ChattingAdapter : RecyclerView.Adapter<ChatViewHolder2>() {
    private val chatList = mutableListOf<ChattingData>()
    private val chatFormatter = ChatDateStringReformat()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder2 {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            MY_CHAT -> {
                val binding = ItemMyChatBinding.inflate(layoutInflater, parent, false)
                return MyChatViewHolder(binding)
            }
            OTHER_CHAT -> {
                val binding = ItemOtherChatBinding.inflate(layoutInflater, parent, false)
                return OtherChatViewHolder(binding)
            }
            DATE -> {
                val binding = ItemChatDateBinding.inflate(layoutInflater, parent, false)
                return DateViewHolder(binding)
            }
            LOADING -> {
                val binding = ItemChatLoadingBinding.inflate(layoutInflater, parent, false)
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
            val data = chatList[position] as ChatData
            data.getChatViewType()
        } else type
    }

    fun initList(list: List<ChattingData>) {
        chatList.addAll(list)
        notifyDataSetChanged()
    }

    fun addList(list: List<ChattingData>, profileVisible: Boolean) {
        var index = chatList.size - 1
        if (!profileVisible) {
            (chatList.last() as ChatData).profileVisible = false
            notifyItemChanged(index++)
        }
        chatList.addAll(list)
        notifyItemRangeInserted(index, list.size)
    }

    fun initLoading() {
        chatList.add(LoadingData())
        notifyItemInserted(itemCount - 1)

    }

    fun deleteLoading() {
        val index = chatList.size - 1
        chatList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addChat(chat: ChatData) {
        if (chatList.isNotEmpty()) {
            if (chatFormatter.isTimeSame(chat, chatList[0] as ChatData)) {
                (chatList[0] as ChatData).timeVisible = false
                notifyItemChanged(0)
                chat.profileVisible = false
            }
            if (chatFormatter.isDayChanged(chat, chatList[0] as ChatData)) {
                chatList.add(0, DateData(date = chatFormatter.exceptTime(chat.time)))
                notifyItemInserted(0)
            }
        }
        chatList.add(0, chat)
        notifyItemInserted(0)
    }
}