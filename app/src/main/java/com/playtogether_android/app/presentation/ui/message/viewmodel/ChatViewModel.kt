package com.playtogether_android.app.presentation.ui.message.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.usecase.message.GetChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    val getChatUseCase: GetChatUseCase
) : ViewModel() {
    private var _chatData = MutableLiveData<List<ChatData>>()
    val chatData: LiveData<List<ChatData>> get() = _chatData

    fun getChatList(roomId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { getChatUseCase(roomId) }
                .onSuccess {
                    //_chatData.value=it
                    var tempList: List<ChatData> = it
                    for (i in it.indices) {
                        var date = it[i].time.slice(IntRange(0, 9))
                        date = date.replace("-", ".")
                        val time = it[i].time.slice(IntRange(11, 15))
                        val dateTime = date + "  " + time
                        tempList[i].time = dateTime
                    }
                    _chatData.value = tempList
                }
                .onFailure { error ->
                    Log.d("messageServer", "채팅 읽어오기 실패")
                }
        }
    }
}