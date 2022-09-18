package com.playtogether_android.app.presentation.ui.userInfo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.userInfo.MyInfoData
import com.playtogether_android.domain.model.userInfo.OtherInfoData
import com.playtogether_android.domain.repository.userInfo.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor (private val userInfoRepository: UserInfoRepository): ViewModel() {

    private val _myInfoData = MutableLiveData<MyInfoData>()
    val myInfoData: LiveData<MyInfoData> = _myInfoData

    private val _otherInfoData = MutableLiveData<OtherInfoData>()
    val otherInfoData: LiveData<OtherInfoData> = _otherInfoData


    // 유저 본인 멀티프로필 상세 조회
    fun getMyInfo() = viewModelScope.launch {
        kotlin.runCatching { userInfoRepository.getMyInfo() }
            .onSuccess {
                _myInfoData.postValue(it)
                Timber.d("getMyInfo-server 성공", it.toString())
            }
            .onFailure {
                it.printStackTrace()
                Timber.e("getMyInfo-server 실패 : $it")
            }
    }

    // 유저 멀티프로필 상세 조회
    fun getOtherInfo(crewId: Int, memberId: Int) = viewModelScope.launch {
        kotlin.runCatching { userInfoRepository.getOtherInfo(crewId, memberId) }
            .onSuccess {
                _otherInfoData.postValue(it)
                Timber.d("getOtherInfo-server 성공 : $it")
            }
            .onFailure {
                it.printStackTrace()
                Timber.e("getOtherInfo-server 실패 : $it")
            }
    }
}