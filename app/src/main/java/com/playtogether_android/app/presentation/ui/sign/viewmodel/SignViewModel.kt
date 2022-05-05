package com.playtogether_android.app.presentation.ui.sign.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.playtogether_android.domain.usecase.sign.PostSignIdUseCase

class SignViewModel(
    val postSignIdUseCase: PostSignIdUseCase
) : ViewModel() {
    var id = MutableLiveData<String>()
    var pw = MutableLiveData<String>()
}