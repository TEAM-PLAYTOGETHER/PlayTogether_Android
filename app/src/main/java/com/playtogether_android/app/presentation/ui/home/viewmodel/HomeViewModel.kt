package com.playtogether_android.app.presentation.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeViewModel(
//    private val homeDataRepository
) {
    private val _refreshView = MutableLiveData<Boolean>()
    val refreshView : LiveData<Boolean> = _refreshView

    fun testData() {

    }
}