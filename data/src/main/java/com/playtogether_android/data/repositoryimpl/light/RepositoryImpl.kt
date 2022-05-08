package com.playtogether_android.data.repositoryimpl.light

import com.playtogether_android.data.datasource.light.LightDataSource
import com.playtogether_android.data.mapper.light.LightMapper
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.repository.light.LightRepository

class RepositoryImpl(private val lightDataSource: LightDataSource) : LightRepository {
    override suspend fun getLightListCategory(): CategoryData {
        val list = lightDataSource.getLightListCategory().data
        return LightMapper.mapperToCategoryData(list)
    }
}