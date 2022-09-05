package com.playtogether_android.domain.usecase.search

import com.playtogether_android.domain.model.search.SearchData
import com.playtogether_android.domain.repository.search.SearchRepository
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend operator fun invoke(
        searchingWord: String,
        category: String?,
        currentPage: Int?,
        pageSize: Int?
    ): SearchData {
        return repository.getSearchResult(
            searchingWord = searchingWord,
            category = category,
            currentPage = currentPage,
            pageSize = pageSize
        )
    }
}