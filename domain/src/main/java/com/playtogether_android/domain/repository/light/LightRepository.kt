package com.playtogether_android.domain.repository.light

import com.playtogether_android.domain.model.light.CategoryData

interface LightRepository {
    suspend fun getLightListCategory(
        crewId: Int,
        category: String,
        sort: String
    ): List<CategoryData>

    suspend fun getNewLightning(crewId: Int): List<CategoryData>

    suspend fun getHotLightning(crewId: Int): List<CategoryData>
}