package com.playtogether_android.domain.repository.light

import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.model.light.HomeLightningData
import com.playtogether_android.domain.model.light.TestData

interface LightRepository {
    suspend fun getLightListCategory(
        category: String,
        sort: String
    ): List<CategoryData>

    suspend fun getNewLightning(): List<HomeLightningData>

    suspend fun getHotLightning(): List<HomeLightningData>
}