package com.playtogether_android.data.api.light

import com.playtogether_android.data.model.response.light.ResponseHomeLightning
import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LightService {

    @GET("light/{crewId}")
    suspend fun getLightListCategory(
        @Path("crewId") crewId: Int,
        @Query("category") category: String,
        @Query("sort") sort: String,
        @Query("curpage") currentPage: Int,
        @Query("pageSize") pageSize: Int
    ): ResponseLightListCategory

    @GET("light/{crewId}/hot")
    suspend fun getHotLightning(
        @Path("crewId") crewId: Int
    ): ResponseHomeLightning

    @GET("light/{crewId}/new")
    suspend fun getNewLightning(
        @Path("crewId") crewId: Int
    ): ResponseHomeLightning
}