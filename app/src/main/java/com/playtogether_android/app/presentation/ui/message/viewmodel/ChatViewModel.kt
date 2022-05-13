package com.playtogether_android.app.presentation.ui.message.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.usecase.message.GetChatUseCase
import kotlinx.coroutines.launch

class ChatViewModel(
    val getChatUseCase: GetChatUseCase
) : ViewModel() {
    private var _chatData = MutableLiveData<List<ChatData>>()
    val chatData : LiveData<List<ChatData>> get() = _chatData

    fun getChatList(roomId : Int){
        viewModelScope.launch {
            kotlin.runCatching { getChatUseCase(roomId) }
                .onSuccess {
                    _chatData.value=it
                }
                .onFailure {
                    error -> Log.d("messageServer", "채팅 읽어오기 실패")
                }
        }
    }
}