package com.playtogether_android.app.presentation.ui.sign

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignViewModel() : ViewModel() {
    var id = MutableLiveData<String>()
    var pw = MutableLiveData<String>()
}