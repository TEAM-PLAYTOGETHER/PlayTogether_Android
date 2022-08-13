package com.playtogether_android.domain.usecase.light

import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.repository.light.LightRepository
import javax.inject.Inject

class GetNewListUseCase @Inject constructor(private val repository: LightRepository) {
    suspend operator fun invoke(): List<CategoryData> {
        return repository.getNewLightning()
    }
}
