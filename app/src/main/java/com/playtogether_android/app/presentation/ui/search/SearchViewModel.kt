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
    var isLastPage: Boolean = false

    var pageSize = 2
    var currentPage = 1

    fun getSearchList(searchingWord: String, order : String) {
        viewModelScope.launch {
            kotlin.runCatching {
                var categoryTemp: String? = null
                if (category.value != null) categoryTemp = category.value.toString()
                Timber.d("Log for searching : $searchingWord, $categoryTemp, $currentPage, $pageSize")
                getSearchResultUseCase(searchingWord, categoryTemp, currentPage, pageSize)
            }
                .onSuccess {
                    if (it.lightData.isEmpty()) {
                        isLastPage = true
                        return@launch
                    }
                    when(order){
                        FIRST -> _searchList.value = it.lightData
                        MORE -> _searchList.value = _searchList.value?.toMutableList()?.apply { addAll(it.lightData) }
                    }
                    currentPage++
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