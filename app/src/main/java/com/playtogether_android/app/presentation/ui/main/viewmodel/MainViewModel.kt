package com.playtogether_android.app.presentation.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.playtogether_android.domain.usecase.mypage.GetUserCheckUseCase

class MainViewModel(
    val getUserCheckUseCase: GetUserCheckUseCase
): ViewModel() {

}