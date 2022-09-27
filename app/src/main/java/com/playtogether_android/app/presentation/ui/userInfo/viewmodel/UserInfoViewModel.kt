package com.playtogether_android.app.presentation.ui.userInfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.userInfo.BlockUserList
import com.playtogether_android.domain.model.userInfo.MyInfoData
import com.playtogether_android.domain.model.userInfo.OtherInfoData
import com.playtogether_android.domain.repository.userInfo.UserInfoRepository
import com.playtogether_android.domain.usecase.message.GetRoomIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val roomIdUseCase: GetRoomIdUseCase
) : ViewModel() {

    private val _myInfoData = MutableLiveData<MyInfoData>()
    val myInfoData: LiveData<MyInfoData> = _myInfoData

    private val _otherInfoData = MutableLiveData<OtherInfoData>()
    val otherInfoData: LiveData<OtherInfoData> = _otherInfoData

    // 유저 차단
    private val _isBlock = MutableLiveData<Boolean>()
    val isBlock: LiveData<Boolean> = _isBlock

    // 동아리 탈퇴
    private val _isDelete = MutableLiveData<Boolean>()
    val isDelete: LiveData<Boolean> = _isDelete

    // 유저 차단 리스트 조회
    private val _blockUserList = MutableLiveData<BlockUserList>()
    val blockUserList: LiveData<BlockUserList> = _blockUserList

    // 유저와의 채팅방 roomId 조회
    var roomId: Int? = null

    // 유저 차단 해제
    private val _isUnblock = MutableLiveData<Boolean>()
    val isUnblock: LiveData<Boolean> = _isUnblock

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
                Timber.e("getOtherInfo-server 성공 : $it")
            }
            .onFailure {
                it.printStackTrace()
                Timber.e("getOtherInfo-server 실패 : $it")
            }
    }

    // 유저 차단
    fun postBlockUser(memberId: Int) = viewModelScope.launch {
        kotlin.runCatching { userInfoRepository.postBlockUser(memberId) }
            .onSuccess {
                _isBlock.value = true
                Timber.d("postBlockUser-server 성공 : $it")
            }
            .onFailure {
                _isBlock.value = false
                Timber.e("postBlockUser-server 실패 : $it")
            }
    }

    // 동아리 탈퇴
    fun delCrew() = viewModelScope.launch {
        kotlin.runCatching { userInfoRepository.delCrew() }
            .onSuccess {
                _isDelete.value = true
                Timber.d("delCrew-server 성공 : $it")
            }
            .onFailure {
                _isDelete.value = false
                Timber.e("delCrew-server 실패 : $it")
            }
    }

    // 유저 차단 리스트 조회
    fun getBlockUserList() = viewModelScope.launch {
        kotlin.runCatching { userInfoRepository.getBlockUserList() }
            .onSuccess {
                _blockUserList.postValue(it)
                Timber.d("getBlockUserList-server 성공 : $it")
            }
            .onFailure {
                it.printStackTrace()
                Timber.e("getBlockUserList-server 실패 : $it")
            }
    }


    // 유저와의 채팅방 roomId 조회
    fun getChattingRoomId(recvId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { roomIdUseCase(recvId) }
                .onSuccess { roomId = it.roomId }
                .onFailure { error -> Timber.e("상대 유저 마이페이지에서 상대 유저와의 roomId 조회 실패") }
        }
    }

    // 유저 차단 해제
    fun delUnblockUser(memberId: Int) = viewModelScope.launch {
        kotlin.runCatching { userInfoRepository.delUnblockUser(memberId) }
            .onSuccess {
                _isUnblock.value = true
                Timber.d("delUnblockUser-server 성공 : $it")
            }
            .onFailure {
                _isUnblock.value = false
                Timber.e("delUnblockUser-server 실패 : $it")
            }
    }
}