package com.playtogether_android.data.datasource.light

import com.playtogether_android.data.api.light.LightService
import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.data.model.response.light.ResponseHomeLightning

class LightDataSourceImpl(private val lightService: LightService) : LightDataSource {

    override suspend fun getLightListCategory(
        crewId: Int,
        category: String,
        sort: String,
        currentPage: Int,
        pageSize: Int
    ): ResponseLightListCategory {
        return lightService.getLightListCategory(crewId, category, sort, currentPage, pageSize)
    }

    override suspend fun getNewLightning(crewId: Int): ResponseHomeLightning {
        return lightService.getNewLightning(crewId)
    }

    override suspend fun getHotLightning(crewId: Int): ResponseHomeLightning {
        return lightService.getHotLightning(crewId)
    }
}