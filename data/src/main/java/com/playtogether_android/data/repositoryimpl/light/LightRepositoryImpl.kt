package com.playtogether_android.data.repositoryimpl.light

import android.hardware.lights.Light
import com.playtogether_android.data.datasource.light.LightDataSource
import com.playtogether_android.data.mapper.light.LightMapper
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.repository.light.LightRepository

class LightRepositoryImpl(private val lightDataSource: LightDataSource) : LightRepository {

    override suspend fun getLightListCategory(
        crewId: Int,
        category: String,
        sort: String,
        currentPage: Int,
        pageSize: Int
    ): List<CategoryData> {
        return LightMapper.mapperToCategoryData(
            lightDataSource.getLightListCategory(
                crewId,
                category,
                sort,
                currentPage,
                pageSize
            )
        )
    }

    override suspend fun getNewLightning(crewId: Int): List<CategoryData> {
        return LightMapper.mapperToHomeLightningData(
            lightDataSource.getNewLightning(crewId)
        )
    }

    override suspend fun getHotLightning(crewId: Int): List<CategoryData> {
        return LightMapper.mapperToHomeLightningData(
            lightDataSource.getHotLightning(crewId)
        )
    }
}