package com.playtogether_android.app.presentation.ui.mypage.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.usecase.message.GetRoomIdUseCase
import com.playtogether_android.domain.usecase.mypage.GetUserCheckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    val getUserCheckUseCase: GetUserCheckUseCase,
    val getRoomIdUseCase: GetRoomIdUseCase
) : ViewModel() {
    //유저 조회
    private val _userCheck = MutableLiveData<UserCheckData>()
    val userCheck: LiveData<UserCheckData>
        get() = _userCheck

    val roomId = MutableLiveData<Int>()

    fun getUserCheck(userLoginId: String) {
        viewModelScope.launch {
            kotlin.runCatching { getUserCheckUseCase(userLoginId) }
                .onSuccess {
                    _userCheck.value = it
                    Log.d("userCheck", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("userCheck", "서버 통신 실패")
                }
        }
    }

    fun getRoomId(organizerId : Int){
        viewModelScope.launch {
            kotlin.runCatching { getRoomIdUseCase(organizerId) }
                .onSuccess {
                    roomId.value=it.roomId
                }
                .onFailure {
                    roomId.value=-1
                }
        }
    }
}