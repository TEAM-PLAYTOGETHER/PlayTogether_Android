package com.playtogether_android.app.presentation.ui.thunder.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.usecase.light.GetThunderCategoryUseCase
import com.playtogether_android.domain.usecase.thunder.GetThunderScrapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ThunderListViewModel @Inject constructor(
    val getThunderCategoryUseCase: GetThunderCategoryUseCase,
    private val getThunderScrapUseCase: GetThunderScrapUseCase,
) : ViewModel() {

    private val _eatingItemList = MutableLiveData<List<CategoryData>>()
    val eatingItemList: LiveData<List<CategoryData>> get() = _eatingItemList

    private val _doingItemList = MutableLiveData<List<CategoryData>>()
    val doingItemList: LiveData<List<CategoryData>> get() = _doingItemList

    private val _goingItemList = MutableLiveData<List<CategoryData>>()
    val goingItemList: LiveData<List<CategoryData>> get() = _goingItemList

    var prevScrapState: Boolean? = null
    val laterScrapState = MutableLiveData<Boolean>()
    var adapterPosition: Int? = null
    var adapterThunderId: Int? = null

    var pageOrder = MutableLiveData<Int>()
    val sort = MutableLiveData<String>()

    var isLastPage = false
    private var pageSize = 20
    private var currentPage = 0

    fun currentCategory(): String {
        return when (pageOrder.value) {
            0 -> CATEGORY_EAT
            1 -> CATEGORY_GO
            else -> CATEGORY_DO
        }
    }

    private fun initList(category: String, list: List<CategoryData>) {
        when (category) {
            CATEGORY_EAT -> _eatingItemList.value = list
            CATEGORY_DO -> _doingItemList.value = list
            CATEGORY_GO -> _goingItemList.value = list
        }
    }

    private fun addList(category: String, list: List<CategoryData>) {
        when (category) {
            CATEGORY_EAT -> _eatingItemList.value =
                _eatingItemList.value?.toMutableList()?.apply { addAll(list) }
            CATEGORY_DO -> _doingItemList.value =
                _doingItemList.value?.toMutableList()?.apply { addAll(list) }
            CATEGORY_GO -> _goingItemList.value =
                _goingItemList.value?.toMutableList()?.apply { addAll(list) }
        }
    }

    fun getScrapValue(thunderId: Int, timing: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getThunderScrapUseCase(thunderId)
            }.onSuccess {
                when (timing) {
                    PREV -> prevScrapState = it
                    LATER -> laterScrapState.value = it
                }
            }.onFailure {
                when (timing) {
                    PREV -> prevScrapState = false
                    LATER -> laterScrapState.value = false
                }
            }
        }
    }

    fun getLightCategoryList(order: String, category: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                val crewId = PlayTogetherRepository.crewId
                val sortString: String = sort.value ?: DEFAULT_SORT
                if (order == FIRST) currentPage = 0
                currentPage++
                getThunderCategoryUseCase(crewId, category, sortString, currentPage, pageSize)
            }.onSuccess {
                when (order) {
                    FIRST -> {
                        if (it.isEmpty()) {
                            isLastPage = true
                            return@launch
                        }
                        initList(category, it)
                    }
                    MORE -> {
                        if (it.isEmpty()) {
                            isLastPage = true
                            return@launch
                        }
                        addList(category, it)
                    }
                }
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
        const val FIRST = "FIRST"
        const val MORE = "MORE"

        const val PREV = "prev"
        const val LATER = "later"
        const val SCRAP_PLUS = "plus"
        const val SCRAP_MINUS = "minus"
        const val SCRAP_DEFAULT = "default"
    }
}
