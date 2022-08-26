package com.playtogether_android.data.api.search

import com.playtogether_android.data.model.response.search.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("light/search")
    suspend fun getSearchResult(
        @Query("search") search: String,
        @Query("category") category: String?
    ): SearchResult
}