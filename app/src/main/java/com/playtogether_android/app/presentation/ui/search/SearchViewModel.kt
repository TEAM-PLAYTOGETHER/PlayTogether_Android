package com.playtogether_android.app.presentation.ui.search

import android.util.Log
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
    val _category = MutableLiveData<String?>()
    var isLastPage: Boolean = false

    var pageSize = 10
    var currentPage = 0
    var totalCount = -1
    var totalPage = -1

    fun getSearchList(searchingWord: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getSearchResultUseCase(searchingWord, _category.value, currentPage, pageSize)
            }
                .onSuccess {
                    _searchList.value = it.lightData
                    currentPage = it.offset + 1
                    totalCount = it.totalCount
                    totalPage = it.totalPage
                    if (totalCount < pageSize) isLastPage = true
                    if (currentPage == totalPage) isLastPage = true
                }
                .onFailure { error -> Timber.d("searchServer error : $error") }
        }
    }

    companion object {
        val EAT = "먹을래"
        val DO = "할래"
        val GO = "갈래"
    }
}