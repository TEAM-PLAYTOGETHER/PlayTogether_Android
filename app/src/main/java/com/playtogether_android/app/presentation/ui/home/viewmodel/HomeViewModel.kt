package com.playtogether_android.app.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.app.presentation.ui.home.temp.TempData
import com.playtogether_android.domain.model.home.JoinThunderData
import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.usecase.home.PostJoinThunderUseCase
import com.playtogether_android.domain.usecase.thunder.PostThunderJoinCancelUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    val postJoinThunderUseCase: PostJoinThunderUseCase
) : ViewModel() {
    private val _refreshView = MutableLiveData<Boolean>()
    val refreshView: LiveData<Boolean> = _refreshView

    //번개 신청
    private val _joinThunder = MutableLiveData<JoinThunderData>()
    val joinThunder: LiveData<JoinThunderData>
        get() = _joinThunder

    val tempList = listOf(
        TempData(
            3,
            "다들 모여",
            "잉어",
            "4/6",
            "22.05.09 세훈홈 14:00",
        ),
        TempData(
            3,
            "다들 모여",
            "잉어",
            "4/6",
            "22.05.09 세훈홈 14:00",
        ),
        TempData(
            3,
            "다들 모여",
            "잉어",
            "4/6",
            "22.05.09 세훈홈 14:00",
        ),
        TempData(
            3,
            "다들 모여",
            "잉어",
            "4/6",
            "22.05.09 세훈홈 14:00",
        ),
    )

    fun testData() {
        viewModelScope.launch {
            kotlin.runCatching {

            }
        }
    }

    fun postJoinThunder(lightId : Int) {
        viewModelScope.launch {
            kotlin.runCatching { postJoinThunderUseCase(lightId) }
                .onSuccess {
                    _joinThunder.value = it
                    Log.d("joinThunder", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("joinThunder", "서버 통신 실패")
                }
        }
    }


}