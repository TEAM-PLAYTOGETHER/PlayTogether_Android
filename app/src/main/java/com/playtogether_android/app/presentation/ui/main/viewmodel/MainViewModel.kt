package com.playtogether_android.app.presentation.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.model.sign.IdDuplicationCheckData
import com.playtogether_android.domain.usecase.mypage.GetUserCheckUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    val getUserCheckUseCase: GetUserCheckUseCase
): ViewModel() {

    //유저 조회
    private val _userCheck = MutableLiveData<UserCheckData>()
    val userCheck: LiveData<UserCheckData>
        get() = _userCheck

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
}