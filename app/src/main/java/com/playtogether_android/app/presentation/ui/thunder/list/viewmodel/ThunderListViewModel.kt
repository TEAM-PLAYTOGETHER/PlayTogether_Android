package com.playtogether_android.app.presentation.ui.thunder.list.viewmodel

import androidx.fragment.app.Fragment
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

    val categoryEatList = MutableLiveData<List<CategoryData>>()
    val categoryGoList = MutableLiveData<List<CategoryData>>()
    val categoryDoList = MutableLiveData<List<CategoryData>>()

    fun getLightCategoryList(category: String, sort: String = DEFAULT_SORT) {
        viewModelScope.launch {
            kotlin.runCatching {
                getThunderCategoryUseCase(category, sort)
            }.onSuccess {
                _categoryItemList.value = it
                when (category) {
                    CATEGORY_EAT -> categoryEatList.value = it
                    CATEGORY_GO -> categoryGoList.value = it
                    CATEGORY_DO -> categoryDoList.value = it
                }
                it.map {
                    _category.value = it.category
                }
                _sortType.value = sort
            }.onFailure {
                Timber.e("getLightList error : $it")
                Timber.e("getLightList error : ${it.message}")
            }
        }
    }

    fun setSortType(type: String): String {
        return if (type == DEFAULT_SORT)
            DEFAULT_SORT_KR
        else
            LIKECNT_KR
    }

    fun setCategory(category: String) {
        _category.value = category
    }

    companion object {
        const val DEFAULT_SORT = "createdAt"
        const val DEFAULT_SORT_KR = "최신순"
        const val LIKECNT_KR = "찜 많은순"
        const val CATEGORY_EAT = "먹을래"
        const val CATEGORY_GO = "갈래"
        const val CATEGORY_DO = "할래"
        const val CATEGORY_EAT_ORDER = 0
        const val CATEGORY_GO_ORDER = 1
        const val CATEGORY_DO_ORDER = 2
    }
}