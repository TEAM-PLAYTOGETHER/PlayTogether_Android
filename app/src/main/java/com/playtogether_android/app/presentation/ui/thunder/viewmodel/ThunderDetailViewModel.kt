package com.playtogether_android.app.presentation.ui.thunder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.thunder.Member
import com.playtogether_android.domain.model.thunder.ThunderDetailData
import com.playtogether_android.domain.usecase.thunder.GetThunderDetailMemberUseCase
import com.playtogether_android.domain.usecase.thunder.GetThunderDetailOrganizerUseCase
import com.playtogether_android.domain.usecase.thunder.GetThunderDetailUseCase
import com.playtogether_android.domain.usecase.thunder.PostThunderJoinCancelUseCase
import kotlinx.coroutines.launch

class ThunderDetailViewModel(
    private val thunderJoinCancelUseCase: PostThunderJoinCancelUseCase,
    private val thunderDetailUseCase: GetThunderDetailUseCase,
    private val thunderDetailMemberUseCase: GetThunderDetailMemberUseCase,
    private val thunderDetailOrganizerUseCase: GetThunderDetailOrganizerUseCase
) : ViewModel() {

    private val _isConfirm = MutableLiveData<Boolean>()
    val isConfirm: LiveData<Boolean> = _isConfirm

    private val _detailItemList = MutableLiveData<List<ThunderDetailData>>()
    val detailItemList: LiveData<List<ThunderDetailData>> = _detailItemList

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time

    private val _place = MutableLiveData<String>()
    val place: LiveData<String> = _place

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    private val _detailImg = MutableLiveData<String>()
    val detailImg: LiveData<String> = _detailImg

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _lightMemberCnt = MutableLiveData<Int>()
    val lightMemberCnt: LiveData<Int> = _lightMemberCnt

    private val _peopleCnt = MutableLiveData<Int>()
    val peopleCnt: LiveData<Int> = _peopleCnt

    private val _memberList = MutableLiveData<List<Member>>()
    val memberList: LiveData<List<Member>> = _memberList

    private val _organizerName = MutableLiveData<String>()
    val organizerName: LiveData<String> = _organizerName

    private val _organizerId = MutableLiveData<Int>()
    val organizerId: LiveData<Int> = _organizerId

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
                it.map {
                    _title.value = it.title
                    _date.value = it.date
                    _time.value = it.time
                    _place.value = it.place
                    _category.value = it.category
                    _detailImg.value = it.image
                    _description.value = it.description
                    _lightMemberCnt.value = it.lightMemberCnt
                    _peopleCnt.value = it.peopleCnt
                    _lightMemberCnt.value = it.lightMemberCnt
                    _peopleCnt.value = it.peopleCnt
                    Log.d("title", "success : ${_title.value}")
                    Log.d("date", "success : ${_date.value}")

                }
                Log.d("thunderDetailList", "success : $it")
                Log.d("thunderDetailListValue", "success : ${_detailItemList.value}")

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
                _organizerName.value = it.name
                _organizerId.value = it.organizerId
                Log.d("_organizerName", "success : ${it.name}")
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