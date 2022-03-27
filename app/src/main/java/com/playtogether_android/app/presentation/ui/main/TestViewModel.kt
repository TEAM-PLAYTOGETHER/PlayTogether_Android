package com.playtogether_android.app.presentation.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.TestData
import com.playtogether_android.domain.usecase.GetTestUseCase
import kotlinx.coroutines.launch

class TestViewModel(
    val getTestUseCase: GetTestUseCase
) : ViewModel() {

    val testInfo = MutableLiveData<List<TestData.Data>>()

    fun getTestData() {
        viewModelScope.launch {
            kotlin.runCatching { getTestUseCase() }
                .onSuccess {
                    testInfo.value = it.data
                    Log.d("test", "서버통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("test", "서버통신 실패")
                }
        }
    }
}