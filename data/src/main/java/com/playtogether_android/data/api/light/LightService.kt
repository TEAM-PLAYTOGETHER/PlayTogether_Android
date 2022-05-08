package com.playtogether_android.data.api.light

import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import retrofit2.http.GET

interface LightService {
    @GET("light/?category=VALUE&sort=VALUE")
    suspend fun getLightListCategory(): ResponseLightListCategory
}