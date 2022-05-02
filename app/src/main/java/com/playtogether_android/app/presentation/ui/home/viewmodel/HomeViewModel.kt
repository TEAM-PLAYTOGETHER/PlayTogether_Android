package com.playtogether_android.app.presentation.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(

) : ViewModel() {
    private val _refreshView = MutableLiveData<Boolean>()
    val refreshView: LiveData<Boolean> = _refreshView

    fun testData() {

    }

}