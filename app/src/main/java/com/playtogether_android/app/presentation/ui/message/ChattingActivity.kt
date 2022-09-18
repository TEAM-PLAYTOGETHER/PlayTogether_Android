package com.playtogether_android.app.presentation.ui.message

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityChattingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.viewmodel.ChatViewModel
import com.playtogether_android.app.util.shortToast
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
        editTextObserver()
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
        chatViewModel.resNewMessageToRoom()
        chatViewModel.resSendMessage { runOnUiThread { shortToast("보내기에 실패하였습니다") } }
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

    private fun editTextObserver() {
        binding.etMessage.viewTreeObserver.addOnGlobalLayoutListener {
            scrollToBottom()
            Timber.e("editText layoutListener : 여기 실행 되나1")
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    Timber.e("editText layoutListener : 여기 실행 되나2")
                    binding.etMessage.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        }
    }

    private fun sdkfjasdlfa(){
        binding.etMessage.viewTreeObserver.addOnGlobalFocusChangeListener(
            object : ViewTreeObserver.OnGlobalFocusChangeListener{
                override fun onGlobalFocusChanged(p0: View?, p1: View?) {
                    Timber.e("asdf : ${p0}")
                }

            }
        )
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
        val name = intent.getStringExtra("name") ?: error("ChatActivity로 name을 넘겨주지 않음")
        binding.tvInChattingName.text = name
    }

    private fun updateChatUi() {
        chatViewModel.chatData.observe(this) {
            chatAdapter.submitList(it) {
                scrollToBottom()
            }
        }
    }

    private fun updateLastChatUi() {
        chatViewModel.isLastChatChanged.observe(this) {
            chatAdapter.notifyItemChanged(chatAdapter.itemCount - 1)
        }
        chatViewModel.isLastChatChanged.value = false
    }

    private fun scrollToBottom() {
        val size = chatAdapter.currentList.size - 1
        binding.rvInChattingChatting.scrollToPosition(size)
    }

    private fun initAdapter() {
        chatAdapter = ChatAdapter()
        binding.rvInChattingChatting.adapter = chatAdapter
        binding.rvInChattingChatting.addItemDecoration(VerticalItemDecoration())
        scrollToBottom()
    }
}