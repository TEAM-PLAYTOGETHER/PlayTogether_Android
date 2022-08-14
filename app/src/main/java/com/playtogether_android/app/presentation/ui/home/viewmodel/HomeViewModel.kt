package com.playtogether_android.app.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.home.JoinThunderData
import com.playtogether_android.domain.model.home.ThunderJoinEndData
import com.playtogether_android.domain.model.home.ThunderJoinEndMember
import com.playtogether_android.domain.model.home.ThunderJoinEndOrganizer
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.usecase.home.GetThunderJoinEndMemberUseCase
import com.playtogether_android.domain.usecase.home.GetThunderJoinEndOrganizerUseCase
import com.playtogether_android.domain.usecase.home.GetThunderJoinEndUseCase
import com.playtogether_android.domain.usecase.home.PostJoinThunderUseCase
import com.playtogether_android.domain.usecase.light.GetHotListUseCase
import com.playtogether_android.domain.usecase.light.GetNewListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val postJoinThunderUseCase: PostJoinThunderUseCase,
    val getThunderJoinEndUseCase: GetThunderJoinEndUseCase,
    val getThunderJoinEndMemberUseCase: GetThunderJoinEndMemberUseCase,
    val getThunderJoinEndOrganizerUseCase: GetThunderJoinEndOrganizerUseCase,
    val getHotListUseCase: GetHotListUseCase,
    val getNewListUseCase: GetNewListUseCase
) : ViewModel() {
    private val _refreshView = MutableLiveData<Boolean>()
    val refreshView: LiveData<Boolean> = _refreshView

    //번개 신청
    private val _joinThunder = MutableLiveData<JoinThunderData>()
    val joinThunder: LiveData<JoinThunderData>
        get() = _joinThunder

    //번개 신청 완료
    private val _endThunder = MutableLiveData<ThunderJoinEndData>()
    val endThunder: LiveData<ThunderJoinEndData>
        get() = _endThunder

    private val _memberList = MutableLiveData<List<ThunderJoinEndMember>>()
    val memberList: LiveData<List<ThunderJoinEndMember>> = _memberList

    private val _organizerInfo = MutableLiveData<ThunderJoinEndOrganizer>()
    val organizerInfo: LiveData<ThunderJoinEndOrganizer> = _organizerInfo

    private val _hotList = MutableLiveData<List<CategoryData>>()
    val hotList: LiveData<List<CategoryData>> = _hotList

    private val _newList = MutableLiveData<List<CategoryData>>()
    val newList: LiveData<List<CategoryData>> = _newList

    fun testData() {
        viewModelScope.launch {
            kotlin.runCatching {

            }
        }
    }

    fun postJoinThunder(lightId: Int) {
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

    fun getThunderJoinEnd(lightId: Int) {
        viewModelScope.launch {
            kotlin.runCatching { getThunderJoinEndUseCase(lightId) }
                .onSuccess {
                    _endThunder.value = it
                    Log.d("번개 참여 완료", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("번개 참여 실패", "서버 통신 성공")
                }
        }
    }

    fun thunderJoinEndOrganizer(lightId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getThunderJoinEndOrganizerUseCase(lightId)
            }.onSuccess {
                _organizerInfo.value = it
            }.onFailure {
                Log.e("thunderDetailOrganizer", "failure")

            }
        }
    }

    fun thunderJoinEndMember(lightId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getThunderJoinEndMemberUseCase(lightId)
            }.onSuccess {
                _memberList.value = it
                Log.d("thunderDetailMember", "success : $it")
            }.onFailure {
                Log.e("thunderDetailMember", "failure")

            }
        }
    }

    fun getHotList() {
        viewModelScope.launch {
            kotlin.runCatching {
                getHotListUseCase()
            }.onSuccess {
                _hotList.value = it
            }.onFailure {
                Timber.e("getHotList : $it")
            }
        }
    }

    fun getNewList() {
        viewModelScope.launch {
            kotlin.runCatching {
                getNewListUseCase()
            }.onSuccess {
                _newList.value = it
            }.onFailure {
                Timber.e("getNewList : $it")
            }
        }
    }

}