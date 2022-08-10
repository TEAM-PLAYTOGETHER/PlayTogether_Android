package com.playtogether_android.app.presentation.ui.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.playtogether_android.domain.model.onboarding.*
import com.playtogether_android.domain.usecase.onboarding.GetCrewListUseCase
import com.playtogether_android.domain.usecase.onboarding.GetSubwayListUseCase
import com.playtogether_android.domain.usecase.onboarding.PostMakeCrewUseCase
import com.playtogether_android.domain.usecase.onboarding.PostRegisterCrewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val postRegisterCrewUseCase: PostRegisterCrewUseCase,
    val postMakeCrewUseCase: PostMakeCrewUseCase,
    val getCrewListUseCase: GetCrewListUseCase,
    val getSubwayListUseCase: GetSubwayListUseCase

) : ViewModel() {


    // 동아리명
    var crewName = MutableLiveData<String>()

    //아이디
    var userId = MutableLiveData<String>()


    var searchingWord = MutableLiveData<String>()

    //동아리 리스트
    private val _getCrewList = MutableLiveData<CrewListData>()
    val getCrewList: LiveData<CrewListData>
        get() = _getCrewList


    //동아리 참여 변수
    private val _registerCrew = MutableLiveData<RegisterCrewData>()
    val registerCrew: LiveData<RegisterCrewData>
        get() = _registerCrew

    //동아리 개설 변수
    private val _makeCrew = MutableLiveData<MakeCrewData>()
    val makeCrew: LiveData<MakeCrewData>
        get() = _makeCrew


    //지하철 정보 조회
    private val _subwayList = MutableLiveData<List<SubwayListData>>()
    val subwayList = _subwayList

    //지하철 정보 조회
    var searchSubwayList = ArrayList<SubwayListData>()

    var listAddAll = MutableLiveData<Boolean>(false)


    //동아리 개설 request
    var requestMakeCrew = MakeCrewItem("", "")


    //동아리 참여
    var crewCode = RegisterCrewItem("")

    var firstMbtiClick = MutableLiveData<Boolean>(false)
    var secondMbtiClick = MutableLiveData<Boolean>(false)
    var thirdMbtiClick = MutableLiveData<Boolean>(false)
    var forthMbtiClick = MutableLiveData<Boolean>(false)

    var selectedAll = MediatorLiveData<Boolean>().apply {
        this.addSource(firstMbtiClick) {
            this.value = isCompleteBtn()
        }
        this.addSource(secondMbtiClick) {
            this.value = isCompleteBtn()
        }
        this.addSource(thirdMbtiClick) {
            this.value = isCompleteBtn()
        }
        this.addSource(forthMbtiClick) {
            this.value = isCompleteBtn()
        }
    }

    private fun isCompleteBtn(): Boolean {
        return (firstMbtiClick.value == true) && (secondMbtiClick.value == true)
                && (thirdMbtiClick.value == true) && (forthMbtiClick.value == true)
    }

    //동아리 참여
    fun postRegisterCrew(registerCrewItem: RegisterCrewItem) {
        viewModelScope.launch {
            kotlin.runCatching { postRegisterCrewUseCase(registerCrewItem) }
                .onSuccess {
                    _registerCrew.value = it
                    Log.d("RegisterCrew", "서버 통신 성공")
                }
                .onFailure {
                    _registerCrew.value = RegisterCrewData(false, "")
                    it.printStackTrace()
                    Log.d("RegisterCrew", "서버 통신 실패")
                }
        }
    }

    //동아리 개설
    fun postMakeCrew(makeCrewItem: MakeCrewItem) {
        viewModelScope.launch {
            kotlin.runCatching { postMakeCrewUseCase(makeCrewItem) }
                .onSuccess {
                    _makeCrew.value = it
                    Timber.d("동아리 개설 : 서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Timber.d("동아리 개설 : 서버 통신 실패")
                }
        }
    }

    //동아리 리스트 조회
    fun getCrewList() {
        viewModelScope.launch {
            kotlin.runCatching { getCrewListUseCase() }
                .onSuccess {
                    _getCrewList.value = it
                    Timber.d("동아리 리스트 조회 : 서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Timber.d("동아리 리스트 조회 : 서버 통신 실패")
                }
        }
    }

    //지하철 정보 조회
    fun getSubwayList() {
        viewModelScope.launch {
            kotlin.runCatching { getSubwayListUseCase() }
                .onSuccess {
                    _subwayList.value = it
                    listAddAll.value = true
                    Timber.d("지하철 리스트 조회 : 서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Timber.d("지하철 리스트 조회 : 서버 통신 실패")
                }
        }
    }


}