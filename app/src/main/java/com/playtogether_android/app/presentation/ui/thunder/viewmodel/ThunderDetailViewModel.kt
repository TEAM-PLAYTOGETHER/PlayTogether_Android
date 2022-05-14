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
import kotlinx.coroutines.launch

class ThunderDetailViewModel(
    private val thunderJoinCancelUseCase: PostThunderJoinCancelUseCase,
    private val thunderDetailUseCase: GetThunderDetailUseCase,
    private val thunderDetailMemberUseCase: GetThunderDetailMemberUseCase,
    private val thunderDetailOrganizerUseCase: GetThunderDetailOrganizerUseCase,
) : ViewModel() {

    private val _isConfirm = MutableLiveData<Boolean>()
    val isConfirm: LiveData<Boolean> = _isConfirm

    private val _detailItemList = MutableLiveData<ThunderDetailData>()
    val detailItemList: LiveData<ThunderDetailData> = _detailItemList

    private val _memberList = MutableLiveData<List<Member>>()
    val memberList: LiveData<List<Member>> = _memberList

    private val _organizerInfo = MutableLiveData<Organizer>()
    val organizerInfo: LiveData<Organizer> = _organizerInfo

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
            }.onFailure {
                Log.e("thunderDetail", "failure")
            }
        }
    }

    fun thunderDetailOrganizer(thunderId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                thunderDetailOrganizerUseCase(thunderId)
            }.onSuccess {
                _organizerInfo.value = it
            }.onFailure {
                Log.e("thunderDetailOrganizer", "failure")

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
                Log.e("thunderDetailMember", "failure")

            }
        }
    }
}