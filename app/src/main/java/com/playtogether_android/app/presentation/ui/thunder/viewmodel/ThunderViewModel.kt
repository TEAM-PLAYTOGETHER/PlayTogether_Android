package com.playtogether_android.app.presentation.ui.thunder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.thunder.ThunderTabListData
import com.playtogether_android.domain.usecase.thunder.GetApplyListUseCase
import kotlinx.coroutines.launch

class ThunderViewModel(
    val getApplyListUseCase: GetApplyListUseCase
) : ViewModel() {

    //번개탭-신청한 번개 리스트
    private val _thunderTabListData = MutableLiveData<ThunderTabListData>()
    val thundertabListData: LiveData<ThunderTabListData>
    get() = _thunderTabListData

    //번개탭-신청한 번개 리스트
    fun getApplyList() = viewModelScope.launch {
        kotlin.runCatching { getApplyListUseCase() }
            .onSuccess {
                _thunderTabListData.postValue(it)
                Log.d("getApplyList", it.toString())
            }
            .onFailure {
                it.printStackTrace()
                Log.d("getApplyList-fail", "fail")
            }
    }

}