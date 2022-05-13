package com.playtogether_android.app.presentation.ui.thunder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import com.playtogether_android.domain.usecase.thunder.PostThunderJoinCancelUseCase
import kotlinx.coroutines.launch

class ThunderDetailViewModel(
    private val thunderJoinCancelUseCase: PostThunderJoinCancelUseCase
) : ViewModel() {

    private val _isConfirm = MutableLiveData<Boolean>()
    val isConfirm: LiveData<Boolean> = _isConfirm

    fun joinAndCancel(thunderId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                thunderJoinCancelUseCase(thunderId)
            }.onSuccess {
                _isConfirm.value = true
                Log.d("onSuccess", "${it.message}")
            }.onFailure {
                _isConfirm.value = false
                Log.e("onFailure", "${it.message}")
            }
        }
    }
}