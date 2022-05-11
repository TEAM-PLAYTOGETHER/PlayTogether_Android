package com.playtogether_android.domain.repository.light

import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.model.light.TestData

interface LightRepository {
    suspend fun getLightListCategory(
        category: String,
        sort: String
    ): List<CategoryData>
}