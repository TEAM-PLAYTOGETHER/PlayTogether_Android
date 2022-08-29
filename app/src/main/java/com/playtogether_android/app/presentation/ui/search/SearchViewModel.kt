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
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val getSearchResultUseCase: GetSearchResultUseCase
): ViewModel() {
    private val _searchList = MutableLiveData<List<SearchData.LightData>>()
    val searchList : LiveData<List<SearchData.LightData>> = _searchList
    val _category = MutableLiveData<String?>()

    var limit = -1
    var offset = -1
    var totalCount = -1
    var totalPage = -1

    fun getSearchList(searchingWord : String){
        viewModelScope.launch {
            kotlin.runCatching { getSearchResultUseCase(searchingWord, _category.value) }
                .onSuccess {
                    _searchList.value = it.lightData
                    limit = it.limit
                    offset = it.offset
                    totalCount = it.totalCount
                    totalPage = it.totalPage
                }
                .onFailure { error -> Log.d("searchServer", "$error") }
        }
    }

    companion object{
        val EAT = "먹을래"
        val DO = "할래"
        val GO = "갈래"
    }
}