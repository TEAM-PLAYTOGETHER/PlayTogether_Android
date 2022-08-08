package com.playtogether_android.app.presentation.ui.message

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityChattingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChattingActivity : BaseActivity<ActivityChattingBinding>(R.layout.activity_chatting) {
    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var chatAdapter: ChatAdapter
    private var roomId = -1
    private var audienceId = -1
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewmodel = chatViewModel
        binding.lifecycleOwner = this

        getIntentData()
        getChatAll()
        initAdapter()
        changeSendImage()
        chatViewModel.initSocket(roomId, audienceId, chatAdapter)

        clickSendMessage()
        clickBackArrow()
    }

    override fun onPause() {
        super.onPause()
        chatViewModel.disconnectSocket()
    }

    private fun clickSendMessage(){
        binding.ivSendMessage.setOnClickListener {
            chatViewModel.sendMessageSocket(binding.etMessage.text.toString(), chatAdapter)
            binding.etMessage.text.clear()
            scrollToBottom()
        }
    }

    private fun clickBackArrow(){
        binding.ivInChattingBack.setOnClickListener {
            finish()
        }
    }

    private fun changeSendImage() {
        chatViewModel.inputMessage.observe(this) {
            if (it.isNullOrEmpty()) cantSendUiChange()
            else canSendUiChange()
        }
    }

    private fun cantSendUiChange() {
        binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message)
        binding.ivSendMessage.setBackgroundColor(
            ContextCompat.getColor(this@ChattingActivity, R.color.gray_gray03)
        )
        binding.ivSendMessage.isClickable = false
    }

    private fun canSendUiChange() {
        binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message_black)
        binding.ivSendMessage.setBackgroundColor(
            ContextCompat.getColor(this@ChattingActivity, R.color.main_green)
        )
        binding.ivSendMessage.isClickable = true
    }

    private fun getIntentData() {
        roomId = intent.getIntExtra("roomId", -1)
        name = intent.getStringExtra("name")!!
        audienceId = intent.getIntExtra("audienceId", -1)
        binding.tvInChattingName.text = name
    }

    private fun getChatAll() {
        if (roomId != -1) {
            chatViewModel.getChatList(roomId)
            chatViewModel.chatData.observe(this) {
                chatAdapter.submitList(it) {
                    scrollToBottom()
                    removeTimeAll()
                }
            }
        }
    }

    private fun scrollToBottom() {
        val size = chatAdapter.currentList.size - 1
        binding.rvInChattingChatting.scrollToPosition(size)
    }

    private fun removeTimeAll() {
        var nowSize = chatAdapter.currentList.size - 1
        var tempSize = nowSize - 1

        if (tempSize < 0)
            return

        while (true) {
            if (chatAdapter.currentList[tempSize].timeVisible == false) {
                break
            }
            if (chatAdapter.currentList[tempSize].messageType == chatAdapter.currentList[nowSize].messageType) {
                if (chatAdapter.currentList[nowSize].time == chatAdapter.currentList[tempSize].time) {
                    chatAdapter.currentList[tempSize].timeVisible = false
                }
            }
            nowSize = tempSize
            tempSize--
            if (tempSize < 0) break
        }
        chatAdapter.submitList(chatAdapter.currentList) {
        }
    }

    private fun initAdapter() {
        chatAdapter = ChatAdapter()
        binding.rvInChattingChatting.adapter = chatAdapter
        binding.rvInChattingChatting.addItemDecoration(VerticalItemDecoration())
        scrollToBottom()
    }
}