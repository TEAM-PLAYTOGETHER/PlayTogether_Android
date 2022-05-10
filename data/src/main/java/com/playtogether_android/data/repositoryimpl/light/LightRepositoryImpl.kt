package com.playtogether_android.data.repositoryimpl.light

import com.playtogether_android.data.datasource.light.LightDataSource
import com.playtogether_android.data.mapper.light.LightMapper
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.repository.light.LightRepository

class LightRepositoryImpl(private val lightDataSource: LightDataSource) : LightRepository {

    override suspend fun getLightListCategory(category: String, sort: String): List<CategoryData> {
        return LightMapper.mapperToCategoryData(
            lightDataSource.getLightListCategory(category, sort)
        )
    }
}