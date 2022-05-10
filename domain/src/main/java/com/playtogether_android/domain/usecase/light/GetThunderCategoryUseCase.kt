package com.playtogether_android.domain.usecase.light

import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.repository.light.LightRepository

class GetThunderCategoryUseCase(private val repository: LightRepository) {
    suspend operator fun invoke(category: String, sort: String): List<CategoryData> {
        return repository.getLightListCategory(category, sort)
    }
}