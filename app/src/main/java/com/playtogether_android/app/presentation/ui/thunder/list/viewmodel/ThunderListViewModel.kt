package com.playtogether_android.app.presentation.ui.thunder.list.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.usecase.light.GetThunderCategoryUseCase
import com.playtogether_android.domain.usecase.thunder.GetApplyListUseCase
import com.playtogether_android.domain.usecase.thunder.GetLikeListUseCase
import com.playtogether_android.domain.usecase.thunder.GetOpenListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ThunderListViewModel @Inject constructor(
    val getThunderCategoryUseCase: GetThunderCategoryUseCase,
    val getApplyListUseCase: GetApplyListUseCase,
    val getOpenListUseCase: GetOpenListUseCase,
    val getLikeListUseCase: GetLikeListUseCase
) : ViewModel() {

    private val _categoryItemList = MutableLiveData<List<CategoryData>>()
    val categoryItemList: LiveData<List<CategoryData>> = _categoryItemList

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

    private val _thunderApplyList = MutableLiveData<List<CategoryData>>()
    val thunderApplyList: LiveData<List<CategoryData>> = _thunderApplyList

    private var _thunderApplyIdList = listOf<Int>()
    val thunderApplyIdList get() = _thunderApplyIdList

    private val _thunderOpenList = MutableLiveData<List<CategoryData>>()
    val thunderOpenList: LiveData<List<CategoryData>> = _thunderOpenList

    private var _thunderOpenIdList = listOf<Int>()
    val thunderOpenIdList get() = _thunderOpenIdList

    private val _thunderLikeList = MutableLiveData<List<CategoryData>>()
    val thunderLikeList: LiveData<List<CategoryData>> = _thunderLikeList

    private var _tapPosition = 0
    val tabPosition get() = _tapPosition

    fun setTabPosition(position: Int) {
        _tapPosition = position
    }

    fun getApplyList() {
        viewModelScope.launch {
            kotlin.runCatching { getApplyListUseCase() }
                .onSuccess { list ->
                    _thunderApplyIdList = emptyList()
                    _thunderApplyList.value = list
                    _thunderApplyIdList = list.map { it.lightId }
                    Timber.e("thunder apply : $_thunderApplyIdList")
                    Timber.d("thunder apply size : ${list.size}")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("getApplyList-fail", "fail")
                }
        }
    }

    fun getOpenList() {
        viewModelScope.launch {
            kotlin.runCatching {
                getOpenListUseCase()
            }.onSuccess { list ->
                _thunderOpenIdList = emptyList()
                _thunderOpenList.value = list
                _thunderOpenIdList = list.map { it.lightId }
                Timber.e("thunder open : $_thunderOpenIdList")
            }.onFailure {

            }
        }
    }

    fun getLikeList() {
        viewModelScope.launch {
            kotlin.runCatching {
                getLikeListUseCase()
            }.onSuccess {
                _thunderLikeList.value = it
                Timber.d("thunder like : $it")
            }.onFailure {

            }
        }
    }

    fun getLightCategoryList(category: String, sort: String = DEFAULT_SORT) {
        viewModelScope.launch {
            kotlin.runCatching {
                val crewId = PlayTogetherRepository.crewId
                getThunderCategoryUseCase(crewId, category, sort)
            }.onSuccess {
                _categoryItemList.value = it
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
        const val LIKECNT = "peopleCnt"
        const val CATEGORY_EAT = "먹을래"
        const val CATEGORY_GO = "갈래"
        const val CATEGORY_DO = "할래"
    }
}
