package com.playtogether_android.app.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.search.SearchData
import com.playtogether_android.domain.usecase.search.GetSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val getSearchResultUseCase: GetSearchResultUseCase
) : ViewModel() {
    private val _searchList = MutableLiveData<List<SearchData.LightData>>()
    val searchList: LiveData<List<SearchData.LightData>> = _searchList
    val category = MutableLiveData<String?>(null)
    val resultEmpty = MutableLiveData<Boolean>(true)
    var isLastPage: Boolean = false

    var pageSize = 10
    var currentPage = 0

    fun getSearchList(searchingWord: String, order: String, lamda: () -> Unit) {
        viewModelScope.launch {
            kotlin.runCatching {
                var categoryTemp: String? = null
                if (category.value != null) categoryTemp = category.value.toString()
                if (order == FIRST) currentPage = 0
                currentPage++
                getSearchResultUseCase(searchingWord, categoryTemp, currentPage, pageSize)
            }
                .onSuccess {
                    when (order) {
                        FIRST -> {
                            _searchList.value = it.lightData
                            if (it.lightData.isEmpty()) {
                                resultEmpty.value = true
                                lamda()
                                isLastPage = true
                                return@launch
                            } else resultEmpty.value = false
                        }
                        MORE -> {
                            if (it.lightData.isEmpty()) {
                                isLastPage = true
                                return@launch
                            }
                            _searchList.value =
                                _searchList.value?.toMutableList()?.apply { addAll(it.lightData) }
                        }
                    }
                    Timber.d("searchServer success")
                }
                .onFailure { error -> Timber.d("searchServer error : $error") }
        }
    }

    companion object {
        val EAT = "먹을래"
        val DO = "할래"
        val GO = "갈래"
        val FIRST = "FIRST"
        val MORE = "MORE"
    }
}