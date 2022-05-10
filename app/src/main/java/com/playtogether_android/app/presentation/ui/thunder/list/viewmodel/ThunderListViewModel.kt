package com.playtogether_android.app.presentation.ui.thunder.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.usecase.light.GetThunderCategoryUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class ThunderListViewModel(
    val getThunderCategoryUseCase: GetThunderCategoryUseCase,
) : ViewModel() {

    private val _categoryList = MutableLiveData<List<CategoryData>>()
    val categoryList: LiveData<List<CategoryData>> = _categoryList

    fun getLightList(category: String, sort: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getThunderCategoryUseCase(category, sort)
            }.onSuccess {
                _categoryList.value = it
            }.onFailure {
                Timber.e("error $it")
            }
        }
    }
}