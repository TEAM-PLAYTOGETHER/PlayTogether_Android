package com.playtogether_android.app.presentation.ui.message.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.message.MessageData
import com.playtogether_android.domain.usecase.message.GetMessageUseCase
import kotlinx.coroutines.launch

class MessageViewModel(
    val getMessageUseCase: GetMessageUseCase
) : ViewModel() {
    private val _messageData = MutableLiveData<List<MessageData>>()
    val messageData : LiveData<List<MessageData>> get() = _messageData

    fun getMessageList(){
        viewModelScope.launch {
            kotlin.runCatching { getMessageUseCase() }
                .onSuccess {
                    if(it!=null){
                        Log.d("messageServer", "성공!!")
                        _messageData.value = it
                    }
                    else
                        Log.d("messageServer", "가져온게 없어서 null")}
                .onFailure { error -> Log.d("messageServer", "$error") }
        }
    }
}