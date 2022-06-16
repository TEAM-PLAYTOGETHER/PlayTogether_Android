package com.playtogether_android.app.presentation.ui.thunder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.thunder.Member
import com.playtogether_android.domain.model.thunder.Organizer
import com.playtogether_android.domain.model.thunder.ThunderDetailData
import com.playtogether_android.domain.usecase.thunder.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThunderDetailViewModel @Inject constructor(
    private val thunderJoinCancelUseCase: PostThunderJoinCancelUseCase,
    private val thunderDetailUseCase: GetThunderDetailUseCase,
    private val thunderDetailMemberUseCase: GetThunderDetailMemberUseCase,
    private val thunderDetailOrganizerUseCase: GetThunderDetailOrganizerUseCase,
    private val thunderDeleteUseCase: PostThunderDeleteUseCase
) : ViewModel() {

    private val _isConfirm = MutableLiveData<Boolean>()
    val isConfirm: LiveData<Boolean> = _isConfirm

    private val _detailItemList = MutableLiveData<ThunderDetailData>()
    val detailItemList: LiveData<ThunderDetailData> = _detailItemList

    private val _memberList = MutableLiveData<List<Member>>()
    val memberList: LiveData<List<Member>> = _memberList

    private val _organizerInfo = MutableLiveData<Organizer>()
    val organizerInfo: LiveData<Organizer> = _organizerInfo

    //번개 삭제
    private val _isDelete = MutableLiveData<Boolean>()
    val isDelete: LiveData<Boolean> = _isDelete

    fun joinAndCancel(thunderId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                thunderJoinCancelUseCase(thunderId)
            }.onSuccess {
                _isConfirm.value = true
                Log.d("onSuccess", "${it.message}")
            }.onFailure {
                _isConfirm.value = false
                Log.e("onFailure", "${it.message}")
            }
        }
    }

    fun thunderDetail(thunderId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                thunderDetailUseCase(thunderId)
            }.onSuccess {
                _detailItemList.value = it
                Log.d("thunderDetail-Success", "${it.image}")
            }.onFailure {
                Log.e("thunderDetail-Failure", "${it.message}")
            }
        }
    }

    fun thunderDetailOrganizer(thunderId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                thunderDetailOrganizerUseCase(thunderId)
            }.onSuccess {
                _organizerInfo.value = it
                Log.d("thunderDetailOriganizer-Success", "${it.userLoginId}")
            }.onFailure {
                it.printStackTrace()
                Log.e("thunderDetailOrganizer", "failure : ${it.message}")
            }
        }
    }

    fun thunderDetailMember(thunderId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                thunderDetailMemberUseCase(thunderId)
            }.onSuccess {
                _memberList.value = it
                Log.d("thunderDetailMember", "success : $it")
            }.onFailure {
                it.printStackTrace()
                Log.e("member", "failure : ${it.message}")
            }
        }
    }

    //번개 삭제
    fun thunderDelete(thunderId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                thunderDeleteUseCase(thunderId)
            }.onSuccess {
                _isDelete.value = true
                Log.d("OnSuccess-thunderDel", "${it.message}")
            }.onFailure {
                _isDelete.value = false
                Log.e("onFailure-thunderDel", "${it.message}")
            }
        }
    }
}