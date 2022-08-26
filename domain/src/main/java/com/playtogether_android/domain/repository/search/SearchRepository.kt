package com.playtogether_android.domain.repository.search

import com.playtogether_android.domain.model.search.SearchData

interface SearchRepository {
    suspend fun getSearchResult(searchingWord: String, category: String?): SearchData
}