package com.playtogether_android.app.presentation.ui.message.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.playtogether_android.app.presentation.ui.message.data.request.ReqEnterRoom
import com.playtogether_android.app.presentation.ui.message.data.request.ReqSendMessage
import com.playtogether_android.app.presentation.ui.message.data.response.*
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.usecase.message.GetChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.URISyntaxException
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    val getChatUseCase: GetChatUseCase,
) : ViewModel() {
    private var _chatData = MutableLiveData<List<ChatData>>()
    val chatData: LiveData<List<ChatData>> get() = _chatData

    private lateinit var socket: Socket
    private val gson by lazy { Gson() }

    var isLastChatChanged = MutableLiveData<Boolean>(false)

    val inputMessage = MutableLiveData<String>("")

    private fun refineChatListDate(list: List<ChatData>): List<ChatData> {
        val tempList: List<ChatData> = list
        for (i in list.indices)
            tempList[i].time = changeRemoteDateFormat(list[i].time)
        return tempList
    }

    private fun removeTimeAll(list: List<ChatData>): List<ChatData> {
        var nowSize = list.size - 1
        var tempSize = nowSize - 1

        if (tempSize < 0) return list

        while (true) {
            if (list[tempSize].timeVisible == false)
                break
            if (list[tempSize].messageType == list[nowSize].messageType) {
                if (list[tempSize].time == list[nowSize].time) {
                    list[tempSize].timeVisible = false
                }
            }
            nowSize = tempSize
            tempSize--
            if (tempSize < 0) break
        }
        return list
    }

    private fun removeTimePart(addChat: ChatData) {
        if (_chatData.value?.isEmpty() != false) return
        if (_chatData.value?.last()?.messageType != addChat.messageType) return
        if (_chatData.value?.last()?.time == addChat.time) {
            _chatData.value = _chatData.value?.toMutableList()?.apply { last().timeVisible = false }
            isLastChatChanged.value = true
        }
    }

    private fun changeRemoteDateFormat(exFormatDate: String): String {
        var date = exFormatDate.slice(IntRange(0, 9))
        date = date.replace("-", ".")
        val time = exFormatDate.slice(IntRange(11, 15))
        val dateTime = date + "  " + time
        return dateTime
    }

    fun getChatList(roomId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { getChatUseCase(roomId) }
                .onSuccess {
                    _chatData.value = removeTimeAll(refineChatListDate(it))
                }
                .onFailure { error ->
                    Log.d("messageServer", "채팅 읽어오기 실패")
                }
        }
    }

    fun initSocket(context: Context) {
        try {
            onConnect()
        } catch (e: URISyntaxException) {
            Toast.makeText(context, "네트워크 연결에 실패했습니다", Toast.LENGTH_SHORT)
            Timber.e("Socket Connection Fail")
        }
        socket.connect()
    }

    private fun onConnect() {
        val token = PlayTogetherRepository.userToken
        val tokenLst = listOf(token)
        val header: MutableMap<String, List<String>> = HashMap()
        header["Authorization"] = tokenLst
        val options = IO.Options()
        options.extraHeaders = header
        socket = IO.socket("http://13.125.232.150:3000", options)
        Timber.e("Socket connection success : ${socket.id()}")
    }

    fun resConnect(lamda: () -> Unit) {
        val connection = Emitter.Listener {
            val temp = gson.fromJson(it[0].toString(), ResConnection::class.java)
            if (!temp.success) lamda()
            Timber.e("Socket ResConnection : $temp")
        }
        socket.on("resConnection", connection)
    }

    fun reqEnterRoom(roomId: Int, recvId: Int) {
        val data = ReqEnterRoom(roomId, recvId)
        val jsonData = gson.toJson(data)
        Timber.e("Socket ReqEnterRoom")
        socket.emit("reqEnterRoom", jsonData)
    }

    fun resEnterRoom(lamda: () -> Unit) {
        val enterListener = Emitter.Listener {
            val success: Boolean = gson.fromJson(it[0].toString(), ResEnterRoom::class.java).success
            if (!success) lamda()
            Timber.e("Socket ResEnterRoom : $success")
        }
        socket.on("resEnterRoom", enterListener)
    }

    fun resNewMessageToRoom() {
        val messageListener = Emitter.Listener {
            val receivedChat: NewMessageReceived.Data.Message =
                gson.fromJson(it[0].toString(), NewMessageReceived::class.java).data.message
            Timber.e("Socket receive chat : $receivedChat")
            addChat(null, receivedChat.content, receivedChat.createdAt, amI = false)
        }
        socket.on("newMessageToRoom", messageListener)
    }

    private fun addChat(messageId: Int?, content: String, createdAt: String, amI: Boolean) {
        val newChat = ChatData(
            messageId = messageId ?: ((chatData.value?.last()?.messageId ?: 0) + 1),
            content = content,
            time = changeRemoteDateFormat(createdAt),
            messageType = amI
        )
        Handler(Looper.getMainLooper()).post {
            removeTimePart(newChat)
            _chatData.value = _chatData.value?.toMutableList()?.apply { add(newChat) }
        }
    }

    fun reqSendMessage(text: String, recvId: Int) {
        val message = ReqSendMessage(recvId, text)
        Timber.e("Socket ReqSendMessage content : $message")
        val jsonData = gson.toJson(message)
        socket.emit("reqSendMessage", jsonData)
    }

    fun resSendMessage(lamda: () -> Unit) {
        val sendListener = Emitter.Listener {
            val sendData = gson.fromJson(it[0].toString(), ResSendMessage::class.java)
            val sendChat = sendData.data.message
            if (sendData.success) {
                addChat(sendChat.messageId, sendChat.content, sendChat.createdAt, true)
            } else
                lamda()
        }
        socket.on("resSendMessage", sendListener)
    }

    fun reqExitRoom() {
        socket.emit("reqExitRoom")
        Timber.e("Socket ReqExitRoom")
    }

    fun resExitRoom(lamda: () -> Unit) {
        val exitListener = Emitter.Listener {
            val success = gson.fromJson(it[0].toString(), ResExitRoom::class.java).success
            if (!success) lamda()
            else disconnect()
            Timber.e("Socket ResExitRoom : $success")
        }
        socket.on("resExitRoom", exitListener)
    }

    fun disconnect() {
        socket.disconnect()
        Timber.e("Socket disconnected")
    }
}