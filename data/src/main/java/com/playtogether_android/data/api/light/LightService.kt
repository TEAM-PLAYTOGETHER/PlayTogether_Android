package com.playtogether_android.data.api.light

import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.data.model.response.light.ResponseHomeLightning
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LightService {

    @GET("light")
    suspend fun getLightListCategory(
        @Query("category") category: String,
        @Query("sort") sort: String
    ): ResponseLightListCategory

    @GET("light/{crewId}/hot")
    suspend fun getHotLightning(
        @Path("crewId") crewId: Int
    ): ResponseHomeLightning

    @GET("light/{crewId}/new")
    suspend fun getNewLightning(
        @Path("crewId") crewId: Int
    ): ResponseHomeLightning

//    @GET("light/?category=`먹을래`&sort=`peopleCnt`")
//    suspend fun getLightListCategory(
//        @Query("category") category: String,
//        @Query("sort") sort: String
//    ): ResponseLightListCategory
}