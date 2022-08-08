package com.playtogether_android.app.presentation.ui.message.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.playtogether_android.app.presentation.ui.message.ChatAdapter
import com.playtogether_android.app.presentation.ui.message.SendMessageSocket
import com.playtogether_android.app.presentation.ui.message.SubscribeSocket
import com.playtogether_android.app.util.AuthInterceptor
import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.model.message.PostSendMessageData
import com.playtogether_android.domain.usecase.message.GetChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import java.net.URISyntaxException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    val getChatUseCase: GetChatUseCase,
) : ViewModel() {
    private lateinit var socket: Socket
    private val gson = Gson()

    private var _chatData = MutableLiveData<List<ChatData>>()
    val chatData: LiveData<List<ChatData>> get() = _chatData

    val inputMessage = MutableLiveData<String>()

    private fun refineChatListDate(list: List<ChatData>): List<ChatData> {
        val tempList: List<ChatData> = list
        for (i in list.indices)
            tempList[i].time = changeRemoteDateFormat(list[i].time)
        return tempList
    }

    private fun changeRemoteDateFormat(exFormatDate: String): String {
        var date = exFormatDate.slice(IntRange(0, 9))
        date = date.replace("-", ".")
        val time = exFormatDate.slice(IntRange(11, 15))
        val dateTime = date + "  " + time
        return dateTime
    }

    private fun changeNowDateFormat(): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        val date = dateFormat.format(Date())
        Log.d("asdf", "date : $date")
        return date
    }

    fun getChatList(roomId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { getChatUseCase(roomId) }
                .onSuccess {
                    _chatData.value = refineChatListDate(it)
                }
                .onFailure { error ->
                    Log.d("messageServer", "채팅 읽어오기 실패")
                }
        }
    }

    fun initSocket(roomId: Int, audienceId: Int, chatAdapter: ChatAdapter) {
        try {
            onConnect()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            Log.d("asdf", "Connection Fail")
        }
        socket.connect()
        emitSubscribe(roomId, audienceId)
        onUpdateChat(chatAdapter)
    }

    fun disconnectSocket() {
        socket.disconnect()
    }

    private fun onConnect() { //서버에 jwt token을 넘겨주며 연결한다.
        val httpClient = OkHttpClient.Builder().addNetworkInterceptor(AuthInterceptor()).build()
        val options = IO.Options()
        options.webSocketFactory = httpClient
        options.callFactory = httpClient
        socket = IO.socket("http://13.125.232.150:5500", options)
        Log.d("asdf", "Connection success : ${socket.id()}")
    }

    private fun onUpdateChat(chatAdapter: ChatAdapter) { //상대방이 보내는 채팅을 소켓으로 받는다.
        val onUpdateChat = Emitter.Listener {
            val chat: PostSendMessageData =
                gson.fromJson(it[0].toString(), PostSendMessageData::class.java)
            val getChat = ChatData(
                null,
                content = chat.content,
                messageType = false,
                time = changeNowDateFormat()
            )
            chatAdapter.addChat(getChat)
        }
        socket.on("newMessage", onUpdateChat)
    }

    private fun emitSubscribe(roomId: Int, audienceId: Int) { //subscribe 이벤트를 emit한다.
        Log.d("asdf", "id : $roomId $audienceId}")
        val data = SubscribeSocket(roomId, audienceId)
        val jsonData = gson.toJson(data)
        socket.emit("subscribe", jsonData)
    }

    fun sendMessageSocket(text: String, chatAdapter: ChatAdapter) {
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
        chatAdapter.addChat(sendChat)
    }
}