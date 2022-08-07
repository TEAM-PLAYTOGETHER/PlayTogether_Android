package com.playtogether_android.app.presentation.ui.message

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityChattingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.viewmodel.ChatViewModel
import com.playtogether_android.app.util.PlayTogetherSharedPreference
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.message.PostSendMessageData
import dagger.hilt.android.AndroidEntryPoint
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import okhttp3.OkHttpClient
import java.net.URISyntaxException

@AndroidEntryPoint
class ChattingActivity : BaseActivity<ActivityChattingBinding>(R.layout.activity_chatting) {
    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var chatAdapter: ChatAdapter
    private var roomId = -1
    private var audienceId = -1
    private lateinit var name: String
    private lateinit var socket: Socket
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewmodel = chatViewModel
        binding.lifecycleOwner = this

        getIntentData()
        getChatAll()
        initAdapter()
        changeSendImage()
        initSocket()

        clickSendMessage()
        clickBackArrow()
        clickRefresh()
    }

    override fun onPause() {
        super.onPause()
        socket.disconnect()
    }

    fun initSocket() {
        try {
            val auth = PlayTogetherSharedPreference.getJwtToken(this)
            val options = IO.Options()
            options.query=auth
            socket = IO.socket("http://13.125.232.150:5500", options)
            Log.d("asdf", "Connection success : ${socket.id()}")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            Log.d("asdf", "Connection Fail")
        }

        socket.connect()
        emitSubscribe()

        /*Log.d("asdf",
            "${socket.on(Socket.EVENT_CONNECT,
                { socket.emit("connection", connection) })}")*/
    }

    private fun emitSubscribe(){
        Log.d("asdf", "id : $roomId $audienceId}")
        val data = SubscribeSocket(roomId, audienceId)
        val jsonData = gson.toJson(data)
        socket.emit("subscribe", jsonData)
    }

    private fun sendMessageSocket(){
        val message = SendMessageSocket(binding.etMessage.text.toString())
        val jsonData = gson.toJson(message)
        socket.emit("sendMessage", jsonData)
    }

    private fun clickSendMessage(){
        binding.ivSendMessage.setOnClickListener {
            //addChat()
            sendMessageSocket()
            binding.etMessage.text.clear()
        }
    }

    private fun clickBackArrow(){
        binding.ivInChattingBack.setOnClickListener {
            finish()
        }
    }

    private fun clickRefresh(){
        binding.ivInChattingRefresh.setOnClickListener {
            getChatAll()
            shortToast("새로고침 되었습니다")
        }
    }

    private fun changeSendImage() {
        chatViewModel.inputMessage.observe(this) {
            if (it.isNullOrEmpty()) cantSend()
            else canSend()
        }
    }

    private fun cantSend() {
        binding.ivSendMessage.setImageResource(R.drawable.ic_icn_message)
        binding.ivSendMessage.setBackgroundColor(
            ContextCompat.getColor(this@ChattingActivity, R.color.gray_gray03)
        )
        binding.ivSendMessage.isClickable = false
    }

    private fun canSend() {
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

    private fun scrollToBottom() {
        val size = chatAdapter.currentList.size - 1
        binding.rvInChattingChatting.scrollToPosition(size)
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

    private fun observeGetSendMessage() {
        chatViewModel.getSendMessage.observe(this) {
            if (it.success) {
                roomId = it.roomId.toInt()
                getChatAll()
            } else {
                Log.d("messageServer", "아직 메시지 전송 처리 안 끝남")

            }
        }
    }

    private fun addChat() {
        chatViewModel.postSendMessage(
            PostSendMessageData(binding.etMessage.text.toString(), audienceId))
        observeGetSendMessage()
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