package com.playtogether_android.app.presentation.ui.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.message.GetSendMessageData
import com.playtogether_android.domain.model.message.PostSendMessageData
import com.playtogether_android.domain.usecase.message.PostSendMessageUseCase
import kotlinx.coroutines.launch

class SendMessageViewModel(
    val postSendMessageUseCase: PostSendMessageUseCase
) : ViewModel() {
    private val _getSendMessage = MutableLiveData<GetSendMessageData>()
    val getSendMessageData : LiveData<GetSendMessageData> get() = _getSendMessage

    fun postSendMessage(postSendMessageData: PostSendMessageData){
        viewModelScope.launch {
            kotlin.runCatching { postSendMessageUseCase(postSendMessageData) }
                .onSuccess { _getSendMessage.value = it
                    Log.d("messageServer", "메시지 보내기 서버통신 성공")}
                .onFailure { _getSendMessage.value = GetSendMessageData("false")
                    it.printStackTrace()
                    Log.d("messageServer", "메시지 보내기 서버통신 실패")}
        }
    }
}