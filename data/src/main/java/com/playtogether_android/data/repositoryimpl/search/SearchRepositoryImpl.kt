package com.playtogether_android.data.repositoryimpl.search

import com.playtogether_android.data.api.search.SearchService
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.search.SearchData
import com.playtogether_android.domain.repository.search.SearchRepository

class SearchRepositoryImpl(private val service: SearchService) : SearchRepository {
    override suspend fun getSearchResult(
        searchingWord: String,
        category: String?,
        currentPage: Int?,
        pageSize: Int?
    ): SearchData {
        return service.getSearchResult(
            search = searchingWord,
            category = category,
            crewId = PlayTogetherRepository.crewId.toString(),
            curpage = currentPage,
            pageSize = pageSize
        ).data
    }
}