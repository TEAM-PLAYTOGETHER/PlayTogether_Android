package com.playtogether_android.app.presentation.ui.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.playtogether_android.domain.model.onboarding.MakeCrewData
import com.playtogether_android.domain.model.onboarding.MakeCrewItem
import com.playtogether_android.domain.model.onboarding.RegisterCrewData
import com.playtogether_android.domain.model.onboarding.RegisterCrewItem
import com.playtogether_android.domain.usecase.onboarding.PostMakeCrewUseCase
import com.playtogether_android.domain.usecase.onboarding.PostRegisterCrewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val postRegisterCrewUseCase: PostRegisterCrewUseCase,
    val postMakeCrewUseCase: PostMakeCrewUseCase
) : ViewModel() {

    //동아리 참여 변수
    private val _registerCrew = MutableLiveData<RegisterCrewData>()
    val registerCrew: LiveData<RegisterCrewData>
        get() = _registerCrew

    //동아리 개설 변수
    private val _makeCrew = MutableLiveData<MakeCrewData>()
    val makeCrew : LiveData<MakeCrewData>
    get() = _makeCrew


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
}