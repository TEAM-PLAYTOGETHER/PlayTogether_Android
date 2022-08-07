package com.playtogether_android.data.datasource.light

import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.data.model.response.light.ResponseHomeLightning

interface LightDataSource {
    suspend fun getLightListCategory(
        category: String,
        sort: String,
    ): ResponseLightListCategory

    suspend fun getNewLightning() : ResponseHomeLightning

    suspend fun getHotLightning() : ResponseHomeLightning
}