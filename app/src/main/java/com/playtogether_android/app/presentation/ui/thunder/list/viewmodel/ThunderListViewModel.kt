package com.playtogether_android.app.presentation.ui.thunder.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.data.singleton.PlayTogetherRepository
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

    private val categoryItemList = MutableLiveData<List<CategoryData>>()

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    private val _sortType = MutableLiveData<String>()
    val sortType: LiveData<String> = _sortType

    private val _categoryEatList = MutableLiveData<List<CategoryData>>()
    val categoryEatList: LiveData<List<CategoryData>> = _categoryEatList

    private val _categoryGoList = MutableLiveData<List<CategoryData>>()
    val categoryGoList: LiveData<List<CategoryData>> = _categoryGoList

    private val _categoryDoList = MutableLiveData<List<CategoryData>>()
    val categoryDoList: LiveData<List<CategoryData>> = _categoryDoList

    val pageOrder = MutableLiveData<Int>()

    private var tapPosition = 0

    fun setTabPosition(position: Int) {
        tapPosition = position
    }

    fun getLightCategoryList(category: String, sort: String = DEFAULT_SORT) {
        viewModelScope.launch {
            kotlin.runCatching {
                val crewId = PlayTogetherRepository.crewId
                getThunderCategoryUseCase(crewId, category, sort)
            }.onSuccess {
                categoryItemList.value = it
                when (category) {
                    CATEGORY_EAT -> _categoryEatList.value = it
                    CATEGORY_GO -> _categoryGoList.value = it
                    CATEGORY_DO -> _categoryDoList.value = it
                }
                it.map {
                    _category.value = it.category
                }
                _sortType.value = sort
            }.onFailure {
                Timber.e("getLightList error : ${it.message}")
            }
        }
    }

    companion object {
        const val DEFAULT_SORT = "createdAt"
        const val SCPCNT = "scpCnt"
        const val CATEGORY_EAT = "먹을래"
        const val CATEGORY_GO = "갈래"
        const val CATEGORY_DO = "할래"
    }
}
