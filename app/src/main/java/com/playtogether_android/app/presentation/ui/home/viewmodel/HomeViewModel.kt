package com.playtogether_android.app.presentation.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.app.presentation.ui.home.temp.TempData
import kotlinx.coroutines.launch

class HomeViewModel(

) : ViewModel() {
    private val _refreshView = MutableLiveData<Boolean>()
    val refreshView: LiveData<Boolean> = _refreshView

    val tempList = listOf(
        TempData(
            3,
            "다들 모여",
            "잉어",
            "4/6",
            "22.05.09 세훈홈 14:00",
        ),
        TempData(
            3,
            "다들 모여",
            "잉어",
            "4/6",
            "22.05.09 세훈홈 14:00",
        ),
        TempData(
            3,
            "다들 모여",
            "잉어",
            "4/6",
            "22.05.09 세훈홈 14:00",
        ),
        TempData(
            3,
            "다들 모여",
            "잉어",
            "4/6",
            "22.05.09 세훈홈 14:00",
        ),
    )

    fun testData() {
        viewModelScope.launch {
            kotlin.runCatching {

            }
        }
    }


}