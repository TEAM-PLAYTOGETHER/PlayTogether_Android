package com.playtogether_android.data.api.light

import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import retrofit2.http.GET
import retrofit2.http.Path

interface LightService {

    @GET("light/?category={category}&sort={sort}")
    suspend fun getLightListCategory(
        @Path("category") category: String,
        @Path("sort") sort: String
    ): ResponseLightListCategory
}