package com.playtogether_android.app.presentation.ui.message

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityChattingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.data.SendMessageSocket
import com.playtogether_android.app.presentation.ui.message.data.SubscribeSocket
import com.playtogether_android.app.presentation.ui.message.viewmodel.ChatViewModel
import com.playtogether_android.app.util.AuthInterceptor
import com.playtogether_android.domain.model.message.ChatData
import dagger.hilt.android.AndroidEntryPoint
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import okhttp3.OkHttpClient
import java.net.URISyntaxException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ChattingActivity : BaseActivity<ActivityChattingBinding>(R.layout.activity_chatting) {
    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var socket: Socket
    private val gson = Gson()
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

        clickSendMessage()
        clickBackArrow()
    }

    override fun onResume() {
        super.onResume()
        initSocket(roomId, audienceId)
    }

    override fun onPause() {
        super.onPause()
        socket.disconnect()
    }

    fun initSocket(roomId: Int, audienceId: Int) {
        try {
            onConnect()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            Log.d("asdf", "Connection Fail")
        }
        socket.connect()
        emitSubscribe(roomId, audienceId)
        onUpdateChat()
    }

    private fun onConnect() { //서버에 jwt token을 넘겨주며 연결한다.
        val httpClient = OkHttpClient.Builder().addNetworkInterceptor(AuthInterceptor()).build()
        val options = IO.Options()
        options.webSocketFactory = httpClient
        options.callFactory = httpClient
        socket = IO.socket("http://13.125.232.150:5500", options)
        Log.d("asdf", "Connection success : ${socket.id()}")
    }

    private fun onUpdateChat() { //상대방이 보내는 채팅을 소켓으로 받는다.
        val onUpdateChat = Emitter.Listener {
            val chat: SocketChatData =
                gson.fromJson(it[0].toString(), SocketChatData::class.java)
            val getChat = ChatData(
                null,
                content = chat.content,
                messageType = false,
                time = changeNowDateFormat()
            )
            runOnUiThread {
                chatAdapter.addChat(getChat)
                scrollToBottom()
                removeTimeAll()
            }
        }
        socket.on("newMessage", onUpdateChat)
    }

    private fun emitSubscribe(roomId: Int, audienceId: Int) { //subscribe 이벤트를 emit한다.
        Log.d("asdf", "id : $roomId $audienceId}")
        val data = SubscribeSocket(roomId, audienceId)
        val jsonData = gson.toJson(data)
        socket.emit("subscribe", jsonData)
    }

    fun sendMessageSocket(text: String) {
        val message = SendMessageSocket(text)
        val jsonData = gson.toJson(message)
        Log.d("asdf", "$message")
        socket.emit("sendMessage", jsonData)

        val sendChat = ChatData(
            null,
            content = message.messageContent,
            messageType = true,
            time = changeNowDateFormat()
        )
        runOnUiThread {
            Log.d("asdf", "runOnUiThread 진입")
            chatAdapter.addChat(sendChat)
        }
    }

    private fun changeNowDateFormat(): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        val date = dateFormat.format(Date())
        Log.d("asdf", "date : $date")
        return date
    }

    private fun clickSendMessage() {
        binding.ivSendMessage.setOnClickListener {
            sendMessageSocket(binding.etMessage.text.toString())
            binding.etMessage.text.clear()
            scrollToBottom()
            removeTimeAll()
        }
    }

    private fun clickBackArrow() {
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