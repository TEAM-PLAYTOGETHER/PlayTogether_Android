package com.playtogether_android.domain.repository.light

import com.playtogether_android.domain.model.light.CategoryData

interface LightRepository {
    suspend fun getLightListCategory(
        category: String,
        sort: String
    ): List<CategoryData>

    suspend fun getNewLightning(): List<CategoryData>

    suspend fun getHotLightning(): List<CategoryData>
}