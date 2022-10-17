package com.playtogether_android.app.presentation.ui.message.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.playtogether_android.app.presentation.ui.message.socketData.response.NewMessageReceived
import com.playtogether_android.app.presentation.ui.message.socketData.response.ResConnection
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.message.MessageData
import com.playtogether_android.domain.usecase.message.GetMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.URISyntaxException
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    val getMessageUseCase: GetMessageUseCase
) : ViewModel() {
    private val _messageData = MutableLiveData<List<MessageData>>()
    val messageData: LiveData<List<MessageData>> get() = _messageData

    private lateinit var socket: Socket
    private val gson by lazy { Gson() }

    fun initSocket(context: Context) {
        try {
            onConnect()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
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

    fun resConnect() {
        val connection = Emitter.Listener {
            val temp = gson.fromJson(it[0].toString(), ResConnection::class.java)
            Timber.e("Socket ResConnection : $temp")
        }
        socket.on("resConnection", connection)
    }

    fun resNewMessageToUser() {
        val messageListener = Emitter.Listener {
            val receivedChat: NewMessageReceived.Data.Message =
                gson.fromJson(it[0].toString(), NewMessageReceived::class.java).data.message.apply {
                    createdAt = changeDateFormat(createdAt)
                }
            updateRoomList(receivedChat)
        }
        socket.on("newMessageToUser", messageListener)
    }

    private fun changeDateFormat(text: String): String {
        var date = text.slice(IntRange(0, 9))
        date = date.replace("-", ".")
        val time = text.slice(IntRange(11, 15))
        val dateTime = date + "  " + time
        return dateTime
    }

    fun disconnect() {
        socket.disconnect()
        Timber.e("Socket disconnected")
    }

    fun getMessageList() {
        viewModelScope.launch {
            kotlin.runCatching { getMessageUseCase() }
                .onSuccess {
                    if (it.isNotEmpty()) {
                        Timber.d("messageServer: 성공!!")
                        val tempList: List<MessageData> = it
                        for (i in it.indices)
                            tempList[i].createdAt = changeDateFormat(it[i].createdAt)

                        _messageData.value = tempList
                    } else
                        Timber.d("messageServer : 가져온게 없어서 null $it")
                }
                .onFailure { error -> Timber.d("messageServer: $error") }
        }
    }

    fun updateRoomList(data: NewMessageReceived.Data.Message) {
        val temp: MessageData = data.let {
            MessageData(
                audience = it.audience,
                audienceId = it.audienceId,
                audienceProfile = it.audienceProfile,
                content = it.content,
                createdAt = it.createdAt,
                read = false,
                roomId = it.roomId,
                send = false
            )
        }

        Handler(Looper.getMainLooper()).post {
            for (item in _messageData.value ?: return@post) {
                if (temp.audienceId == item.audienceId) {
                    _messageData.value = _messageData.value?.toMutableList()?.apply { remove(item) }
                }
            }
            _messageData.value = _messageData.value?.toMutableList()?.apply { add(0, temp) }
        }
    }
}