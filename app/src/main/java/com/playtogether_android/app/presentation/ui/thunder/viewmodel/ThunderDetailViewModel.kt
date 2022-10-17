package com.playtogether_android.app.presentation.ui.thunder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.thunder.GetThunderExistCheck
import com.playtogether_android.domain.model.thunder.Member
import com.playtogether_android.domain.model.thunder.Organizer
import com.playtogether_android.domain.model.thunder.ThunderDetailData
import com.playtogether_android.domain.usecase.message.GetRoomIdUseCase
import com.playtogether_android.domain.usecase.thunder.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ThunderDetailViewModel @Inject constructor(
    private val thunderJoinCancelUseCase: PostThunderJoinCancelUseCase,
    private val thunderDetailUseCase: GetThunderDetailUseCase,
    private val thunderDetailMemberUseCase: GetThunderDetailMemberUseCase,
    private val thunderDetailOrganizerUseCase: GetThunderDetailOrganizerUseCase,
    private val thunderDeleteUseCase: PostThunderDeleteUseCase,
    private val getRoomIdUseCase: GetRoomIdUseCase,
    private val postThunderScrapUseCase: PostThunderScrapUseCase,
    private val getThunderScrapUseCase: GetThunderScrapUseCase,
    private val getThunderExistCheckerUseCase: GetThunderExistCheckerUseCase
) : ViewModel() {

    private val _isConfirm = MutableLiveData<Boolean>()
    val isConfirm: LiveData<Boolean> = _isConfirm

    private val _detailItemList = MutableLiveData<ThunderDetailData>()
    val detailItemList: LiveData<ThunderDetailData> = _detailItemList

    private val _memberList = MutableLiveData<List<Member>>()
    val memberList: LiveData<List<Member>> = _memberList

    private val _organizerInfo = MutableLiveData<Organizer>()
    val organizerInfo: LiveData<Organizer> = _organizerInfo

    val roomId = MutableLiveData<Int>()

    //번개 삭제
    private val _isDelete = MutableLiveData<Boolean>()
    val isDelete: LiveData<Boolean> = _isDelete

    private val _isLike = MutableLiveData<Boolean>()
    val isLike: LiveData<Boolean> = _isLike

    private var _isEntered = false
    val isEntered get() = _isEntered

    private var _isOrganizer = false
    val isOrganizer get() = _isOrganizer

    private val _isThunderType = MutableLiveData<GetThunderExistCheck>()
    val isThunderType: LiveData<GetThunderExistCheck> = _isThunderType


    fun getThunderInfo(thunderId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getThunderExistCheckerUseCase(thunderId)
            }.onSuccess {
                _isThunderType.value = it
                Timber.e("thunder detail info success")
                Timber.e("thunder detail apply ${it.isEntered}")
                Timber.e("thunder detail organ ${it.isOrganizer}")

            }.onFailure {
                Timber.e("thunder detail info error : $it")
            }
        }
    }

    fun postScrap(thunderId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                postThunderScrapUseCase(thunderId)
            }.onSuccess {
                _isLike.value = isLike.value?.not()
            }.onFailure {
                Timber.d("post scrap error : $it")
            }
        }
    }

    fun getScrapValue(thunderId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getThunderScrapUseCase(thunderId)
            }.onSuccess {
                Timber.e("get scrap value success : $it")
                _isLike.value = it
            }.onFailure {
                _isLike.value = false
                Timber.e("get scrap value error : $it")
            }
        }
    }


    fun getRoomId(organizerId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getRoomIdUseCase(organizerId)
            }.onSuccess {
                roomId.value = it.roomId
                Log.d("asdf", "viewmodel roomId : ${it.roomId}")
            }.onFailure {
                roomId.value = -1
                Log.d("asdf", "viewmodel roomId fail")
            }
        }
    }

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
                Timber.e("organizer : $it")
//                Log.d("thunderDetailOriganizer-Success", "${it.userLoginId}")
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