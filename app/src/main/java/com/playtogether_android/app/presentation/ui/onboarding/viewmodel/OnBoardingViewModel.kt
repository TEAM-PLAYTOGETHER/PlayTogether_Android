package com.playtogether_android.app.presentation.ui.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.playtogether_android.domain.model.onboarding.RegisterCrewData
import com.playtogether_android.domain.model.onboarding.RegisterCrewItem
import com.playtogether_android.domain.usecase.onboarding.PostRegisterCrewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val postRegisterCrewUseCase: PostRegisterCrewUseCase
) : ViewModel() {

    //동아리 참여 변수
    private val _registerCrew = MutableLiveData<RegisterCrewData>()
    val registerCrew: LiveData<RegisterCrewData>
        get() = _registerCrew


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
}