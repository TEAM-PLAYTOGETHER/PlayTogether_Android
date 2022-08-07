package com.playtogether_android.app.presentation.ui.message.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.model.message.GetSendMessageData
import com.playtogether_android.domain.model.message.PostSendMessageData
import com.playtogether_android.domain.usecase.message.GetChatUseCase
import com.playtogether_android.domain.usecase.message.PostSendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    val getChatUseCase: GetChatUseCase,
    val postSendMessageUseCase: PostSendMessageUseCase
) : ViewModel() {
    private var _chatData = MutableLiveData<List<ChatData>>()
    val chatData: LiveData<List<ChatData>> get() = _chatData

    private val _getSendMessage = MutableLiveData<GetSendMessageData>()
    val getSendMessage: LiveData<GetSendMessageData> get() = _getSendMessage

    val inputMessage = MutableLiveData<String>()

    private fun refineChatListDate(list: List<ChatData>): List<ChatData> {
        val tempList: List<ChatData> = list
        for (i in list.indices)
            tempList[i].time = changeDateFormat(list[i].time)
        return tempList
    }

    private fun changeDateFormat(exFormatDate: String): String {
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
                    _chatData.value = refineChatListDate(it)
                }
                .onFailure { error ->
                    Log.d("messageServer", "채팅 읽어오기 실패")
                }
        }
    }

    fun postSendMessage(postSendMessageData: PostSendMessageData) {
        viewModelScope.launch {
            kotlin.runCatching { postSendMessageUseCase(postSendMessageData) }
                .onSuccess {
                    _getSendMessage.value = it
                    Log.d("messageServer", "메시지 보내기 서버통신 성공")
                }
                .onFailure {
                    _getSendMessage.value = GetSendMessageData("false", false)
                    it.printStackTrace()
                    Log.d("messageServer", "메시지 보내기 서버통신 실패")
                }
        }
    }
}