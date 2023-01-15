package com.playtogether_android.app.presentation.ui.message.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.app.presentation.ui.message.socketData.ChattingSocket
import com.playtogether_android.app.util.ChatDateStringReformat
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.model.message.ChattingData
import com.playtogether_android.domain.usecase.message.GetChatUseCase
import com.playtogether_android.domain.usecase.sign.GetTokenIssuance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(
    val getChatUseCase: GetChatUseCase,
    val getTokenIssuance: GetTokenIssuance
) : ViewModel() {
    private val _chattingList = MutableLiveData<List<ChattingData>>()
    val chattingList: LiveData<List<ChattingData>> get() = _chattingList

    private val _addedChattingList = MutableLiveData<List<ChattingData>>()
    val addedChattingList: LiveData<List<ChattingData>> get() = _addedChattingList
    var lastProfileVisible = true

    private val socket = ChattingSocket()
    private val chattingFormatter = ChatDateStringReformat()

    val tokenStatus = MutableLiveData<Int>()

    var isFirstPage: Boolean = true
    val isLoading = MutableLiveData<Boolean>()
    private val pageSize: Int = 40
    private var lastId: Int? = null
    var isLastPage: Boolean = false

    val inputMessage = MutableLiveData<String>("")

    fun checkToken() {
        viewModelScope.launch {
            kotlin.runCatching { getTokenIssuance(PlayTogetherRepository.userRefreshToken) }
                .onSuccess {
                    Timber.e("asdf in viewModel : $it")
                    tokenStatus.value = it.status
                    PlayTogetherRepository.userToken = it.accessToken
                    PlayTogetherRepository.userRefreshToken = it.refreshToken
                }.onFailure {
                    tokenStatus.value = FAILURE
                }
        }
    }

    fun getChatList(roomId: Int) {
        Timber.e("chatting in getChatList")
        viewModelScope.launch {
            kotlin.runCatching { getChatUseCase(roomId, null, pageSize) }
                .onSuccess {
                    Timber.e("chatting getList success1 : $it")
                    _chattingList.value = checkDateChanged(it)
                    Timber.e("chatting getList success2 : $it")
                    lastId = it.last().messageId
                    if (it.size < pageSize) isLastPage = true
                    isFirstPage = false
                }
                .onFailure { error ->
                    Timber.d("chatting messageServer: 첫 채팅 읽어오기 실패 / error:$error")
                    tokenStatus.value = FAILURE
                }
        }
    }

    fun getMoreChatList(roomId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { getChatUseCase(roomId, lastId, pageSize) }
                .onSuccess {
                    _addedChattingList.value = checkDateChanged(it)
                    _chattingList.value =
                        _chattingList.value?.toMutableList()?.apply { addAll(checkDateChanged(it)) }
                    isLoading.value = false
                    lastId =
                        chattingList.value?.let { (chattingList.value!!.last() as ChatData).messageId }
                    if (it.size < pageSize) isLastPage = true
                }
                .onFailure { error -> Timber.d("messageServer: 다음 채팅 읽어오기 실패 / error:$error") }
        }
    }

    private fun checkDateChanged(list: List<ChatData>): List<ChattingData> {
        val (list, prevProfileVisible) = chattingFormatter.processDate(
            list,
            chattingList.value?.let { it.last() as ChatData }
        )
        lastProfileVisible = prevProfileVisible
        return list
    }

    fun connectSocket(context: Context) {
        socket.initSocket(context)
    }

    fun enterRoom(roomId: Int, recvId: Int, errorLambda: () -> Unit) {
        socket.reqEnterRoom(roomId, recvId)
        socket.resEnterRoom { errorLambda() }
    }

    fun listenSocketConnection(errorLambda: () -> Unit, exitRoom: () -> Unit) {
        socket.resConnect { errorLambda() }
        socket.resExitRoom { exitRoom() }
    }

    fun listenSocketMessage(
        addChat: (ChatData) -> Unit,
        errorLambda: () -> Unit
    ) {
        socket.resNewMessageToRoom(
            { addChat(it) },
            { content, createdAt, amI ->
                chattingFormatter.reformatSocketChat(content, createdAt, amI)
            }
        )
        socket.resSendMessage(
            { errorLambda() },
            { addChat(it) },
            { content, createdAt, amI ->
                chattingFormatter.reformatSocketChat(content, createdAt, amI)
            }
        )
    }

    fun sendMessage(message: String, recvId: Int) {
        socket.reqSendMessage(message, recvId)
    }

    fun exitRoom() {
        socket.reqExitRoom()
    }

    companion object {
        const val TOKEN_ALIVE = 200
        const val TOKEN_REFRESHED = 400
        const val FAILURE = 500
    }
}
