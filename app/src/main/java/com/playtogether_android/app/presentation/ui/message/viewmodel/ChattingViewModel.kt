package com.playtogether_android.app.presentation.ui.message.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.app.presentation.ui.message.adapter.ChattingData
import com.playtogether_android.domain.usecase.message.GetChatUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class ChattingViewModel(
    val getChatUseCase: GetChatUseCase
) : ViewModel() {
    private val _chattingList = MutableLiveData<List<ChattingData>>()
    val chattingList: LiveData<List<ChattingData>> get() = _chattingList

    val isLoading = MutableLiveData<Boolean>(false)
    private val pageSize: Int = 20
    private var curPage = 1
    var isLastPage: Boolean = false

    fun getChatList(roomId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { getChatUseCase(roomId, curPage, pageSize) }
                .onSuccess {
                    //_chattingList.value = it
                    if (it.size < pageSize) isLastPage = true
                    else curPage++
                }
                .onFailure { error -> Timber.d("messageServer: 첫 채팅 읽어오기 실패") }
        }
    }

    fun getMoreChatList(roomId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { getChatUseCase(roomId, curPage, pageSize) }
                .onSuccess {
                    if (it.size < pageSize) isLastPage = true
                    else curPage++
                }
                .onFailure { error -> Timber.d("messageServer: 다음 채팅 읽어오기 실패") }
        }
    }
}
