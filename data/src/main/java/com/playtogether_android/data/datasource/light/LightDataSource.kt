package com.playtogether_android.data.datasource.light

import com.playtogether_android.data.model.response.light.ResponseHomeLightning
import com.playtogether_android.data.model.response.light.ResponseLightListCategory

interface LightDataSource {
    suspend fun getLightListCategory(
        crewId: Int,
        category: String,
        sort: String,
        currentPage: Int,
        pageSize: Int
    ): ResponseLightListCategory

    suspend fun getNewLightning(crewId: Int): ResponseHomeLightning

    suspend fun getHotLightning(crewId: Int): ResponseHomeLightning
}