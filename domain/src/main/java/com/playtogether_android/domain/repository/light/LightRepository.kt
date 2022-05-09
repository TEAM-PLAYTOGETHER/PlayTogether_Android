package com.playtogether_android.domain.repository.light

import com.playtogether_android.domain.model.light.CategoryData

interface LightRepository {
    suspend fun getLightListCategory(): List<CategoryData>
}