package com.playtogether_android.app.presentation.ui.message.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.message.MessageData
import com.playtogether_android.domain.usecase.message.GetMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    val getMessageUseCase: GetMessageUseCase
) : ViewModel() {
    private val _messageData = MutableLiveData<List<MessageData>>()
    val messageData: LiveData<List<MessageData>> get() = _messageData

    fun getMessageList() {
        viewModelScope.launch {
            kotlin.runCatching { getMessageUseCase() }
                .onSuccess {
                    if (it != null) {
                        Log.d("messageServer", "성공!!")
                        var tempList: List<MessageData> = it
                        for (i in it.indices) {
                            var date = it[i].createdAt.slice(IntRange(0, 9))
                            date = date.replace("-", ".")
                            val time = it[i].createdAt.slice(IntRange(11, 15))
                            val dateTime = date + "  " + time
                            tempList[i].createdAt = dateTime
                        }
                        _messageData.value = tempList
                    } else
                        Log.d("messageServer", "가져온게 없어서 null")
                }
                .onFailure { error -> Log.d("messageServer", "$error") }
        }
    }
}