package com.playtogether_android.data.api

import com.playtogether_android.data.model.ResponseTestData
import retrofit2.http.GET

interface TestService {
    @GET("publicData/dustSensor")
    suspend fun getTestData() : ResponseTestData
}