package com.playtogether_android.app.presentation.ui.thunder.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.usecase.thunder.GetThunderScrapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThunderDoViewModel @Inject constructor(
    private val getThunderScrapUseCase: GetThunderScrapUseCase
) : ViewModel() {
    var prevScrapState: Boolean? = null
    val laterScrapState = MutableLiveData<Boolean>()

    var adapterPosition: Int? = null
    var adapterThunderId: Int? = null

    fun getScrapValue(thunderId: Int, timing: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getThunderScrapUseCase(thunderId)
            }.onSuccess {
                when (timing) {
                    ThunderListViewModel.PREV -> prevScrapState = it
                    ThunderListViewModel.LATER -> laterScrapState.value = it
                }
            }.onFailure {
                when (timing) {
                    ThunderListViewModel.PREV -> prevScrapState = false
                    ThunderListViewModel.LATER -> laterScrapState.value = false
                }
            }
        }
    }
}
