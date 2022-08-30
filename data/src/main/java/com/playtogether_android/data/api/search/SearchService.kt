package com.playtogether_android.data.api.search

import com.playtogether_android.data.model.response.search.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {
    @GET("light/{crewId}/{curpage}/{pageSize}/search")
    suspend fun getSearchResult(
        @Path("crewId") crewId : String,
        @Path("curpage") curpage : Int?,
        @Path("pageSize") pageSize : Int?,
        @Query("search") search: String,
        @Query("category") category: String?
    ): SearchResult
}