package com.playtogether_android.app.presentation.ui.message.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemChatDateBinding
import com.playtogether_android.app.databinding.ItemChatLoadingBinding
import com.playtogether_android.app.databinding.ItemMyChatBinding
import com.playtogether_android.app.databinding.ItemOtherChatBinding
import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.model.message.ChattingData
import com.playtogether_android.domain.model.message.DateData

abstract class ChatViewHolder2(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(data: ChattingData)
}

class MyChatViewHolder(private val binding: ItemMyChatBinding) : ChatViewHolder2(binding) {
    override fun bind(data: ChattingData) {
        val mychatData = data as ChatData
        binding.data = mychatData
    }
}

class OtherChatViewHolder(private val binding: ItemOtherChatBinding) : ChatViewHolder2(binding) {
    override fun bind(data: ChattingData) {
        val otherChatData = data as ChatData
        binding.data = otherChatData
    }
}

class DateViewHolder(private val binding: ItemChatDateBinding) : ChatViewHolder2(binding) {
    override fun bind(data: ChattingData) {
        binding.textView.text = (data as DateData).getDate()
    }
}

class LoadingViewHolder(private val binding: ItemChatLoadingBinding) : ChatViewHolder2(binding) {
    override fun bind(data: ChattingData) {}
}