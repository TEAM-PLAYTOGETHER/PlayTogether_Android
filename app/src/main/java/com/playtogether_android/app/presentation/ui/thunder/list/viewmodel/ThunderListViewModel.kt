package com.playtogether_android.app.presentation.ui.thunder.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.usecase.light.GetThunderCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ThunderListViewModel @Inject constructor(
    val getThunderCategoryUseCase: GetThunderCategoryUseCase,
) : ViewModel() {

    private val _categoryItemList = MutableLiveData<List<CategoryData>>()
    val categoryItemList: LiveData<List<CategoryData>> = _categoryItemList

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    private val _sortType = MutableLiveData<String>()
    val sortType: LiveData<String> = _sortType

    fun getLightCategoryList(category: String = DEFAULT_CATEGORY, sort: String = DEFAULT_SORT) {
        viewModelScope.launch {
            kotlin.runCatching {
                getThunderCategoryUseCase(category, sort)
            }.onSuccess {
                _categoryItemList.value = it
                it.map {
                    _category.value = it.category
                }
                _sortType.value = sort
                Timber.d("viewmodel category : $category")
            }.onFailure {
                Timber.e("getLightList error : $it")
            }
        }
    }

    fun setSortType(type: String) {
        _sortType.value = type
    }

    fun setCategory(category: String) {
        _category.value = category
    }

    companion object {
        const val DEFAULT_CATEGORY = "먹을래"
        const val DEFAULT_SORT = "createdAt"
    }
}