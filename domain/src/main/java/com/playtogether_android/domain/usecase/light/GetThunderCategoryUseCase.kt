package com.playtogether_android.domain.usecase.light

import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.repository.light.LightRepository
import javax.inject.Inject

class GetThunderCategoryUseCase @Inject constructor(private val repository: LightRepository) {
    suspend operator fun invoke(
        crewId: Int,
        category: String,
        sort: String,
        currentPage: Int,
        pageSize: Int
    ): List<CategoryData> {
        return repository.getLightListCategory(crewId, category, sort, currentPage, pageSize)
    }
}