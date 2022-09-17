package com.playtogether_android.app.presentation.ui.userInfo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.userInfo.MyInfoData
import com.playtogether_android.domain.repository.userInfo.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor (private val userInfoRepository: UserInfoRepository): ViewModel() {

    private val _myInfoData = MutableLiveData<MyInfoData>()
    val myInfoData: LiveData<MyInfoData> = _myInfoData

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
}