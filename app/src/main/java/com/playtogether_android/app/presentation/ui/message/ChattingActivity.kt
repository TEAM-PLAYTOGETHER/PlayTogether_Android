package com.playtogether_android.app.presentation.ui.message

import android.os.Bundle
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityChattingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.adapter.ChatAdapter
import com.playtogether_android.app.presentation.ui.message.adapter.VerticalItemDecoration
import com.playtogether_android.app.presentation.ui.message.viewmodel.ChatViewModel
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.message.ChatData
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ChattingActivity : BaseActivity<ActivityChattingBinding>(R.layout.activity_chatting) {
    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewmodel = chatViewModel
        binding.lifecycleOwner = this

        setName()
        initAdapter()
        updateChatUi()
        updateLastChatUi()
        clickSendMessage()
        clickBackArrow()
    }

    override fun onResume() {
        super.onResume()
        val roomId = intent.getIntExtra("roomId", -1)
        val recvId = intent.getIntExtra("audienceId", -1)
        if (roomId == -1) error("ChatActivity로 roomId를 넘겨주지 않음")
        if (recvId == -1) error("ChatActivity로 recvId를 넘겨주지 않음")
        chatViewModel.getChatList(roomId)
        connectSocketServer(roomId, recvId)
    }

    override fun onPause() {
        super.onPause()
        chatViewModel.reqExitRoom()
    }

    private fun connectSocketServer(roomId: Int, recvId: Int) {
        chatViewModel.initSocket(this)
        chatViewModel.resConnect { socketErrorControl("resConnection") }
        chatViewModel.reqEnterRoom(roomId, recvId)
        chatViewModel.resEnterRoom { socketErrorControl("resEnterRoom") }
        chatViewModel.resNewMessageToRoom { addChatToRecyclerView(it) }
        chatViewModel.resSendMessage(
            { runOnUiThread { shortToast("보내기에 실패하였습니다") } },
            { addChatToRecyclerView(it) }
        )
        chatViewModel.resExitRoom {
            runOnUiThread {
                finish()
                Timber.e("채팅방을 나갔음에도 소켓 연결이 끊어지지 않음")
            }
        }
    }

    private fun socketErrorControl(text: String) {
        runOnUiThread {
            shortToast("서버 통신이 원활하지 않습니다.")
            finish()
            Timber.e("채팅방 소켓 서버에 연결하지 못했습니다. ($text 오류")
        }
    }

    private fun addChatToRecyclerView(chatData: ChatData) {
        runOnUiThread {
            removeTimePart(chatData)
            chatAdapter.chatList.add(chatData)
            chatAdapter.notifyItemInserted(chatAdapter.itemCount - 1)
            scrollToBottom()
        }
    }

    private fun removeTimePart(addChat: ChatData) {
        if (chatAdapter.chatList.isEmpty()) return
        if (chatAdapter.chatList.last().messageType != addChat.messageType) return
        if (chatAdapter.chatList.last().time == addChat.time) {
            chatAdapter.chatList.last().timeVisible = false
            chatViewModel.isLastChatChanged.value = true
        }
    }

    private fun clickSendMessage() {
        val recvId = intent.getIntExtra("audienceId", -1)
        binding.ivSendMessage.setOnClickListener {
            chatViewModel.reqSendMessage(binding.etMessage.text.toString(), recvId)
            binding.etMessage.text.clear()
        }
    }

    private fun clickBackArrow() {
        binding.ivInChattingBack.setOnClickListener { finish() }
    }

    private fun setName() {
        val name = intent.getStringExtra("name")
        binding.tvInChattingName.text = name
    }

    private fun updateChatUi() {
        chatViewModel.chatData.observe(this) {
            chatAdapter.chatList.addAll(it)
            chatAdapter.notifyDataSetChanged()
            scrollToBottom()
        }
    }

    private fun updateLastChatUi() {
        chatViewModel.isLastChatChanged.observe(this) {
            chatAdapter.notifyItemChanged(chatAdapter.itemCount - 1)
        }
        chatViewModel.isLastChatChanged.value = false
    }

    private fun scrollToBottom() {
        val size = chatAdapter.itemCount - 1
        binding.rvInChattingChatting.scrollToPosition(size)
    }

    private fun initAdapter() {
        chatAdapter = ChatAdapter()
        binding.rvInChattingChatting.adapter = chatAdapter
        binding.rvInChattingChatting.addItemDecoration(VerticalItemDecoration())
    }
}