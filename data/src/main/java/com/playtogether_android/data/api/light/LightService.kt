package com.playtogether_android.data.api.light

import com.playtogether_android.data.model.response.light.ResponseLightJoinCancel
import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LightService {

    @GET("light")
    suspend fun getLightListCategory(
        @Query("category") category: String,
        @Query("sort") sort: String
    ): ResponseLightListCategory

//    @GET("light/?category=`๋จน์๋`&sort=`peopleCnt`")
//    suspend fun getLightListCategory(
//        @Query("category") category: String,
//        @Query("sort") sort: String
//    ): ResponseLightListCategory
}